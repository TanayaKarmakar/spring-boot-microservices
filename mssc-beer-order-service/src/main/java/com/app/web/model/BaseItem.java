package com.app.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseItem {
	@JsonProperty("id")
	private UUID id;
	
	@JsonProperty("version")
	private Integer version;
	
	@JsonProperty("createdDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;
	
	@JsonProperty("lastModifiedDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime lastModifiedDate;

}
