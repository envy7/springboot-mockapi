package com.example.mockapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MockapiApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Test properties file used: application-test.properties");
	}

}
