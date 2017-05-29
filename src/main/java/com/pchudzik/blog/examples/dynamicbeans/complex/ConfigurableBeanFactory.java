package com.pchudzik.blog.examples.dynamicbeans.complex;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
class ConfigurableBeanFactory implements BeanFactoryPostProcessor, InitializingBean {
	private final Resource propertiesLocation;

	private List<String> beanInstances;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
		beanInstances.forEach(instance -> {
			registry.registerBeanDefinition(instance, BeanDefinitionBuilder
					.rootBeanDefinition(TestRepository.class)
					.addConstructorArgValue(instance)
					.getBeanDefinition());
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.beanInstances = asList(PropertiesLoaderUtils
				.loadProperties(propertiesLocation)
				.getProperty("dynamic-beans.instances", "")
				.split(","));
	}
}
