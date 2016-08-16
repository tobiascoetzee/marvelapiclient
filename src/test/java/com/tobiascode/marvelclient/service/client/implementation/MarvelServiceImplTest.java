package com.tobiascode.marvelclient.service.client.implementation;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tobiascode.marvelclient.service.client.CharacterService;
import com.tobiascode.marvelclient.service.client.EventService;
import com.tobiascode.marvelclient.service.client.MarvelService;
import com.tobiascode.marvelclient.service.client.StoryService;
import com.tobiascode.marvelclient.service.model.*;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.testutil.DataBuilder;

@RunWith(MockitoJUnitRunner.class)
public class MarvelServiceImplTest {
    @Mock
    private CharacterService characterService;

    @Mock
    private EventService eventService;

    @Mock
    private StoryService storyService;

    @InjectMocks
    private MarvelService marvelService = new MarvelServiceImpl(characterService, eventService, storyService);

    @Test
    public void getAllMarvelCharacters_should_return_all_characters() {
        int expectedTotal = 3;
        when(characterService.getTotalCharacters()).thenReturn(expectedTotal);
        when(characterService.getAllCharacters(expectedTotal)).thenReturn(asList(new Character(), new Character(), new Character()));

        List<Character> allCharacters = marvelService.getAllMarvelCharacters();

        assertEquals(expectedTotal, allCharacters.size());
    }

    @Test
    public void getAllMarvelCharacters_should_return_only_max_number_requested() {
        int expectedTotal = 1;
        when(characterService.getAllCharacters(expectedTotal)).thenReturn(singletonList(new Character()));

        List<Character> allCharacters = marvelService.getAllMarvelCharacters(expectedTotal);

        assertEquals(expectedTotal, allCharacters.size());
        assertEquals(expectedTotal, allCharacters.size());
    }

    @Test
    public void getAllMarvelEvents_should_return_all_events() {
        int expectedTotal = 3;
        when(eventService.getTotalEvents()).thenReturn(expectedTotal);
        when(eventService.getAllEvents(expectedTotal)).thenReturn(asList(new Event(), new Event(), new Event()));

        List<Event> events = marvelService.getAllMarvelEvents();

        assertEquals(expectedTotal, events.size());
    }

    @Test
    public void getAllMarvelEvents_should_return_only_max_number_requested() {
        int expectedTotal = 1;
        when(eventService.getAllEvents(expectedTotal)).thenReturn(singletonList(new Event()));

        List<Event> events = marvelService.getAllMarvelEvents(expectedTotal);

        assertEquals(expectedTotal, events.size());
        assertEquals(expectedTotal, events.size());
    }

    @Test
    public void getAllMarvelStories_should_return_all_stories() {
        int expectedTotal = 3;
        when(storyService.getTotalStories()).thenReturn(expectedTotal);
        when(storyService.getAllStories(expectedTotal)).thenReturn(asList(new Story(), new Story(), new Story()));

        List<Story> events = marvelService.getAllMarvelStories();

        assertEquals(expectedTotal, events.size());
    }

    @Test
    public void getAllMarvelStories_should_return_only_max_number_requested() {
        int expectedTotal = 1;
        when(storyService.getAllStories(expectedTotal)).thenReturn(asList(new Story()));

        List<Story> events = marvelService.getAllMarvelStories(expectedTotal);

        assertEquals(expectedTotal, events.size());
    }

    @Test
    public void getOnlyTeams_should_only_return_teams() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        addCategories(allCharacters);

        List<Team> teams = marvelService.getOnlyTeams(allCharacters);

        assertEquals(1, teams.size());
    }

    @Test
    public void getOnlyCharacters_should_only_return_characters() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        addCategories(allCharacters);

        List<Character> characters = marvelService.getOnlyCharacters(allCharacters);

        assertEquals(2, characters.size());
    }

    @Test
    public void linkTeamsToCharacters_should_link_get_teamids(){
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        List<Team> allTeams = DataBuilder.buildTeamList();

        allCharacters.get(0).setTeamNames(singletonList(allTeams.get(0).getName()));
        allCharacters.get(1).setTeamNames(singletonList(allTeams.get(1).getName()));
        allCharacters.get(2).setTeamNames(singletonList(allTeams.get(2).getName()));

        marvelService.linkTeamsToCharacters(allCharacters,allTeams);

        assertEquals(allTeams.get(0).getId(), allCharacters.get(0).getTeamIds().get(0).intValue());
        assertEquals(allTeams.get(1).getId(), allCharacters.get(1).getTeamIds().get(0).intValue());
        assertEquals(allTeams.get(2).getId(), allCharacters.get(2).getTeamIds().get(0).intValue());
    }

    @Test
    public void getEventIdsForGivenResources_should_be_successful(){
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        int expectedTotal = 3;

        marvelService.getEventIdsForGivenResources(allCharacters);

        assertEquals(expectedTotal, allCharacters.size());
    }

    @Test
    public void getStoryIdsForGivenResources_should_be_successful(){
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        int expectedTotal = 3;

        marvelService.getStoryIdsForGivenResources(allCharacters);

        assertEquals(expectedTotal, allCharacters.size());
    }

    private void addCategories(List<Character> allCharacters) {
        allCharacters.get(0).setCategories(asList(Categories.TEAMS, Categories.HEROES));
        allCharacters.get(1).setCategories(asList(Categories.PEOPLE, Categories.HEROES));
        allCharacters.get(2).setCategories(asList(Categories.PEOPLE, Categories.HEROES));
    }}