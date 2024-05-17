package com.exercise.truproxy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Address(
	    @JsonProperty("premises") String premises,
	    @JsonProperty("postal_code") String postalCode,
	    @JsonProperty("country") String country,
	    @JsonProperty("locality") String locality,
	    @JsonProperty("address_line_1") String firstLineAddress
		) {

}
