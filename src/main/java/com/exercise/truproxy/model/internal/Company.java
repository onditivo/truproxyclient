package com.exercise.truproxy.model.internal;

import java.util.Date;
import java.util.List;

import com.exercise.truproxy.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"company_number",
    "company_type",
    "title",
    "company_status",
    "date_of_creation",
    "address",
    "officers"
})
public record Company(
		 @JsonProperty("company_number") String companyNumber,
		 @JsonProperty("company_type") String companyType,
		 @JsonProperty("title") String title,
		 @JsonProperty("company_status") String companyStatus,
		 @JsonProperty("date_of_creation") Date dateOfCreation,
		 @JsonProperty("address") Address address,
		 @JsonProperty("officers")List<Officer> officers) {

}
