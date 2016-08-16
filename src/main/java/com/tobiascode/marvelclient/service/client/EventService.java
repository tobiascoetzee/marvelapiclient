package com.tobiascode.marvelclient.service.client;

import java.util.List;

import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;
import com.tobiascode.marvelclient.service.model.Event;

public interface EventService {
    int getTotalEvents();

    List<Event> getAllEvents(int totalEvents);

    void getEventIdsForGivenResources(List<? extends ContainsEventResources> resources);
}
