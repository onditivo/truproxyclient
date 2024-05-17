package com.exercise.truproxy.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercise.truproxy.model.SearchRequest;
import com.exercise.truproxy.model.internal.CompanyDetail;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

@SpringBootTest
@WireMockTest(httpPort = 8181)
public class SearchServiceTest {
	
    @Autowired
    private SearchService searchService;
    
    @Test
    void given_truproxy_respose_lookupservice_for_only_active_company_return_one_company_with_one_active_officer(
    		WireMockRuntimeInfo wireMockRuntimeInfo) throws Exception {
    	SearchRequest request = new SearchRequest();
    	request.setCompanyName("bbc");
    	request.setCompanyNumber("10241297");
        
        stubFor(get(urlPathMatching("/TruProxyAPI/rest/Companies/v1/Search?([a-z]*)"))
        		.withHeader("x-api-key", containing("100%Fake"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-company-response.json")));
        
        stubFor(get(urlPathMatching("/TruProxyAPI/rest/Companies/v1/Officers?([a-z]*)"))
        		.withHeader("x-api-key", containing("100%Fake"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("get-officers-response.json")));
        
        CompanyDetail response = searchService.getCompany(request, true);

        assertThat(response).isNotNull();
        assertThat(response.totalElements()).isEqualTo(1);

        verify(getRequestedFor(urlPathMatching("/TruProxyAPI/rest/Companies/v1/Search?([a-z]*)"))
        		.withHeader("x-api-key", containing("100%Fake")));
        
        verify(getRequestedFor(urlPathMatching("/TruProxyAPI/rest/Companies/v1/Officers?([a-z]*)"))
        		.withHeader("x-api-key", containing("100%Fake")));
    }
}
