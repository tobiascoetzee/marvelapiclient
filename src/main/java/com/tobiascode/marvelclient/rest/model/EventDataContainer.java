package com.tobiascode.marvelclient.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class EventDataContainer {
    private int total;
    private List<Event> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Event> getResults() {
        return results;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "EventDataContainer{" +
                "total=" + total +
                ", results=" + results +
                '}';
    }
}
