package com.pchudzik.blog.examples.dynamicbeans.complex;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
class ConfigurableBeanFactory implements BeanFactoryPostProcessor, InitializingBean {
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
				.loadProperties(new ClassPathResource("/application.properties"))
				.getProperty("dynamic-beans.instances", "")
				.split(","));
	}
}
