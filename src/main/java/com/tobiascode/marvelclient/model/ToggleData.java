package com.tobiascode.marvelclient.model;

import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.service.constants.Property;

public class ToggleData {
    private boolean getCharacters;
    private boolean getCharactersEvents;
    private boolean getCharactersStories;
    private boolean getEvents;
    private boolean getEventsStories;
    private boolean getStories;
    private boolean enrichCharacters;

    public ToggleData(boolean loadConfiguration) {
        if (loadConfiguration) {
            initialiseGetProperties();
        }
    }

    public boolean isGetCharacters() {
        return getCharacters;
    }

    public void setGetCharacters(boolean getCharacters) {
        this.getCharacters = getCharacters;
    }

    public boolean isGetCharactersEvents() {
        return getCharactersEvents;
    }

    public void setGetCharactersEvents(boolean getCharactersEvents) {
        this.getCharactersEvents = getCharactersEvents;
    }

    public boolean isGetCharactersStories() {
        return getCharactersStories;
    }

    public void setGetCharactersStories(boolean getCharactersStories) {
        this.getCharactersStories = getCharactersStories;
    }

    public boolean isGetEvents() {
        return getEvents;
    }

    public void setGetEvents(boolean getEvents) {
        this.getEvents = getEvents;
    }

    public boolean isGetEventsStories() {
        return getEventsStories;
    }

    public void setGetEventsStories(boolean getEventsStories) {
        this.getEventsStories = getEventsStories;
    }

    public boolean isGetStories() {
        return getStories;
    }

    public void setGetStories(boolean getStories) {
        this.getStories = getStories;
    }

    public boolean isEnrichCharacters() {
        return enrichCharacters;
    }

    public void setEnrichCharacters(boolean enrichCharacters) {
        this.enrichCharacters = enrichCharacters;
    }

    private void initialiseGetProperties() {
        getCharacters = Boolean.valueOf(Configuration.getProperty(Property.GET_CHARACTERS).orElse("false"));
        getCharactersEvents = Boolean.valueOf(Configuration.getProperty(Property.GET_CHARACTERS_EVENTS).orElse("false"));
        getCharactersStories = Boolean.valueOf(Configuration.getProperty(Property.GET_CHARACTERS_STORIES).orElse("false"));

        getEvents = Boolean.valueOf(Configuration.getProperty(Property.GET_EVENTS).orElse("false"));
        getEventsStories = Boolean.valueOf(Configuration.getProperty(Property.GET_EVENTS_STORIES).orElse("false"));

        getStories = Boolean.valueOf(Configuration.getProperty(Property.GET_STORIES).orElse("false"));

        enrichCharacters = Boolean.valueOf(Configuration.getProperty(Property.ENRICH_CHARACTERS).orElse("false"));
    }
}
