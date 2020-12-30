package com.app.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeerDto {
	@Null
	private UUID uuid;
	
	@Null
	private Long version;
	
	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;
	
	@Null
	private OffsetDateTime lastModifiedDate;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;
	
	@NotNull
	private BeerStyleEnum beerStyle;

	@NotNull
	private String upc;
	
	@Positive
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;
	
	@Positive
	private Integer quantityToBrew;
}
