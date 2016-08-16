package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public interface ContainsCharacterResources extends Serializable{
    String getResourceName();

    String getOriginalResourceName();

    String getCharacterCollectionUri();

    int getAvailableCharacters();

    List<Integer> getCharacterIds();

    void setCharacterIds(List<Integer> characterIds);
}
