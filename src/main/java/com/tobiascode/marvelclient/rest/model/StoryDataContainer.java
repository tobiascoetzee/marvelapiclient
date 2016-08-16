package com.tobiascode.marvelclient.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class StoryDataContainer {
    private int total;
    private List<Story> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Story> getResults() {
        return results;
    }

    public void setResults(List<Story> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "StoryDataContainer{" +
                "total=" + total +
                ", results=" + results +
                '}';
    }
}
