package com.tobiascode.marvelclient.service.client.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.rest.model.CharacterDataWrapper;
import com.tobiascode.marvelclient.rest.model.MarvelCharacter;
import com.tobiascode.marvelclient.service.client.CharacterService;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsCharacterResources;
import com.tobiascode.marvelclient.service.model.containsresources.CharacterResources;
import com.tobiascode.marvelclient.service.model.mapper.CharacterMapper;

public class CharacterServiceImpl extends CoreServiceImpl<Character, MarvelCharacter, CharacterDataWrapper> implements CharacterService {
    public CharacterServiceImpl() {
        super(CharacterDataWrapper.class);

        setResourceUrl(ResourceUrl.CHARACTER);
        setGetTotal(w -> w.getData().getTotal());
        setGetResults(w -> w.getData().getResults());
        setMapApiResourceToServiceResource(c -> new CharacterMapper(c).getCharacter());
        setGetApiResourceId(MarvelCharacter::getId);
    }

    @Override
    public int getTotalCharacters() {
        return super.getTotalResources();
    }

    @Override
    public List<Character> getAllCharacters(int totalCharacters) {
        return super.getAllResources(totalCharacters);
    }

    @Override
    public List<Character> searchForCharacter(String characterName) {
        return super.searchFor(characterName);
    }

    @Override
    public void getCharacterIdsForGivenResources(List<? extends ContainsCharacterResources> resources) {
        if(resources == null || resources.isEmpty()){
            return;
        }

        super.getResourceIdsForGivenResources(resources
                .stream()
                .map(CharacterResources::new)
                .collect(Collectors.toList()));
    }
}
