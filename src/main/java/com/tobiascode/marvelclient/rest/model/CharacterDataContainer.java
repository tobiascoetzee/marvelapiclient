package com.tobiascode.marvelclient.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CharacterDataContainer {
    private int total;
    private List<MarvelCharacter> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MarvelCharacter> getResults() {
        return results;
    }

    public void setResults(List<MarvelCharacter> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "CharacterDataContainer{" +
                "total=" + total +
                ", results=" + results +
                '}';
    }
}
