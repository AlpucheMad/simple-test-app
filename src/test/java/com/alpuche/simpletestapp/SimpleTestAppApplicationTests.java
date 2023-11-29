package com.alpuche.simpletestapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class SimpleTestAppApplicationTests {


	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Environment env;
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void contextLoads() {
		System.out.println("STARTING TESTS...");
	}

	@ParameterizedTest
	@ValueSource(strings = {"property1", "property2"})
	void testProperties(String propertyName) {
		String propertyValue = env.getProperty(propertyName);
		assertNotNull(propertyValue, "El valor de la propiedad no debe ser nulo para: " + propertyName);
	}

	@ParameterizedTest
	@ValueSource(strings = {"/endpoint1", "/endpoint2"})
	void testEndpoints(String url) throws Exception {
		mockMvc.perform(get(url)).andExpect(status().isOk());
	}

	@ParameterizedTest
	@CsvSource({"1, 2, 3", "2, 3, 5", "3, 5, 8"})
	void testAddition(int num1, int num2, int expectedResult) {
		assertEquals(expectedResult, num1 + num2);
	}

}
