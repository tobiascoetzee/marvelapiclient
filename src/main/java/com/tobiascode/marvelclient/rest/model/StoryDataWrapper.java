package com.tobiascode.marvelclient.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class StoryDataWrapper {
    private StoryDataContainer data;

    public StoryDataContainer getData() {
        return data;
    }

    public void setData(StoryDataContainer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StoryDataWrapper{" +
                "data=" + data +
                '}';
    }
}
