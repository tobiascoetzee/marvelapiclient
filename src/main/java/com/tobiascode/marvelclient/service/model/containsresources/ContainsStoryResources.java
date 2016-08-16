package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public interface ContainsStoryResources extends Serializable {
    String getResourceName();

    String getOriginalResourceName();

    String getStoryCollectionUri();

    int getAvailableStories();

    List<Integer> getStoryIds();

    void setStoryIds(List<Integer> storyIds);
}
