package com.tobiascode.marvelclient.service.client.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.rest.model.StoryDataWrapper;
import com.tobiascode.marvelclient.service.client.StoryService;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsStoryResources;
import com.tobiascode.marvelclient.service.model.containsresources.StoryResources;
import com.tobiascode.marvelclient.service.model.mapper.StoryMapper;

public class StoryServiceImpl extends CoreServiceImpl<Story, com.tobiascode.marvelclient.rest.model.Story, StoryDataWrapper> implements StoryService {
    public StoryServiceImpl() {
        super(StoryDataWrapper.class);

        setResourceUrl(ResourceUrl.STORY);
        setGetTotal(w -> w.getData().getTotal());
        setGetResults(w -> w.getData().getResults());
        setMapApiResourceToServiceResource(s -> new StoryMapper(s).getStory());
        setGetApiResourceId(com.tobiascode.marvelclient.rest.model.Story::getId);
        setDefaultOrderBy("id");
    }

    @Override
    public int getTotalStories() {
        return super.getTotalResources();
    }

    @Override
    public List<Story> getAllStories(int totalStories) {
        return super.getAllResources(totalStories);
    }

    @Override
    public void getStoryIdsForGivenResources(List<? extends ContainsStoryResources> resources) {
        if(resources == null || resources.isEmpty()){
            return;
        }

        super.getResourceIdsForGivenResources(resources
                .stream()
                .map(StoryResources::new)
                .collect(Collectors.toList()));
    }
}
