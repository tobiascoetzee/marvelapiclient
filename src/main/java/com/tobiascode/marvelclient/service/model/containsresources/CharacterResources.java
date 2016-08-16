package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public class CharacterResources implements ContainsOtherResources, Serializable {
    private ContainsCharacterResources characterResources;

    public CharacterResources(ContainsCharacterResources characterResources) {
        this.characterResources = characterResources;
    }

    @Override
    public String getThisResourceName() {
        return "character";
    }

    @Override
    public String getContainingResourceName() {
        return characterResources.getResourceName();
    }

    @Override
    public String getOriginalResourceName() {
        return characterResources.getOriginalResourceName();
    }

    @Override
    public String getResourceCollectionUri() {
        return characterResources.getCharacterCollectionUri();
    }

    @Override
    public int getAvailableResource() {
        return characterResources.getAvailableCharacters();
    }

    @Override
    public void setResourceIds(List<Integer> characterIds) {
        characterResources.setCharacterIds(characterIds);
    }

    @Override
    public List<Integer> getResourceIds() {
        return characterResources.getCharacterIds();
    }
}
