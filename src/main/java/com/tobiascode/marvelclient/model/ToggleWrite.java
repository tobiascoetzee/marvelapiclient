package com.tobiascode.marvelclient.model;

import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.service.constants.Property;

public class ToggleWrite {
    private boolean writeCharacters;
    private boolean writeCharactersTeams;
    private boolean writeCharactersEvents;
    private boolean writeCharactersStories;
    private boolean writeTeams;
    private boolean writeTeamsEvents;
    private boolean writeTeamsStories;
    private boolean writeEvents;
    private boolean writeEventsStories;
    private boolean writeStories;

    public ToggleWrite(boolean loadConfiguration) {
        if(loadConfiguration){
            initialiseWriteProperties();
        }
    }

    public boolean isWriteCharacters() {
        return writeCharacters;
    }

    public void setWriteCharacters(boolean writeCharacters) {
        this.writeCharacters = writeCharacters;
    }

    public boolean isWriteCharactersTeams() {
        return writeCharactersTeams;
    }

    public void setWriteCharactersTeams(boolean writeCharactersTeams) {
        this.writeCharactersTeams = writeCharactersTeams;
    }

    public boolean isWriteCharactersEvents() {
        return writeCharactersEvents;
    }

    public void setWriteCharactersEvents(boolean writeCharactersEvents) {
        this.writeCharactersEvents = writeCharactersEvents;
    }

    public boolean isWriteCharactersStories() {
        return writeCharactersStories;
    }

    public void setWriteCharactersStories(boolean writeCharactersStories) {
        this.writeCharactersStories = writeCharactersStories;
    }

    public boolean isWriteTeams() {
        return writeTeams;
    }

    public void setWriteTeams(boolean writeTeams) {
        this.writeTeams = writeTeams;
    }

    public boolean isWriteTeamsEvents() {
        return writeTeamsEvents;
    }

    public void setWriteTeamsEvents(boolean writeTeamsEvents) {
        this.writeTeamsEvents = writeTeamsEvents;
    }

    public boolean isWriteTeamsStories() {
        return writeTeamsStories;
    }

    public void setWriteTeamsStories(boolean writeTeamsStories) {
        this.writeTeamsStories = writeTeamsStories;
    }

    public boolean isWriteEvents() {
        return writeEvents;
    }

    public void setWriteEvents(boolean writeEvents) {
        this.writeEvents = writeEvents;
    }

    public boolean isWriteEventsStories() {
        return writeEventsStories;
    }

    public void setWriteEventsStories(boolean writeEventsStories) {
        this.writeEventsStories = writeEventsStories;
    }

    public boolean isWriteStories() {
        return writeStories;
    }

    public void setWriteStories(boolean writeStories) {
        this.writeStories = writeStories;
    }

    private void initialiseWriteProperties() {
        writeCharacters = Boolean.valueOf(Configuration.getProperty(Property.WRITE_CHARACTERS).orElse("false"));
        writeCharactersTeams = Boolean.valueOf(Configuration.getProperty(Property.WRITE_CHARACTERS_TEAMS).orElse("false"));
        writeCharactersEvents = Boolean.valueOf(Configuration.getProperty(Property.WRITE_CHARACTERS_EVENTS).orElse("false"));
        writeCharactersStories = Boolean.valueOf(Configuration.getProperty(Property.WRITE_CHARACTERS_STORIES).orElse("false"));

        writeTeams = Boolean.valueOf(Configuration.getProperty(Property.WRITE_TEAMS).orElse("false"));
        writeTeamsEvents = Boolean.valueOf(Configuration.getProperty(Property.WRITE_TEAMS_EVENTS).orElse("false"));
        writeTeamsStories = Boolean.valueOf(Configuration.getProperty(Property.WRITE_TEAMS_STORIES).orElse("false"));

        writeEvents = Boolean.valueOf(Configuration.getProperty(Property.WRITE_EVENTS).orElse("false"));
        writeEventsStories = Boolean.valueOf(Configuration.getProperty(Property.WRITE_EVENTS_STORIES).orElse("false"));

        writeStories = Boolean.valueOf(Configuration.getProperty(Property.WRITE_STORIES).orElse("false"));
    }
}
