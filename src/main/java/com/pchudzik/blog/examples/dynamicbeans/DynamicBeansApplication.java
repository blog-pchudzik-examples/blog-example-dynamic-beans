package com.pchudzik.blog.examples.dynamicbeans;

import com.pchudzik.blog.examples.dynamicbeans.complex.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DynamicBeansApplication {
	public static void main(String[] args) {
		final ApplicationContext applicationContext = SpringApplication.run(DynamicBeansApplication.class, args);

		singleDynamicBeanExample(applicationContext);
		multipleDynamicBeanExample(applicationContext);
		dynamicRepositories(applicationContext);
	}

	private static void singleDynamicBeanExample(ApplicationContext applicationContext) {
		final DynamicBeanExample dynamicBean = applicationContext.getBean("dynamicBean", DynamicBeanExample.class);
		System.out.println(dynamicBean.hello());
	}

	private static void multipleDynamicBeanExample(ApplicationContext applicationContext) {
		final DynamicBeanExample repetableBean = applicationContext.getBean("repeatableBean42", DynamicBeanExample.class);
		System.out.println(repetableBean.hello());
	}

	private static void dynamicRepositories(ApplicationContext applicationContext) {
		final TestService testService = applicationContext.getBean(TestService.class);
		System.out.println(testService.hello());
	}
}
