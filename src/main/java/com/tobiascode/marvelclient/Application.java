package com.tobiascode.marvelclient;

import java.io.IOException;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

public class Application {
    @Inject
    private MarvelDataService marvelDataService;

    public void main(@Observes ContainerInitialized event) throws IOException {
        marvelDataService.processCharacters();
        marvelDataService.processEvents();
        marvelDataService.processStories();

        System.out.println("DONE!");
    }
}
