package com.exercise.truproxy.model.internal;

import java.util.Date;

import com.exercise.truproxy.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Officer(
	@JsonProperty("name") String name,
	@JsonProperty("officer_role") String officerRole,
	@JsonProperty("appointed_on") Date appointedOn,
	@JsonProperty("address") Address address) {

}
