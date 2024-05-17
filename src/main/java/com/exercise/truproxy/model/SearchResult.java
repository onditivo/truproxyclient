package com.exercise.truproxy.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchResult {
	private final int total;
	private final List<String> items;
}
