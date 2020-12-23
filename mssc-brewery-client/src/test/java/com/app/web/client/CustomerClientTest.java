package com.app.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.web.model.CustomerDto;

@SpringBootTest
public class CustomerClientTest {
	@Autowired
	CustomerClient client;
	
	@Test
	void testGetBeerById() {
		CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());
		
		assertNotNull(customerDto);
	}
	
	@Test
	void testSaveNewCustomer() {
		CustomerDto customerDto = CustomerDto
				.builder()
				.name("John Doe")
				.build();
		
		URI uri = client.saveNewCustomer(customerDto);
		
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}
	
	@Test
	void testUpdateCustomer() {
		CustomerDto customerDto = CustomerDto
				.builder()
				.name("John Doe")
				.build();
		
		client.updateCustomer(UUID.randomUUID(), customerDto);
	}
	
	@Test
	void testDeleteCustomer() {
		client.deleteCustomer(UUID.randomUUID());
	}

}
