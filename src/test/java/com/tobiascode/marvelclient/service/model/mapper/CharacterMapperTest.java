package com.tobiascode.marvelclient.service.model.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.model.*;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.ThumbNail;

public class CharacterMapperTest {
    @Test
    public void getCharacter_should_map_correctly() {
        Character character = new CharacterMapper(buildCharacterToConvert()).getCharacter();

        assertEquals(buildCharacter(), character);
    }

    private Character buildCharacter() {
        Character character = new Character();

        character.setId(1009610);
        character.setName("Spider-Man");
        character.setWikiUrl("http://localhost:8080/universe/Spider-Man_(Peter_Parker)?utm_campaign=apiRef&utm_source=b78feb5c7c46161271cf3a6a38f1d88d");
        character.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b", "jpg"));
        character.setEventCollectionUri("http://localhost:8080/v1/public/characters/1009610/events");
        character.setAvailableEvents(31);
        character.setStoryCollectionUri("http://localhost:8080/v1/public/characters/1009610/stories");
        character.setAvailableStories(20);

        return character;
    }

    private MarvelCharacter buildCharacterToConvert() {
        MarvelCharacter marvelCharacter = new MarvelCharacter();
        Image image = new Image();
        CharacterUrl characterUrl = new CharacterUrl();
        EventList eventList = new EventList();
        StoryList storyList = new StoryList();

        marvelCharacter.setId(RestFile.VALID_CHARACTER_ID);
        marvelCharacter.setName("Spider-Man");
        marvelCharacter.setThumbnail(image);
        marvelCharacter.setUrls(Collections.singletonList(characterUrl));
        marvelCharacter.setEvents(eventList);
        marvelCharacter.setStories(storyList);

        image.setPath("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b");
        image.setExtension("jpg");

        characterUrl.setType("wiki");
        characterUrl.setUrl("http://localhost:8080/universe/Spider-Man_(Peter_Parker)?utm_campaign=apiRef&utm_source=b78feb5c7c46161271cf3a6a38f1d88d");

        eventList.setCollectionURI("http://localhost:8080/v1/public/characters/1009610/events");
        eventList.setAvailable(31);

        storyList.setCollectionURI("http://localhost:8080/v1/public/characters/1009610/stories");
        storyList.setAvailable(20);

        return marvelCharacter;
    }
}