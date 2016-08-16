package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public interface ContainsEventResources extends Serializable {
    String getResourceName();

    String getOriginalResourceName();

    String getEventCollectionUri();

    int getAvailableEvents();

    List<Integer> getEventIds();

    void setEventIds(List<Integer> eventIds);
}
