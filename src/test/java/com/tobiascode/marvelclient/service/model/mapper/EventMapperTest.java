package com.tobiascode.marvelclient.service.model.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.model.CharacterList;
import com.tobiascode.marvelclient.rest.model.Image;
import com.tobiascode.marvelclient.rest.model.StoryList;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.ThumbNail;

public class EventMapperTest {
    @Test
    public void getEvent_should_map_correctly() {
        Event event = new EventMapper(buildEventToConvert()).getEvent();

        assertEquals(buildEvent(), event);
    }

    private static Event buildEvent() {
        Event event = new Event();

        event.setId(RestFile.VALID_EVENT_ID);
        event.setTitle("Acts of Vengeance!");
        event.setDescription("Loki sets about convincing the super-villains of Earth to attack heroes other than those they normally fight in an attempt to destroy the Avengers to absolve his guilt over inadvertently creating the team in the first place.");
        event.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b", "jpg"));
        event.setCharacterCollectionUri("http://localhost:8080/v1/public/events/116/characters");
        event.setAvailableCharacters(67);
        event.setStoryCollectionUri("http://localhost:8080/v1/public/events/116/stories");
        event.setAvailableStories(145);

        return event;
    }

    private static com.tobiascode.marvelclient.rest.model.Event buildEventToConvert() {
        com.tobiascode.marvelclient.rest.model.Event event = new com.tobiascode.marvelclient.rest.model.Event();
        CharacterList characterList = new CharacterList();
        StoryList storyList = new StoryList();
        Image image = new Image();

        event.setId(RestFile.VALID_EVENT_ID);
        event.setTitle("Acts of Vengeance!");
        event.setDescription("Loki sets about convincing the super-villains of Earth to attack heroes other than those they normally fight in an attempt to destroy the Avengers to absolve his guilt over inadvertently creating the team in the first place.");
        event.setThumbnail(image);
        event.setCharacters(characterList);
        event.setStories(storyList);

        characterList.setCollectionURI("http://localhost:8080/v1/public/events/116/characters");
        characterList.setAvailable(67);

        storyList.setCollectionURI("http://localhost:8080/v1/public/events/116/stories");
        storyList.setAvailable(145);

        image.setPath("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b");
        image.setExtension("jpg");

        return event;
    }
}