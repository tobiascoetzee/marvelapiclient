package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public class EventResources implements ContainsOtherResources, Serializable {
    private ContainsEventResources eventResources;

    public EventResources(ContainsEventResources eventResources) {
        this.eventResources = eventResources;
    }

    @Override
    public String getThisResourceName() {
        return "event";
    }

    @Override
    public String getContainingResourceName() {
        return eventResources.getResourceName();
    }

    @Override
    public String getOriginalResourceName() {
        return eventResources.getOriginalResourceName();
    }

    @Override
    public String getResourceCollectionUri() {
        return eventResources.getEventCollectionUri();
    }

    @Override
    public int getAvailableResource() {
        return eventResources.getAvailableEvents();
    }

    @Override
    public void setResourceIds(List<Integer> eventIds) {
        eventResources.setEventIds(eventIds);
    }

    @Override
    public List<Integer> getResourceIds() {
        return eventResources.getEventIds();
    }
}
