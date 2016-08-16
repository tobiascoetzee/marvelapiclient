package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public class StoryResources implements ContainsOtherResources, Serializable {
    private ContainsStoryResources storyResources;

    public StoryResources(ContainsStoryResources storyResources) {
        this.storyResources = storyResources;
    }

    @Override
    public String getThisResourceName() {
        return "story";
    }

    @Override
    public String getContainingResourceName() {
        return storyResources.getResourceName();
    }

    @Override
    public String getOriginalResourceName() {
        return storyResources.getOriginalResourceName();
    }

    @Override
    public String getResourceCollectionUri() {
        return storyResources.getStoryCollectionUri();
    }

    @Override
    public int getAvailableResource() {
        return storyResources.getAvailableStories();
    }

    @Override
    public void setResourceIds(List<Integer> storyIds) {
        storyResources.setStoryIds(storyIds);
    }

    @Override
    public List<Integer> getResourceIds() {
        return storyResources.getStoryIds();
    }
}
