package com.tobiascode.marvelclient.service.model.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.model.CharacterList;
import com.tobiascode.marvelclient.rest.model.EventList;
import com.tobiascode.marvelclient.service.model.Story;

public class StoryMapperTest {
    @Test
    public void getStory_should_map_correctly() {
        Story story = new StoryMapper(buildStoryToConvert()).getStory();

        assertEquals(buildStory(), story);
    }

    private Story buildStory() {
        Story story = new Story();

        story.setId(RestFile.VALID_STORY_ID);
        story.setTitle("Investigating the murder of a teenage girl, Cage suddenly learns that a three-way gang war is under way for control of the turf");
        story.setDescription("");
        story.setType("story");
        story.setCharacterCollectionUri("http://localhost:8080/v1/public/stories/7/characters");
        story.setAvailableCharacters(0);
        story.setEventCollectionUri("http://localhost:8080/v1/public/stories/7/events");
        story.setAvailableEvents(0);

        return story;
    }

    private com.tobiascode.marvelclient.rest.model.Story buildStoryToConvert() {
        com.tobiascode.marvelclient.rest.model.Story story = new com.tobiascode.marvelclient.rest.model.Story();
        CharacterList characterList = new CharacterList();
        EventList eventList = new EventList();

        story.setId(RestFile.VALID_STORY_ID);
        story.setTitle("Investigating the murder of a teenage girl, Cage suddenly learns that a three-way gang war is under way for control of the turf");
        story.setDescription("");
        story.setType("story");
        story.setCharacters(characterList);
        story.setEvents(eventList);

        characterList.setAvailable(0);
        characterList.setCollectionURI("http://localhost:8080/v1/public/stories/7/characters");

        eventList.setAvailable(0);
        eventList.setCollectionURI("http://localhost:8080/v1/public/stories/7/events");

        return story;
    }
}