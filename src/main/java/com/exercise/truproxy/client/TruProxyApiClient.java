package com.exercise.truproxy.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exercise.truproxy.config.TruProxyProperties;
import com.exercise.truproxy.exception.ServerException;
import com.exercise.truproxy.model.CompanyInfo;
import com.exercise.truproxy.model.CompanyResult;
import com.exercise.truproxy.model.OfficerInfo;
import com.exercise.truproxy.model.OfficerResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TruProxyApiClient {    
    public static final String QUERY = "Query";
    public static final String SEARCH = "Search";
    public static final String OFFICERS = "Officers";
    
    private final RestTemplate restTemplate;
    private final TruProxyProperties truProxyProperties;
	
	public CompanyResult<CompanyInfo> searchCompany(final String postfix, final String queryParamName, final String query) {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<CompanyResult<CompanyInfo>> response = null;
		
        try {
            response = restTemplate.exchange(
            		getRequestUrl(postfix, queryParamName, query),
                    HttpMethod.GET, 
                    new HttpEntity<>(headers), 
                    new ParameterizedTypeReference<>() {
                    });
            
        } catch (Exception ex) {
            log.error("Error calling TruProxy", ex.getMessage());
            ex.printStackTrace();
            throw new ServerException("Error calling TruProxy to get company details");
        }
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Failed to get companies: {}, {}", response.getStatusCode(), response.getBody());
            throw new ServerException("TruProxy get companies request failed: " + response.getStatusCode());
        }

        return response.getBody();
	}
	
	public OfficerResult<OfficerInfo> searchOfficer(final String postfix, final String queryParamName, final String query) {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<OfficerResult<OfficerInfo>> response = null;
		
        try {
            response = restTemplate.exchange(
            		getRequestUrl(postfix, queryParamName, query),
                    HttpMethod.GET, 
                    new HttpEntity<>(headers), 
                    new ParameterizedTypeReference<>() {
                    });
            
        } catch (Exception ex) {
            log.error("Error calling TruProxy", ex.getMessage());
            throw new ServerException("Error calling TruProxy to get officer details");
        }
        
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Failed to get officers: {}, {}", response.getStatusCode(), response.getBody());
            throw new ServerException("TruProxy get officers request failed: " + response.getStatusCode());
        }

        return response.getBody();
	}
	
	private String getRequestUrl(final String postfix, final String queryParamName, final String query) {
		
        return UriComponentsBuilder.fromHttpUrl(truProxyProperties.getUrl() + "/TruProxyAPI/rest/Companies/v1/" + postfix)
                .queryParam(queryParamName, query)
                .toUriString();
	}

}
