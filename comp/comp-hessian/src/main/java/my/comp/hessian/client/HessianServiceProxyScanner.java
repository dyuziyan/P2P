package my.comp.hessian.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import my.comp.rmi.annotation.LocalService;
import my.comp.rmi.annotation.RemoteService;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

public class HessianServiceProxyScanner extends ClassPathBeanDefinitionScanner {

	private Class<? extends Annotation> annotationClass;
	private String serviceUrl;
	private Map<String, String> requestHeaders;
	private long readTimeout = 1000 * 60;
	private long connectTimeout = 1000 * 20;

	public void setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
	}

	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public HessianServiceProxyScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void registerFilters(final String[] excludePackages) {
		boolean acceptAllInterfaces = true;

		// if specified, use the given annotation and / or marker interface
		if (this.annotationClass != null) {
			addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
			acceptAllInterfaces = false;
		}

		if (acceptAllInterfaces) {
			// default include filter that accepts all classes
			addIncludeFilter(new TypeFilter() {
				public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
					return true;
				}
			});
		}

		// exclude package-info.java
		addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});

		addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				for (String excludePackage : excludePackages) {
					if (className.startsWith(excludePackage)) { return true; }
				}
				return false;
			}
		});
	}

	/**
	 * Calls the parent search that will search and register all the candidates. Then the registered objects are post processed to set them
	 * as HessianServiceProxy
	 */
	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

		if (beanDefinitions.isEmpty()) {
			logger.warn("No hessian service was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
		} else {
			for (BeanDefinitionHolder holder : beanDefinitions) {
				ScannedGenericBeanDefinition definition = (ScannedGenericBeanDefinition) holder.getBeanDefinition();

				if (logger.isDebugEnabled()) {
					logger.debug("Creating HessianServiceProxy with name '" + holder.getBeanName() + "' and '"
							+ definition.getBeanClassName() + "' serviceInterface");
				}

				// the mapper interface is the original class of the bean
				// but, the actual class of the bean is HessianProxyFactoryBean

				Map<String, Object> attrs = definition.getMetadata().getAnnotationAttributes(RemoteService.class.getName());
				String name = (String) attrs.get("value");

				String localService = getLocalService(definition);
				if (localService != null && getRegistry().containsBeanDefinition(localService)) {
					// 删除代理实现
					getRegistry().removeBeanDefinition(name);
					if (logger.isDebugEnabled()) {
						logger.debug("Removed RemoteService with name " + name + ", use LocalService " + localService + " instead");
					}
					continue;
				}

				definition.setAttribute("name", name);
				definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
				definition.getPropertyValues().add("serviceUrl", serviceUrl + name);

				// definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
				definition.getPropertyValues().add("proxyFactory", new HessianProxyFactory2(requestHeaders, readTimeout, connectTimeout));
				definition.setBeanClass(HessianProxyFactoryBean.class);

			}
		}

		return beanDefinitions;
	}

	private String getLocalService(ScannedGenericBeanDefinition definition) {
		Map<String, Object> attrs = definition.getMetadata().getAnnotationAttributes(LocalService.class.getName());
		if (attrs == null) return null;
		String name = (String) attrs.get("ref");
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
		if (super.checkCandidate(beanName, beanDefinition)) {
			return true;
		} else {
			logger.warn("Skipping HessianServiceProxy with name '" + beanName + "' and '" + beanDefinition.getBeanClassName()
					+ "' serviceInterface" + ". Bean already defined with the same name!");
			return false;
		}
	}

}
