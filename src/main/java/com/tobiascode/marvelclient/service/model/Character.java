package com.tobiascode.marvelclient.service.model;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;

public final class Character implements ContainsStoryResources, ContainsEventResources, Serializable {
    private int id;
    private String name;
    private String description;
    private String realName;
    private String identity;
    private String citizenship;
    private String placeOfBirth;
    private String eyeColour;
    private String hairColour;
    private double height;
    private double weight;
    private String wikiUrl;
    private ThumbNail thumbnail;
    private String eventCollectionUri;
    private int availableEvents;
    private String storyCollectionUri;
    private int availableStories;
    private List<Integer> eventIds;
    private List<Integer> storyIds;
    private List<String> teamNames;
    private List<Integer> teamIds;
    private List<Categories> categories;

    public Character() {
        eventIds = new ArrayList<>();
        storyIds = new ArrayList<>();
        teamNames = new ArrayList<>();
        teamIds = new ArrayList<>();
        categories = asList(Categories.NONE);
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
    public List<Integer> getEventIds() {
        return eventIds;
    }

    @Override
    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    @Override
    public List<Integer> getStoryIds() {
        return storyIds;
    }

    @Override
    public void setStoryIds(List<Integer> storyIds) {
        this.storyIds = storyIds;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
    }

    public List<Integer> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    @Override
    public String getResourceName() {
        return getName();
    }

    @Override
    public String getOriginalResourceName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id &&
                Double.compare(character.height, height) == 0 &&
                Double.compare(character.weight, weight) == 0 &&
                availableEvents == character.availableEvents &&
                availableStories == character.availableStories &&
                Objects.equals(name, character.name) &&
                Objects.equals(description, character.description) &&
                Objects.equals(realName, character.realName) &&
                Objects.equals(identity, character.identity) &&
                Objects.equals(citizenship, character.citizenship) &&
                Objects.equals(placeOfBirth, character.placeOfBirth) &&
                Objects.equals(eyeColour, character.eyeColour) &&
                Objects.equals(hairColour, character.hairColour) &&
                Objects.equals(wikiUrl, character.wikiUrl) &&
                Objects.equals(thumbnail, character.thumbnail) &&
                Objects.equals(eventCollectionUri, character.eventCollectionUri) &&
                Objects.equals(storyCollectionUri, character.storyCollectionUri) &&
                Objects.equals(eventIds, character.eventIds) &&
                Objects.equals(storyIds, character.storyIds) &&
                Objects.equals(teamNames, character.teamNames) &&
                Objects.equals(teamIds, character.teamIds) &&
                Objects.equals(categories, character.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, realName, identity, citizenship, placeOfBirth, eyeColour, hairColour, height, weight, wikiUrl, thumbnail, eventCollectionUri, availableEvents, storyCollectionUri, availableStories, eventIds, storyIds, teamNames, teamIds, categories);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", realName='" + realName + '\'' +
                ", identity='" + identity + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", eyeColour='" + eyeColour + '\'' +
                ", hairColour='" + hairColour + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", wikiUrl='" + wikiUrl + '\'' +
                ", thumbnail=" + thumbnail +
                ", eventCollectionUri='" + eventCollectionUri + '\'' +
                ", availableEvents=" + availableEvents +
                ", storyCollectionUri='" + storyCollectionUri + '\'' +
                ", availableStories=" + availableStories +
                ", eventIds=" + eventIds +
                ", storyIds=" + storyIds +
                ", teamNames=" + teamNames +
                ", teamIds=" + teamIds +
                ", categories=" + categories +
                '}';
    }
}
