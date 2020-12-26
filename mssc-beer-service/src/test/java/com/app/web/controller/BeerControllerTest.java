package com.app.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.web.model.BeerDto;
import com.app.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = getValidBeerDto();
		String beerToJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(post("/api/v1/beer")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(beerToJson)).andExpect(status().isCreated());
		
	}
	
	@Test
	void updateBeerById() throws Exception {
		BeerDto beerDto = getValidBeerDto();
		String beerToJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(beerToJson)).andExpect(status().isNoContent());
	}
	
	BeerDto getValidBeerDto() {
		return BeerDto.builder()
				.beerName("My Beer")
				.beerStyle(BeerStyleEnum.ALE)
				.price(new BigDecimal("2.99"))
				.upc(3564354354345L)
				.build();
	}

}
