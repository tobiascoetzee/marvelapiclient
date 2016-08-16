package com.tobiascode.marvelclient.service.model.mapper;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.rest.model.CharacterUrl;
import com.tobiascode.marvelclient.rest.model.MarvelCharacter;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.ThumbNail;

public class CharacterMapper {
    private final Logger logger = LoggerFactory.getLogger(CharacterMapper.class);
    private final Character character;

    public CharacterMapper(MarvelCharacter marvelCharacterToConvert) {
        character = createCharacter(marvelCharacterToConvert);
    }

    public Character getCharacter() {
        return character;
    }

    private Character createCharacter(MarvelCharacter marvelCharacterToConvert) {
        logger.info("Mapping: " + marvelCharacterToConvert.getName());

        Character mappedCharacter = new Character();
        mappedCharacter.setId(marvelCharacterToConvert.getId());
        mappedCharacter.setName(marvelCharacterToConvert.getName());
        mappedCharacter.setThumbnail(new ThumbNail(marvelCharacterToConvert.getThumbnail().getPath(), marvelCharacterToConvert.getThumbnail().getExtension()));
        mappedCharacter.setWikiUrl(getWikiUrl(marvelCharacterToConvert.getUrls()).orElse(""));
        mappedCharacter.setEventCollectionUri(marvelCharacterToConvert.getEvents().getCollectionURI());
        mappedCharacter.setAvailableEvents(marvelCharacterToConvert.getEvents().getAvailable());
        mappedCharacter.setStoryCollectionUri(marvelCharacterToConvert.getStories().getCollectionURI());
        mappedCharacter.setAvailableStories(marvelCharacterToConvert.getStories().getAvailable());

        return mappedCharacter;
    }

    private static Optional<String> getWikiUrl(List<CharacterUrl> characterUrls) {
        return characterUrls.stream()
                .filter(url -> "wiki".equalsIgnoreCase(url.getType()))
                .map(CharacterUrl::getUrl)
                .findFirst();
    }
}
