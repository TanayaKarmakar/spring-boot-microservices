package com.app.services;

import java.util.UUID;

import com.app.web.model.CustomerDto;

public interface CustomerService {
	public CustomerDto getCustomerById(UUID id);
}
