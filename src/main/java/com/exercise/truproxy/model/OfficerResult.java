package com.exercise.truproxy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"etag",
    "links",
    "kind",
    "items_per_page",
    "items",
    "active_count",
    "total_results",
    "resigned_count"
})
public class OfficerResult<T> {
    private @JsonProperty("etag") String etag;
    private @JsonProperty("links") Link links;
    private @JsonProperty("kind") String kind;
    private @JsonProperty("items_per_page") long itemsPerPage;
    private @JsonProperty("items")List<T> data;
    private @JsonProperty("active_count") int activeCount;
    private @JsonProperty("total_results") int totalresults;
    private @JsonProperty("resigned_count") int resignedCount;
    
    public OfficerResult() {}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public Link getLinks() {
		return links;
	}

	public void setLinks(Link links) {
		this.links = links;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public long getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(long itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public int getTotalresults() {
		return totalresults;
	}

	public void setTotalresults(int totalresults) {
		this.totalresults = totalresults;
	}

	public int getResignedCount() {
		return resignedCount;
	}

	public void setResignedCount(int resignedCount) {
		this.resignedCount = resignedCount;
	}
}
