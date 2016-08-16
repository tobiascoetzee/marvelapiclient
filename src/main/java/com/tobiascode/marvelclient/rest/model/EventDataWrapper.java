package com.tobiascode.marvelclient.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class EventDataWrapper {
    private EventDataContainer data;

    public EventDataContainer getData() {
        return data;
    }

    public void setData(EventDataContainer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventDataWrapper{" +
                "data=" + data +
                '}';
    }
}
