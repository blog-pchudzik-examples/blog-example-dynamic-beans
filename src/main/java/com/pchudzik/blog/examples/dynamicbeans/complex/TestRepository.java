package com.pchudzik.blog.examples.dynamicbeans.complex;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class TestRepository {
	private final String instanceId;

	String hello() {
		return instanceId;
	}
}
