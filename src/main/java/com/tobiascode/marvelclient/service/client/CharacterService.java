package com.tobiascode.marvelclient.service.client;

import java.util.List;

import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsCharacterResources;

public interface CharacterService {
    int getTotalCharacters();

    List<Character> getAllCharacters(int totalCharacters);

    List<Character> searchForCharacter(String characterName);

    void getCharacterIdsForGivenResources(List<? extends ContainsCharacterResources> resources);
}
