package com.exercise.truproxy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "page_number",
    "kind",
    "total_results",
    "items"
})
public class CompanyResult<T> {
    private @JsonProperty("page_number") int pageNumber;
    private @JsonProperty("kind") String kind;
    private @JsonProperty("total_results") long totalElements;
    private @JsonProperty("items")List<T> data;
    
    public CompanyResult() {}

    @JsonProperty("page_number")
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@JsonProperty("kind")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@JsonProperty("total_results")
	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	@JsonProperty("items")
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
