package com.tobiascode.marvelclient.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tobiascode.marvelclient.service.model.containsresources.ContainsCharacterResources;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;

public final class Event implements ContainsStoryResources, ContainsCharacterResources, Serializable {
    private int id;
    private String title;
    private String description;
    private ThumbNail thumbnail;
    private String characterCollectionUri;
    private int availableCharacters;
    private String storyCollectionUri;
    private int availableStories;
    private List<Integer> characterIds;
    private List<Integer> storyIds;

    public Event() {
        characterIds = new ArrayList<>();
        storyIds = new ArrayList<>();
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

    public ThumbNail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbNail thumbnail) {
        this.thumbnail = thumbnail;
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
    public String getStoryCollectionUri() {
        return storyCollectionUri;
    }

    public void setStoryCollectionUri(String storyCollectionUri) {
        this.storyCollectionUri = storyCollectionUri;
    }

    @Override
    public int getAvailableStories() {
        return availableStories;
    }

    public void setAvailableStories(int availableStories) {
        this.availableStories = availableStories;
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
    public List<Integer> getStoryIds() {
        return storyIds;
    }

    @Override
    public void setStoryIds(List<Integer> storyIds) {
        this.storyIds = storyIds;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                availableCharacters == event.availableCharacters &&
                availableStories == event.availableStories &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                Objects.equals(thumbnail, event.thumbnail) &&
                Objects.equals(characterCollectionUri, event.characterCollectionUri) &&
                Objects.equals(storyCollectionUri, event.storyCollectionUri) &&
                Objects.equals(characterIds, event.characterIds) &&
                Objects.equals(storyIds, event.storyIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, thumbnail, characterCollectionUri, availableCharacters, storyCollectionUri, availableStories, characterIds, storyIds);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                ", characterCollectionUri='" + characterCollectionUri + '\'' +
                ", availableCharacters=" + availableCharacters +
                ", storyCollectionUri='" + storyCollectionUri + '\'' +
                ", availableStories=" + availableStories +
                ", characterIds=" + characterIds +
                ", storyIds=" + storyIds +
                '}';
    }
}
