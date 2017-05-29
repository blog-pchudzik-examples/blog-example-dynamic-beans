package com.pchudzik.blog.examples.dynamicbeans;

import org.springframework.stereotype.Component;

@Component
class TestDependency {
	public String hello() {
		return "test dependency";
	}
}
