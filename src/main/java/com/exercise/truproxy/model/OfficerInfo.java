package com.exercise.truproxy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"address",
    "name",
    "appointed_on",
    "officer_role",
    "links",
    "date_of_birth",
    "occupation",
    "country_of_residence",
    "nationality",
    "resigned_on"
})
public record OfficerInfo (
	@JsonProperty("address") Address address,
	@JsonProperty("name") String name,
	@JsonProperty("appointed_on") Date appointedOn,
	@JsonProperty("officer_role") String officerRole,
	@JsonProperty("links") Link links,
	@JsonProperty("date_of_birth") AbbreviateDate dateOfBirth,
	@JsonProperty("occupation") String occupation,
	@JsonProperty("country_of_residence") String countryOfResidence,
	@JsonProperty("nationality") String nationality,
	@JsonProperty("resigned_on") Date resignedOn
	) {
	
}