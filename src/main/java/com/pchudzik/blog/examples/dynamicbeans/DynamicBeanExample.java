package com.pchudzik.blog.examples.dynamicbeans;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DynamicBeanExample {
	private final String beanId;
	private final TestDependency testDependency;

	public String hello() {
		return "hello from " + beanId + " with dependency " + testDependency.hello();
	}
}
