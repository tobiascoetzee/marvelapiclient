package com.tobiascode.marvelclient.service.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.ThumbNail;

public class EventMapper {
    private final Logger logger = LoggerFactory.getLogger(EventMapper.class);
    private final Event event;

    public EventMapper(com.tobiascode.marvelclient.rest.model.Event eventToConvert) {
        event = createEvent(eventToConvert);
    }

    public Event getEvent() {
        return event;
    }

    private Event createEvent(com.tobiascode.marvelclient.rest.model.Event eventToConvert) {
        logger.info("Mapping: " + eventToConvert.getTitle());

        Event mappedEvent = new Event();

        mappedEvent.setId(eventToConvert.getId());
        mappedEvent.setTitle(eventToConvert.getTitle());
        mappedEvent.setDescription(eventToConvert.getDescription());
        mappedEvent.setThumbnail(new ThumbNail(eventToConvert.getThumbnail().getPath(), eventToConvert.getThumbnail().getExtension()));
        mappedEvent.setCharacterCollectionUri(eventToConvert.getCharacters().getCollectionURI());
        mappedEvent.setAvailableCharacters(eventToConvert.getCharacters().getAvailable());
        mappedEvent.setStoryCollectionUri(eventToConvert.getStories().getCollectionURI());
        mappedEvent.setAvailableStories(eventToConvert.getStories().getAvailable());

        return mappedEvent;
    }
}
