package com.tobiascode.marvelclient.service.client.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.client.CharacterService;
import com.tobiascode.marvelclient.service.client.EventService;
import com.tobiascode.marvelclient.service.client.MarvelService;
import com.tobiascode.marvelclient.service.client.StoryService;
import com.tobiascode.marvelclient.service.model.*;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;
import com.tobiascode.marvelclient.service.model.mapper.TeamMapper;

public class MarvelServiceImpl implements MarvelService {
    private final Logger logger = LoggerFactory.getLogger(MarvelServiceImpl.class);

    private CharacterService characterService;
    private EventService eventService;
    private StoryService storyService;

    @Inject
    public MarvelServiceImpl(CharacterService characterService, EventService eventService, StoryService storyService) {
        this.characterService = characterService;
        this.eventService = eventService;
        this.storyService = storyService;
    }

    @Override
    public List<Character> getAllMarvelCharacters() {
        int totalCharacters = characterService.getTotalCharacters();
        return getAllMarvelCharacters(totalCharacters);
    }

    @Override
    public List<Event> getAllMarvelEvents() {
        int totalEvents = eventService.getTotalEvents();
        return getAllMarvelEvents(totalEvents);
    }

    @Override
    public List<Story> getAllMarvelStories() {
        int totalStories = storyService.getTotalStories();
        return getAllMarvelStories(totalStories);
    }

    @Override
    public List<Character> getAllMarvelCharacters(int maxCharactersToReturn) {
        logger.info("Getting " + maxCharactersToReturn + " characters.");

        return characterService.getAllCharacters(maxCharactersToReturn);
    }

    @Override
    public List<Event> getAllMarvelEvents(int maxEventsToReturn) {
        logger.info("Getting " + maxEventsToReturn + " events.");

        return eventService.getAllEvents(maxEventsToReturn);
    }

    @Override
    public List<Story> getAllMarvelStories(int maxStoriesToReturn) {
        logger.info("Getting " + maxStoriesToReturn + " stories.");

        return storyService.getAllStories(maxStoriesToReturn);
    }

    @Override
    public List<Team> getOnlyTeams(List<Character> allCharacters) {
        List<Team> teams = new ArrayList<>();

        for (Character character : allCharacters) {
            if (character.getCategories().contains(Categories.TEAMS)) {
                teams.add(new TeamMapper(character).getTeam());
            }
        }

        return teams;
    }

    @Override
    public List<Character> getOnlyCharacters(List<Character> allCharacters) {
        List<Character> characters = new ArrayList<>();

        for (Character character : allCharacters) {
            if (character.getCategories().contains(Categories.PEOPLE)) {
                characters.add(character);
            }
        }

        return characters;
    }

    @Override
    public void linkTeamsToCharacters(List<Character> allCharacters, List<Team> allTeams) {
        for (Character character : allCharacters) {
            for (String teamName : character.getTeamNames()) {
                Optional<Team> team = allTeams.stream().filter(t -> t.getName().equalsIgnoreCase(teamName)).findFirst();

                if (team.isPresent()) {
                    logger.debug("Found id [" + team.get().getId() + " for: " + teamName);
                    character.getTeamIds().add(team.get().getId());
                } else {
                    logger.warn("No team record found for: " + teamName);
                }
            }
        }
    }

    @Override
    public void getEventIdsForGivenResources(List<? extends ContainsEventResources> resources) {
        eventService.getEventIdsForGivenResources(resources);
    }

    @Override
    public void getStoryIdsForGivenResources(List<? extends ContainsStoryResources> resources) {
        storyService.getStoryIdsForGivenResources(resources);
    }
}
