package my.comp.hessian.client;

import static org.springframework.util.Assert.notNull;

import java.lang.annotation.Annotation;
import java.util.Map;

import my.comp.hessian.server.HessianBeanNameGenerator;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;

public class HessianServiceProxyScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

	private String basePackage;
	
	private String excludePackage;

	private Class<? extends Annotation> annotationClass;

	private ApplicationContext applicationContext;

	private String beanName;

	private String serviceUrl;

	private boolean processPropertyPlaceHolders;

	private BeanNameGenerator nameGenerator;
	
	private Map<String,String> requestHeaders;
	
	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * This property lets you set the base package for your mapper interface files.
	 * <p>
	 * You can set more than one package by using a semicolon or comma as a separator.
	 * <p>
	 * Mappers will be searched for recursively starting in the specified package(s).
	 *
	 * @param basePackage
	 *            base package name
	 */
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public void setExcludePackage(String excludePackage) {
		this.excludePackage = excludePackage;
	}

	/**
	 * This property specifies the annotation that the scanner will search for.
	 * <p>
	 * The scanner will register all interfaces in the base package that also have the specified annotation.
	 * <p>
	 * Note this can be combined with markerInterface.
	 *
	 * @param annotationClass
	 *            annotation class
	 */
	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	/**
	 *
	 * @since 1.1.1
	 *
	 * @param processPropertyPlaceHolders
	 */
	public void setProcessPropertyPlaceHolders(boolean processPropertyPlaceHolders) {
		this.processPropertyPlaceHolders = processPropertyPlaceHolders;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	/**
	 * Gets beanNameGenerator to be used while running the scanner.
	 *
	 * @return the beanNameGenerator BeanNameGenerator that has been configured
	 * @since 1.2.0
	 */
	public BeanNameGenerator getNameGenerator() {
		return nameGenerator;
	}

	/**
	 * Sets beanNameGenerator to be used while running the scanner.
	 *
	 * @param nameGenerator
	 *            the beanNameGenerator to set
	 * @since 1.2.0
	 */
	public void setNameGenerator(BeanNameGenerator nameGenerator) {
		this.nameGenerator = nameGenerator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		notNull(this.basePackage, "Property 'basePackage' is required");
		if(nameGenerator == null) nameGenerator = new HessianBeanNameGenerator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// left intentionally blank
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0.2
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if (this.processPropertyPlaceHolders) {
			processPropertyPlaceHolders();
		}

		HessianServiceProxyScanner scanner = new HessianServiceProxyScanner(registry);
		scanner.setAnnotationClass(this.annotationClass);
		scanner.setResourceLoader(this.applicationContext);
		scanner.setBeanNameGenerator(this.nameGenerator);
		scanner.setServiceUrl(this.serviceUrl);
		scanner.setRequestHeaders(this.requestHeaders);
		String[] excludePackages = StringUtils.tokenizeToStringArray(this.excludePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		if (null == excludePackages) excludePackages = new String[0];
		scanner.registerFilters(excludePackages);
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	/*
	 * BeanDefinitionRegistries are called early in application startup, before BeanFactoryPostProcessors. This means that
	 * PropertyResourceConfigurers will not have been loaded and any property substitution of this class' properties will fail. To avoid
	 * this, find any PropertyResourceConfigurers defined in the context and run them on this class' bean definition. Then update the
	 * values.
	 */
	private void processPropertyPlaceHolders() {
		Map<String, PropertyResourceConfigurer> prcs = applicationContext.getBeansOfType(PropertyResourceConfigurer.class);

		if (!prcs.isEmpty() && applicationContext instanceof GenericApplicationContext) {
			BeanDefinition mapperScannerBean = ((GenericApplicationContext) applicationContext).getBeanFactory().getBeanDefinition(beanName);

			// PropertyResourceConfigurer does not expose any methods to explicitly perform
			// property placeholder substitution. Instead, create a BeanFactory that just
			// contains this mapper scanner and post process the factory.
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			factory.registerBeanDefinition(beanName, mapperScannerBean);

			for (PropertyResourceConfigurer prc : prcs.values()) {
				prc.postProcessBeanFactory(factory);
			}

			PropertyValues values = mapperScannerBean.getPropertyValues();

			this.basePackage = updatePropertyValue("basePackage", values);
		}
	}

	private String updatePropertyValue(String propertyName, PropertyValues values) {
		PropertyValue property = values.getPropertyValue(propertyName);

		if (property == null) {
			return null;
		}

		Object value = property.getValue();

		if (value == null) {
			return null;
		} else if (value instanceof String) {
			return value.toString();
		} else if (value instanceof TypedStringValue) {
			return ((TypedStringValue) value).getValue();
		} else {
			return null;
		}
	}

}
