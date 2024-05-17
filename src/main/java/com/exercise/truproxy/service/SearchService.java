package com.exercise.truproxy.service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exercise.truproxy.model.CompanyInfo;
import com.exercise.truproxy.model.CompanyResult;
import com.exercise.truproxy.model.OfficerInfo;
import com.exercise.truproxy.model.OfficerResult;
import com.exercise.truproxy.model.SearchRequest;
import com.exercise.truproxy.model.internal.Company;
import com.exercise.truproxy.model.internal.CompanyDetail;
import com.exercise.truproxy.model.internal.Officer;
import com.exercise.truproxy.client.TruProxyApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    
    public static final String QUERY = "Query";
    public static final String COMPANY_NAME = "CompanyNumber";
    public static final String SEARCH = "Search";
    public static final String OFFICERS = "Officers";
    
	private final TruProxyApiClient truProxyApiClient;
	
	public CompanyDetail getCompany(final SearchRequest searchRequest, final boolean includeActiveOnly) {
		CompanyResult<CompanyInfo> companyResult = 
				(isNotBlank(searchRequest.getCompanyName()) && isNotBlank(searchRequest.getCompanyNumber())) ?
				getCompanyDetail(searchRequest.getCompanyNumber()) : getCompanyDetail(searchRequest.getCompanyName());
		
		OfficerResult<OfficerInfo> officerResult = isNotBlank(searchRequest.getCompanyNumber()) ? 
				getOfficer(searchRequest.getCompanyNumber()) : getOfficer(extractCompanyNumber(companyResult));
		
		CompanyDetail companyDetail = consolidateResult(companyResult, officerResult, includeActiveOnly);
		
		//ToDo: save company detail
		if(isNotBlank(searchRequest.getCompanyNumber())) {
			//ToDo: Add persistence service
		}
		
		return companyDetail;
        
	}

	private CompanyResult<CompanyInfo> getCompanyDetail(final String searchTerm) {
		return truProxyApiClient.searchCompany(SEARCH, QUERY, searchTerm);
	}


	private OfficerResult<OfficerInfo> getOfficer(final String companyNumber) {
		log.warn("An invalid company number, return empty result");
		
		return isNotBlank(companyNumber) ? 
				truProxyApiClient.searchOfficer(OFFICERS, COMPANY_NAME, companyNumber) : new OfficerResult<OfficerInfo>();
	}
	
	private String extractCompanyNumber(final CompanyResult<CompanyInfo> searchResult) {
		Optional<CompanyInfo> result = searchResult.getData()
				.stream().filter(ci -> isNotBlank(ci.companyNumber()))
				.collect(Collectors.toList()).stream().findFirst();
		
		return result.isPresent() ? result.get().companyNumber() : null;
	}

	private CompanyDetail consolidateResult(final CompanyResult<CompanyInfo> companyResult, 
			final OfficerResult<OfficerInfo> officerResult, final boolean includeActiveOnly) {
		if (includeActiveOnly) {
			List<CompanyInfo> activeCompany = companyResult.getData()
					.stream()
					.filter(ci -> "active".equalsIgnoreCase(ci.companyStatus()))
					.collect(Collectors.toList());
			
			List<OfficerInfo> activeOfficer = officerResult.getData()
					.stream()
					.filter(oi -> isNull(oi.resignedOn()))
					.collect(Collectors.toList());
			
			return transformResult(activeCompany, activeOfficer);
		}


		return transformResult(companyResult.getData(), officerResult.getData());
	}

	private CompanyDetail transformResult(List<CompanyInfo> companyInfo, List<OfficerInfo> officerInfo) {
		List<Officer> officers = officerInfo
				.stream()
				.map(oi -> new Officer(oi.name(), oi.officerRole(), oi.appointedOn(), oi.address()))
				.collect(Collectors.toList());
		
		List<Company> company = companyInfo
				.stream()
				.map(ci -> new Company(ci.companyNumber(), ci.companyType(), ci.title(), ci.companyStatus(), ci.dateOfCreation(), ci.address(), officers))
				.collect(Collectors.toList());
		
		CompanyDetail companyDetail = new CompanyDetail(company.size(), company);
		
		return companyDetail;
	}
}
