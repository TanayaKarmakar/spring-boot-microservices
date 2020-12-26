package com.app.web.mappers;

import org.mapstruct.Mapper;

import com.app.domain.Customer;
import com.app.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {
	CustomerDto customerToCustomerDto(Customer customer);
	
	Customer customerDtoToCustomer(CustomerDto customerDto);

}
