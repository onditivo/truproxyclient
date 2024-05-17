package com.exercise.truproxy.controller;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.truproxy.exception.ClientException;
import com.exercise.truproxy.model.SearchRequest;
import com.exercise.truproxy.model.internal.CompanyDetail;
import com.exercise.truproxy.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class SearchController {
	 private final SearchService searchService;
	 
	 @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public CompanyDetail getCompany(final @RequestBody SearchRequest searchRequest,
			 final @RequestParam(name = "includeActiveOnly", required = true, defaultValue = "true") boolean includeActiveOnly) {
		 if (isNull(searchRequest) || 
				 (isEmpty(searchRequest.getCompanyName()) && isEmpty(searchRequest.getCompanyNumber()))) {
			 throw new ClientException("Invalid input value");
		 }
		 
		 return searchService.getCompany(searchRequest, includeActiveOnly);
	 }
}
