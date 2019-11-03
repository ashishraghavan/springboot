package org.ashish.springboot.practice;

import org.ashish.springboot.practice.repository.NbaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PracticeApplicationTests {

	@Autowired
	private NbaRepository repository;

	@Test
	void contextLoads() {
		System.out.println("Testing if the context has loaded");
	}


}
