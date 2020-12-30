package com.app.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	@Autowired
	ObjectMapper objectMapper;
	
	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("BeerName")
				.beerStyle(BeerStyleEnum.ALE)
				.uuid(UUID.randomUUID())
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.price(new BigDecimal("12.99"))
				.upc("3435435435334")
				.build();
	}
}
