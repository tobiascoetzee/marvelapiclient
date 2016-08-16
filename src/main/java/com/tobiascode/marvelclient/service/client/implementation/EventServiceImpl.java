package com.tobiascode.marvelclient.service.client.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.rest.model.EventDataWrapper;
import com.tobiascode.marvelclient.service.client.EventService;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;
import com.tobiascode.marvelclient.service.model.containsresources.EventResources;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.mapper.EventMapper;

public class EventServiceImpl extends CoreServiceImpl<Event, com.tobiascode.marvelclient.rest.model.Event, EventDataWrapper> implements EventService {
    public EventServiceImpl() {
        super(EventDataWrapper.class);

        setResourceUrl(ResourceUrl.EVENT);
        setGetTotal(w -> w.getData().getTotal());
        setGetResults(w -> w.getData().getResults());
        setMapApiResourceToServiceResource(e -> new EventMapper(e).getEvent());
        setGetApiResourceId(com.tobiascode.marvelclient.rest.model.Event::getId);
    }

    @Override
    public int getTotalEvents() {
        return super.getTotalResources();
    }

    @Override
    public List<Event> getAllEvents(int totalEvents) {
        return super.getAllResources(totalEvents);
    }

    @Override
    public void getEventIdsForGivenResources(List<? extends ContainsEventResources> resources) {
        if(resources == null || resources.isEmpty()){
            return;
        }

        super.getResourceIdsForGivenResources(resources
                .stream()
                .map(EventResources::new)
                .collect(Collectors.toList()));
    }
}
