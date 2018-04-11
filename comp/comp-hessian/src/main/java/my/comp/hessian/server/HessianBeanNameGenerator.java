package my.comp.hessian.server;

import java.util.Map;

import my.comp.rmi.annotation.RemoteService;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;

public class HessianBeanNameGenerator extends AnnotationBeanNameGenerator {

	@Override
	protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getMetadata();
		
		Map<String, Object> attrs = amd.getAnnotationAttributes(RemoteService.class.getName());
		String name = (String) attrs.get("value");
		
		return name;
	}
	

}
