package com.pchudzik.blog.examples.dynamicbeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
class SingleDynamicBeanProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
		final BeanDefinition dynamicBean = BeanDefinitionBuilder
				.rootBeanDefinition(DynamicBeanExample.class)
				.setScope(SCOPE_PROTOTYPE)
				.addConstructorArgValue("dynamically created bean")
				.getBeanDefinition();

		beanDefinitionRegistry.registerBeanDefinition("dynamicBean", dynamicBean);
	}

}
