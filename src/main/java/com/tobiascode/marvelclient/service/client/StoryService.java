package com.tobiascode.marvelclient.service.client;

import java.util.List;

import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;
import com.tobiascode.marvelclient.service.model.Story;

public interface StoryService {
    int getTotalStories();

    List<Story> getAllStories(int totalStories);

    void getStoryIdsForGivenResources(List<? extends ContainsStoryResources> resources);
}
