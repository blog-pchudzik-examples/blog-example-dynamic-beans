package com.pchudzik.blog.examples.dynamicbeans.complex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestService {
	private final Collection<TestRepository> allRepositories;

	public String hello() {
		return "service with " + allRepositories.stream()
				.map(TestRepository::hello)
				.collect(Collectors.joining(","));
	}
}
