package com.tobiascode.marvelclient.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class MarvelCharacter {
    private int id;
    private String name;
    private String description;
    private List<CharacterUrl> urls;
    private Image thumbnail;
    private StoryList stories;
    private EventList events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CharacterUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<CharacterUrl> urls) {
        this.urls = urls;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public StoryList getStories() {
        return stories;
    }

    public void setStories(StoryList stories) {
        this.stories = stories;
    }

    public EventList getEvents() {
        return events;
    }

    public void setEvents(EventList events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", urls=" + urls +
                ", thumbnail=" + thumbnail +
                ", stories=" + stories +
                ", events=" + events +
                '}';
    }
}
