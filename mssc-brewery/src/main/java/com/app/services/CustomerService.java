package com.app.services;

import java.util.UUID;

import com.app.web.model.CustomerDto;

public interface CustomerService {
	public CustomerDto getCustomerById(UUID id);
	
	CustomerDto saveNewCustomer(CustomerDto customerDto);

	void updateCustomer(UUID customerId, CustomerDto customerDto);

	void deleteCustomer(UUID customerId);
}
