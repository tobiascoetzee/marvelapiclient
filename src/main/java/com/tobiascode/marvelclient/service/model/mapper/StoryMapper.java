package com.tobiascode.marvelclient.service.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Story;

public class StoryMapper {
    private final Logger logger = LoggerFactory.getLogger(StoryMapper.class);
    private final Story story;

    public StoryMapper(com.tobiascode.marvelclient.rest.model.Story storyToConvert) {
        story = createStory(storyToConvert);
    }

    public Story getStory() {
        return story;
    }

    private Story createStory(com.tobiascode.marvelclient.rest.model.Story storyToConvert) {
        logger.info("Mapping: " + storyToConvert.getTitle());

        Story mappedStory = new Story();

        mappedStory.setId(storyToConvert.getId());
        mappedStory.setTitle(storyToConvert.getTitle());
        mappedStory.setDescription(storyToConvert.getDescription());
        mappedStory.setType(storyToConvert.getType());
        mappedStory.setCharacterCollectionUri(storyToConvert.getCharacters().getCollectionURI());
        mappedStory.setAvailableCharacters(storyToConvert.getCharacters().getAvailable());
        mappedStory.setEventCollectionUri(storyToConvert.getEvents().getCollectionURI());
        mappedStory.setAvailableEvents(storyToConvert.getEvents().getAvailable());

        return mappedStory;
    }
}
