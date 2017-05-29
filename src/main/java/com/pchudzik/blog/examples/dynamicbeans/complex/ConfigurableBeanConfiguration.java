package com.pchudzik.blog.examples.dynamicbeans.complex;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
class ConfigurableBeanConfiguration {
	@Bean
	public static BeanFactoryPostProcessor configurableBeanFactory() {
		return new ConfigurableBeanFactory(new ClassPathResource("/application.properties"));
	}
}
