package com.exercise.truproxy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.exercise.truproxy.exception.ClientException;
import com.exercise.truproxy.model.Address;
import com.exercise.truproxy.model.SearchRequest;
import com.exercise.truproxy.model.internal.Company;
import com.exercise.truproxy.model.internal.CompanyDetail;
import com.exercise.truproxy.model.internal.Officer;
import com.exercise.truproxy.service.SearchService;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {
	@Mock
	private SearchService mockSearchService;
	
	@InjectMocks
	private SearchController searchController;
	
	@Test
	public void given_a_invalid_input_getCompany_return_one_item() throws ParseException {
		// given
    	SearchRequest request = new SearchRequest();
    	request.setCompanyName("BBC LIMITED");
    	request.setCompanyNumber("06500244");
    	CompanyDetail companyDetail= createCompanyDetail();
    	
    	when(mockSearchService.getCompany(request, false)).thenReturn(companyDetail);
    	
    	// when
    	CompanyDetail response = searchController.getCompany(request, false);
    	
    	// then
        assertThat(response).isNotNull();
        assertThat(response.totalElements()).isEqualTo(1);
	}

	@Test
	public void given_an_invalid_input_getCompany_throws_exception() {
		// given
    	SearchRequest request = new SearchRequest();
    	String expectedMessage = "Invalid input value";
    	
    	// when
	    Exception exception = assertThrows(ClientException.class, () -> {
	    	searchController.getCompany(request, false);
	    });

	    // then
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
    private static CompanyDetail createCompanyDetail() throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	Date appointedOn = formatter.parse("2008-02-11");
    	Address officerAddress = new Address("5", "SW20 0DP", "England", "London", "Cranford Close");
    	Officer officer = new Officer("BOXALL, Sarah Victoria", "secretary", appointedOn, officerAddress);
    	
		List<Officer> officers = new ArrayList<>();
		officers.add(officer);
		
		Address companyAddress = new Address("Boswell Cottage Main Street", "DN22 0AD", "England", "Retford", "North Leverton");
		Date dateOfCreation = formatter.parse("2008-02-11");
		Company company = new Company("06500244", "ltd", "BBC LIMITED", "active", dateOfCreation, companyAddress, officers);
		
		List<Company> companies = new ArrayList<>();
		companies.add(company);
		
		CompanyDetail companyDetail = new CompanyDetail(companies.size(), companies);
		
		return companyDetail;
    }
}
