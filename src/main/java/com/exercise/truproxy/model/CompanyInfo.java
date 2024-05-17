package com.exercise.truproxy.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyInfo(@JsonProperty("company_status") String companyStatus,
	 @JsonProperty("address_snippet") String addressSnippet,
	 @JsonProperty("date_of_creation") Date dateOfCreation,
	 @JsonProperty("matches") Matches matches,
	 @JsonProperty("description") String description,
	 @JsonProperty("links") Link links,
	 @JsonProperty("company_number") String companyNumber,
	 @JsonProperty("title") String title,
	 @JsonProperty("company_type") String companyType,
	 @JsonProperty("address") Address address,
	 @JsonProperty("kind") String kind,
	 @JsonProperty("description_identifier") List<String> descriptionIdentifier) {

}
