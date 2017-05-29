package com.pchudzik.blog.examples.dynamicbeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
class DynamicBeanFactoryProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;

		IntStream
				.range(0, 100)
				.forEach(index -> beanDefinitionRegistry.registerBeanDefinition("repeatableBean" + index, BeanDefinitionBuilder
						.rootBeanDefinition(DynamicBeanExample.class)
						.setFactoryMethodOnBean("createInstance", "dynamicBeanFactoryProcessor")
						.addConstructorArgValue("repeatable bean " + index)
						.addConstructorArgReference("testDependency")
						.getBeanDefinition()));
	}

	DynamicBeanExample createInstance(String beanId, TestDependency testDependency) {
		return new DynamicBeanExample(beanId, testDependency);
	}
}
