package my.comp.hessian.server;

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
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.util.StringUtils;

public class HessianServiceExporterScanner extends ClassPathBeanDefinitionScanner {

	private Class<? extends Annotation> annotationClass;

	public HessianServiceExporterScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public void registerFilters() {
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
	}

	/**
	 * Calls the parent search that will search and register all the candidates. Then the registered objects are post processed to set them
	 * as HessianServiceExporter
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
					logger.debug("Creating HessianServiceExporter with name '" + holder.getBeanName() + "' and '" + definition.getBeanClassName()
							+ "' serviceInterface");
				}

				// the mapper interface is the original class of the bean
				// but, the actual class of the bean is MapperFactoryBean

				Map<String, Object> attrs = definition.getMetadata().getAnnotationAttributes(RemoteService.class.getName());
				String name = (String) attrs.get("value");

				definition.setAttribute("name", name);
				definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());

				String serviceBeanName = determineServiceName(definition);

				definition.getPropertyValues().add("service", new RuntimeBeanReference(serviceBeanName));

				// definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

				definition.setBeanClass(HessianServiceExporter.class);
			}
		}

		return beanDefinitions;
	}

	protected String determineServiceName(ScannedGenericBeanDefinition definition) {

//		Map<String, Object> attrs = definition.getMetadata().getAnnotationAttributes(LocalService.class.getName());
//		String service = attrs == null ? "" : (String) attrs.get("name");
//		if (!StringUtils.isEmpty(service)) return service;

		String name = definition.getBeanClassName();
		name = name.substring(name.lastIndexOf(".") + 1);
		name = name + "Impl";

		return StringUtils.uncapitalize(name);

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
			logger.warn("Skipping HessianServiceExporter with name '" + beanName + "' and '" + beanDefinition.getBeanClassName() + "' serviceInterface"
					+ ". Bean already defined with the same name!");
			return false;
		}
	}

}
