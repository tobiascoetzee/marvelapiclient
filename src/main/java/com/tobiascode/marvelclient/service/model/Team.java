package com.tobiascode.marvelclient.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Team implements Serializable {
    private int id;
    private String name;
    private String description;
    private String wikiUrl;
    private ThumbNail thumbnail;
    private List<Integer> eventIds;
    private List<Integer> storyIds;
    private List<Categories> categories;

    public Team() {
        eventIds = new ArrayList<>();
        storyIds = new ArrayList<>();
        categories = new ArrayList<>();
    }

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

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public ThumbNail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbNail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Integer> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    public List<Integer> getStoryIds() {
        return storyIds;
    }

    public void setStoryIds(List<Integer> storyIds) {
        this.storyIds = storyIds;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(description, team.description) &&
                Objects.equals(wikiUrl, team.wikiUrl) &&
                Objects.equals(thumbnail, team.thumbnail) &&
                Objects.equals(eventIds, team.eventIds) &&
                Objects.equals(storyIds, team.storyIds) &&
                Objects.equals(categories, team.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, wikiUrl, thumbnail, eventIds, storyIds, categories);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", wikiUrl='" + wikiUrl + '\'' +
                ", thumbnail=" + thumbnail +
                ", eventIds=" + eventIds +
                ", storyIds=" + storyIds +
                ", categories=" + categories +
                '}';
    }
}
