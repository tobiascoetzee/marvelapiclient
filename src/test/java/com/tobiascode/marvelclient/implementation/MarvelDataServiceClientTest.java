package com.tobiascode.marvelclient.implementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tobiascode.marvelclient.enrich.Enricher;
import com.tobiascode.marvelclient.model.ToggleData;
import com.tobiascode.marvelclient.model.ToggleWrite;
import com.tobiascode.marvelclient.service.client.MarvelService;
import com.tobiascode.marvelclient.testutil.CleanupTestFolder;
import com.tobiascode.marvelclient.transform.TransformToCsvFile;

@RunWith(MockitoJUnitRunner.class)
public class MarvelDataServiceClientTest {
    @Mock
    private MarvelService marvelService;

    @Mock
    private Enricher enricher;

    @Mock
    private TransformToCsvFile transformToCsvFile;

    private ToggleWrite toggleWrite = new ToggleWrite(true);
    private ToggleData toggleData = new ToggleData(true);

    @InjectMocks
    MarvelDataServiceClient marvelDataService = new MarvelDataServiceClient(marvelService, enricher, transformToCsvFile, toggleData, toggleWrite);

    @Before
    public void init() {
        CleanupTestFolder.CleanFiles("testbackup");
        CleanupTestFolder.CleanFiles("testimportdata");
    }

    @After
    public void cleanUp() {
        CleanupTestFolder.CleanFiles("testbackup");
        CleanupTestFolder.CleanFiles("testimportdata");
    }

    @Test
    public void processCharacters_all_true_should_call_all_methods() throws Exception {
        marvelDataService.processCharacters();

        verify(marvelService, times(1)).getAllMarvelCharacters();
        verify(marvelService, times(1)).getEventIdsForGivenResources(any());
        verify(marvelService, times(1)).getStoryIdsForGivenResources(any());
        verify(enricher, times(1)).addCategories(any());
        verify(marvelService, times(1)).getOnlyTeams(any());
        verify(marvelService, times(1)).getOnlyCharacters(any());
        verify(enricher, times(1)).enrichCharacter(any());
        verify(marvelService, times(1)).linkTeamsToCharacters(any(), any());

        verify(transformToCsvFile, times(1)).writeCharacterFile(any(), any());
        verify(transformToCsvFile, times(1)).writeCharacterEventFile(any(), any());
        verify(transformToCsvFile, times(1)).writeCharacterStoryFile(any(), any());

        verify(transformToCsvFile, times(1)).writeTeamFile(any(), any());
        verify(transformToCsvFile, times(1)).writeTeamEventFile(any(), any());
        verify(transformToCsvFile, times(1)).writeTeamStoryFile(any(), any());
        verify(transformToCsvFile, times(1)).writeCharacterTeamFile(any(), any());
    }

    @Test
    public void processCharacters_all_get_is_false_should_not_call_any_methods() throws Exception {
        toggleData.setGetCharacters(false);

        marvelDataService.processCharacters();

        verify(marvelService, times(0)).getAllMarvelCharacters();
        verify(marvelService, times(0)).getEventIdsForGivenResources(any());
        verify(marvelService, times(0)).getStoryIdsForGivenResources(any());
        verify(enricher, times(0)).addCategories(any());
        verify(marvelService, times(0)).getOnlyTeams(any());
        verify(marvelService, times(0)).getOnlyCharacters(any());
        verify(enricher, times(0)).enrichCharacter(any());
        verify(marvelService, times(0)).linkTeamsToCharacters(any(), any());

        verify(transformToCsvFile, times(0)).writeCharacterFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterStoryFile(any(), any());

        verify(transformToCsvFile, times(0)).writeTeamFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamStoryFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterTeamFile(any(), any());
    }

    @Test
    public void processCharacters_all_write_is_false_should_not_call_any_write_methods() throws Exception {
        toggleWrite.setWriteCharacters(false);
        toggleWrite.setWriteCharactersEvents(false);
        toggleWrite.setWriteCharactersStories(false);
        toggleWrite.setWriteCharactersTeams(false);
        toggleWrite.setWriteTeams(false);
        toggleWrite.setWriteTeamsEvents(false);
        toggleWrite.setWriteTeamsStories(false);

        marvelDataService.processCharacters();

        verify(marvelService, times(1)).getAllMarvelCharacters();
        verify(marvelService, times(1)).getEventIdsForGivenResources(any());
        verify(marvelService, times(1)).getStoryIdsForGivenResources(any());
        verify(enricher, times(1)).addCategories(any());
        verify(marvelService, times(1)).getOnlyTeams(any());
        verify(marvelService, times(1)).getOnlyCharacters(any());
        verify(enricher, times(1)).enrichCharacter(any());
        verify(marvelService, times(1)).linkTeamsToCharacters(any(), any());

        verify(transformToCsvFile, times(0)).writeCharacterFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterStoryFile(any(), any());

        verify(transformToCsvFile, times(0)).writeTeamFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamStoryFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterTeamFile(any(), any());
    }

    @Test
    public void processCharacters_enrich_character_is_false_should_not_call_any_enrich_or_team_methods() throws Exception {
        toggleData.setEnrichCharacters(false);

        marvelDataService.processCharacters();

        verify(marvelService, times(1)).getAllMarvelCharacters();
        verify(marvelService, times(1)).getEventIdsForGivenResources(any());
        verify(marvelService, times(1)).getStoryIdsForGivenResources(any());
        verify(enricher, times(0)).addCategories(any());
        verify(marvelService, times(0)).getOnlyTeams(any());
        verify(marvelService, times(0)).getOnlyCharacters(any());
        verify(enricher, times(0)).enrichCharacter(any());
        verify(marvelService, times(0)).linkTeamsToCharacters(any(), any());

        verify(transformToCsvFile, times(1)).writeCharacterFile(any(), any());
        verify(transformToCsvFile, times(1)).writeCharacterEventFile(any(), any());
        verify(transformToCsvFile, times(1)).writeCharacterStoryFile(any(), any());

        verify(transformToCsvFile, times(0)).writeTeamFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeTeamStoryFile(any(), any());
        verify(transformToCsvFile, times(0)).writeCharacterTeamFile(any(), any());
    }

    @Test
    public void processStories_all_true_should_call_all_methods() throws Exception {
        marvelDataService.processStories();

        verify(marvelService, times(1)).getAllMarvelStories();
        verify(transformToCsvFile, times(1)).writeStoryFile(any(), any());
    }

    @Test
    public void processStories_all_get_is_false_should_not_call_any_methods() throws Exception {
        toggleData.setGetStories(false);

        marvelDataService.processStories();

        verify(marvelService, times(0)).getAllMarvelStories();
        verify(transformToCsvFile, times(0)).writeStoryFile(any(), any());
    }

    @Test
    public void processStories_all_write_is_false_should_not_call_any_write_methods() throws Exception {
        toggleWrite.setWriteStories(false);

        marvelDataService.processStories();

        verify(marvelService, times(1)).getAllMarvelStories();
        verify(transformToCsvFile, times(0)).writeStoryFile(any(), any());
    }

    @Test
    public void processEvents_all_true_should_call_all_methods() throws Exception {
        marvelDataService.processEvents();

        verify(marvelService, times(1)).getAllMarvelEvents();
        verify(marvelService, times(1)).getStoryIdsForGivenResources(any());

        verify(transformToCsvFile, times(1)).writeEventFile(any(), any());
        verify(transformToCsvFile, times(1)).writeEventStoryFile(any(), any());
    }

    @Test
    public void processEvents_all_get_is_false_should_not_call_any_methods() throws Exception {
        toggleData.setGetEvents(false);

        marvelDataService.processEvents();

        verify(marvelService, times(0)).getAllMarvelEvents();
        verify(marvelService, times(0)).getStoryIdsForGivenResources(any());

        verify(transformToCsvFile, times(0)).writeEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeEventStoryFile(any(), any());
    }

    @Test
    public void processEvents_all_write_is_false_should_not_call_any_write_methods() throws Exception {
        toggleWrite.setWriteEvents(false);
        toggleWrite.setWriteEventsStories(false);

        marvelDataService.processEvents();

        verify(marvelService, times(1)).getAllMarvelEvents();
        verify(marvelService, times(1)).getStoryIdsForGivenResources(any());

        verify(transformToCsvFile, times(0)).writeEventFile(any(), any());
        verify(transformToCsvFile, times(0)).writeEventStoryFile(any(), any());
    }
}