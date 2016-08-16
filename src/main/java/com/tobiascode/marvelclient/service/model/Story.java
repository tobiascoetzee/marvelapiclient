package com.tobiascode.marvelclient.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tobiascode.marvelclient.service.model.containsresources.ContainsCharacterResources;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;

public final class Story implements ContainsEventResources, ContainsCharacterResources, Serializable {
    private int id;
    private String title;
    private String description;
    private String type;
    private String characterCollectionUri;
    private int availableCharacters;
    private String eventCollectionUri;
    private int availableEvents;
    private List<Integer> characterIds;
    private List<Integer> eventIds;

    public Story() {
        characterIds = new ArrayList<>();
        eventIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCharacterCollectionUri() {
        return characterCollectionUri;
    }

    public void setCharacterCollectionUri(String characterCollectionUri) {
        this.characterCollectionUri = characterCollectionUri;
    }

    @Override
    public int getAvailableCharacters() {
        return availableCharacters;
    }

    public void setAvailableCharacters(int availableCharacters) {
        this.availableCharacters = availableCharacters;
    }

    @Override
    public String getResourceName() {
        return getTitle();
    }

    @Override
    public String getOriginalResourceName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getEventCollectionUri() {
        return eventCollectionUri;
    }

    public void setEventCollectionUri(String eventCollectionUri) {
        this.eventCollectionUri = eventCollectionUri;
    }

    @Override
    public int getAvailableEvents() {
        return availableEvents;
    }

    public void setAvailableEvents(int availableEvents) {
        this.availableEvents = availableEvents;
    }

    @Override
    public List<Integer> getCharacterIds() {
        return characterIds;
    }

    @Override
    public void setCharacterIds(List<Integer> characterIds) {
        this.characterIds = characterIds;
    }

    @Override
    public List<Integer> getEventIds() {
        return eventIds;
    }

    @Override
    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return id == story.id &&
                availableCharacters == story.availableCharacters &&
                availableEvents == story.availableEvents &&
                Objects.equals(title, story.title) &&
                Objects.equals(description, story.description) &&
                Objects.equals(type, story.type) &&
                Objects.equals(characterCollectionUri, story.characterCollectionUri) &&
                Objects.equals(eventCollectionUri, story.eventCollectionUri) &&
                Objects.equals(characterIds, story.characterIds) &&
                Objects.equals(eventIds, story.eventIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, type, characterCollectionUri, availableCharacters, eventCollectionUri, availableEvents, characterIds, eventIds);
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", characterCollectionUri='" + characterCollectionUri + '\'' +
                ", availableCharacters=" + availableCharacters +
                ", eventCollectionUri='" + eventCollectionUri + '\'' +
                ", availableEvents=" + availableEvents +
                ", characterIds=" + characterIds +
                ", eventIds=" + eventIds +
                '}';
    }
}
