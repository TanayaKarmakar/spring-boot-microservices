package com.app.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTest
public class BeerDtoTest extends BaseTest {
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void testSerializeDto() throws JsonProcessingException {
		BeerDto beerDto = getDto();
		String jsonString = objectMapper.writeValueAsString(beerDto);
		
		System.out.println(jsonString);
	}
	
	@Test
	void testDeserializeDto() throws JsonMappingException, JsonProcessingException {
		String jsonString = "{\"id\":\"159afea3-584a-489a-9978-b13b5526c44d\","
				+ "\"version\":null,\"createdDate\":\"2020-12-28T05:53:40.654788+05:30\","
				+ "\"lastModifiedDate\":\"2020-12-28T05:53:40.654816+05:30\","
				+ "\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\","
				+ "\"upc\":3435435435334,\"price\":12.99,\"quantityOnHand\":null}\"";
		
		BeerDto beerDto = objectMapper.readValue(jsonString, BeerDto.class);
		
		System.out.println(beerDto.toString());
				
	}
}
