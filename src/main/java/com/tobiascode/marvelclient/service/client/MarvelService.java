package com.tobiascode.marvelclient.service.client;

import java.util.List;

import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.service.model.Team;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsEventResources;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;

public interface MarvelService {
    List<Character> getAllMarvelCharacters();
    List<Event> getAllMarvelEvents();
    List<Story> getAllMarvelStories();
    List<Character> getAllMarvelCharacters(int maxCharactersToReturn);
    List<Event> getAllMarvelEvents(int maxEventsToReturn);
    List<Story> getAllMarvelStories(int maxStoriesToReturn);
    List<Team> getOnlyTeams(List<Character> allCharacters);
    List<Character> getOnlyCharacters(List<Character> allCharacters);
    void linkTeamsToCharacters(List<Character> allCharacters, List<Team> allTeams);
    void getEventIdsForGivenResources(List<? extends ContainsEventResources> resources);
    void getStoryIdsForGivenResources(List<? extends ContainsStoryResources> resources);
}
