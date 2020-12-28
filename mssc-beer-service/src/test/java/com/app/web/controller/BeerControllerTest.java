package com.app.web.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.app.services.BeerService;
import com.app.web.model.BeerDto;
import com.app.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.app.web.mappers")
public class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
    BeerService beerService;
	
	@Test
	void getBeerById() throws Exception {
		given(beerService.getById(any())).willReturn(getValidBeerDto());
		
		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
				.param("iscold", "yes")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(document("v1/beer", 
				pathParameters(
						parameterWithName("beerId").description("UUID of the beer to get")
				),
				requestParameters(
						parameterWithName("iscold").description("Is Beer Cold Query Param")
				),
				responseFields(
						fieldWithPath("id").description("Id of Beer"),
						fieldWithPath("version").description("Version number"),
                        fieldWithPath("createdDate").description("Date Created"),
                        fieldWithPath("lastModifiedDate").description("Date Updated"),
                        fieldWithPath("beerName").description("Beer Name"),
                        fieldWithPath("beerStyle").description("Beer Style"),
                        fieldWithPath("upc").description("UPC of Beer"),
                        fieldWithPath("price").description("Price"),
                        fieldWithPath("quantityOnHand").description("Quantity On hand")
				)));
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = getValidBeerDto();
		String beerToJson = objectMapper.writeValueAsString(beerDto);
		
		ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
		
		given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
		
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(beerToJson)).andExpect(status().isCreated())
		.andDo(document("v1/beer", 
				requestFields(
						fields.withPath("id").ignored(),
						fields.withPath("version").ignored(),
						fields.withPath("createdDate").ignored(),
						fields.withPath("lastModifiedDate").ignored(),
						fields.withPath("beerName").description("Name of the beer"),
						fields.withPath("beerStyle").description("Style of beer"),
						fields.withPath("upc").description("Beer UPC").attributes(),
						fields.withPath("price").description("Beer Price"),
						fields.withPath("quantityOnHand").ignored()	
				)));
		
	}
	
	@Test
	void updateBeerById() throws Exception {
		given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());
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
				.quantityOnHand(12)
				.build();
	}
	
	private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }

}
