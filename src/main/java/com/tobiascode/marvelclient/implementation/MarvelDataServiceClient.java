package com.tobiascode.marvelclient.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.tobiascode.marvelclient.MarvelDataService;
import com.tobiascode.marvelclient.enrich.Enricher;
import com.tobiascode.marvelclient.model.ToggleData;
import com.tobiascode.marvelclient.model.ToggleWrite;
import com.tobiascode.marvelclient.service.client.MarvelService;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.service.model.Team;
import com.tobiascode.marvelclient.transform.TransformToCsvFile;
import com.tobiascode.marvelclient.util.BackupRestore;
import com.tobiascode.marvelclient.util.Configuration;

public class MarvelDataServiceClient implements MarvelDataService {
    private boolean keepBackup;
    private String backupFolder;

    private ToggleData getToggle;
    private ToggleWrite writeToggle;
    private MarvelService marvelService;
    private Enricher enricher;
    private TransformToCsvFile transformToCsvFile;

    public MarvelDataServiceClient(MarvelService marvelService, Enricher enricher, TransformToCsvFile transformToCsvFile, ToggleData getToggle, ToggleWrite writeToggle) {
        this.getToggle = getToggle;
        this.writeToggle = writeToggle;
        this.marvelService = marvelService;
        this.enricher = enricher;
        this.transformToCsvFile = transformToCsvFile;

        keepBackup = Boolean.valueOf(Configuration.getProperty(Property.KEEP_BACKUP).orElse("false"));
        backupFolder = Configuration.getProperty(Property.BACKUP_FOLDER).orElse("./");
    }

    @Inject
    public MarvelDataServiceClient(MarvelService marvelService, Enricher enricher, TransformToCsvFile transformToCsvFile) {
        this(marvelService, enricher, transformToCsvFile, new ToggleData(true), new ToggleWrite(true));
    }

    @Override
    public void processCharacters() throws IOException {
        if (getToggle.isGetCharacters()) {
            int charactersNumberToGet = Integer.parseInt(Configuration.getProperty(Property.CHARACTERS_NUMBERTOGET).orElse("-1"));
            List<Character> allCharacters;
            List<Character> onlyCharacters = new ArrayList<>();

            BackupRestore backupRestoreOnlyCharacters = new BackupRestore(keepBackup, backupFolder + "OnlyCharacters.bck");
            BackupRestore backupRestoreCharacter = new BackupRestore(keepBackup, backupFolder + "MainCharacter.bck");
            BackupRestore backupRestoreTeam = new BackupRestore(keepBackup, backupFolder + "MainTeam.bck");

            if (charactersNumberToGet > -1) {
                allCharacters = marvelService.getAllMarvelCharacters(charactersNumberToGet);
            } else {
                allCharacters = marvelService.getAllMarvelCharacters();
            }

            if (getToggle.isGetCharactersEvents()) {
                marvelService.getEventIdsForGivenResources(allCharacters);
            }

            if (getToggle.isGetCharactersStories()) {
                marvelService.getStoryIdsForGivenResources(allCharacters);
            }

            if (getToggle.isEnrichCharacters()) {
                enricher.addCategories(allCharacters);
                backupRestoreCharacter.writeBackup(allCharacters.size(), allCharacters);

                List<Team> allTeams = marvelService.getOnlyTeams(allCharacters);
                backupRestoreTeam.writeBackup(allTeams.size(), allTeams);

                onlyCharacters = marvelService.getOnlyCharacters(allCharacters);
                backupRestoreOnlyCharacters.writeBackup(onlyCharacters.size(), onlyCharacters);

                enricher.enrichCharacter(onlyCharacters);

                marvelService.linkTeamsToCharacters(allCharacters, allTeams);

                backupRestoreOnlyCharacters.writeBackup(onlyCharacters.size(), onlyCharacters);
                writeTeamFiles(onlyCharacters, allTeams);
            }

            backupRestoreOnlyCharacters.writeBackup(onlyCharacters.size(), onlyCharacters);
            writeCharacterFiles(onlyCharacters);
        }
    }

    private void writeTeamFiles(List<Character> characters, List<Team> teams) throws IOException {
        if (writeToggle.isWriteTeams()) {
            transformToCsvFile.writeTeamFile(teams, "teams");
        }

        if (writeToggle.isWriteTeamsEvents()) {
            transformToCsvFile.writeTeamEventFile(teams, "teams_events");
        }

        if (writeToggle.isWriteTeamsStories()) {
            transformToCsvFile.writeTeamStoryFile(teams, "teams_stories");
        }

        if (writeToggle.isWriteCharactersTeams()) {
            transformToCsvFile.writeCharacterTeamFile(characters, "characters_teams");
        }
    }

    private void writeCharacterFiles(List<Character> characters) throws IOException {
        if (writeToggle.isWriteCharacters()) {
            transformToCsvFile.writeCharacterFile(characters, "characters");
        }

        if (getToggle.isGetCharactersStories() && writeToggle.isWriteCharactersStories()) {
            transformToCsvFile.writeCharacterStoryFile(characters, "characters_stories");
        }

        if (getToggle.isGetCharactersEvents() && writeToggle.isWriteCharactersEvents()) {
            transformToCsvFile.writeCharacterEventFile(characters, "characters_events");
        }
    }

    @Override
    public void processStories() throws IOException {
        if (getToggle.isGetStories()) {
            int storiesNumberToGet = Integer.parseInt(Configuration.getProperty(Property.STORIES_NUMBERTOGET).orElse("-1"));
            List<Story> allStories;

            if (storiesNumberToGet > -1) {
                allStories = marvelService.getAllMarvelStories(storiesNumberToGet);
            } else {
                allStories = marvelService.getAllMarvelStories();
            }

            if (writeToggle.isWriteStories()) {
                transformToCsvFile.writeStoryFile(allStories, "stories");
            }
        }
    }

    @Override
    public void processEvents() throws IOException {
        if (getToggle.isGetEvents()) {
            int eventsNumberToGet = Integer.parseInt(Configuration.getProperty(Property.EVENTS_NUMBERTOGET).orElse("-1"));
            List<Event> allEvents;

            if (eventsNumberToGet > -1) {
                allEvents = marvelService.getAllMarvelEvents(eventsNumberToGet);
            } else {
                allEvents = marvelService.getAllMarvelEvents();
            }

            processEventStories(allEvents);

            if (writeToggle.isWriteEvents()) {
                transformToCsvFile.writeEventFile(allEvents, "events");
            }
        }
    }

    private void processEventStories(List<Event> allEvents) throws IOException {
        if (getToggle.isGetEventsStories()) {
            marvelService.getStoryIdsForGivenResources(allEvents);

            if (writeToggle.isWriteEventsStories()) {
                transformToCsvFile.writeEventStoryFile(allEvents, "events_stories");
            }
        }
    }
}
