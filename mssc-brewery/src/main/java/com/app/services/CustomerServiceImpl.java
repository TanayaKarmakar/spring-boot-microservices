package com.app.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID id) {
		return CustomerDto.builder().id(UUID.randomUUID())
				.name("John Doe")
				.build();
	}

}
