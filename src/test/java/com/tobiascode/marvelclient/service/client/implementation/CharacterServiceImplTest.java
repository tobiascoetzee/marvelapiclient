package com.tobiascode.marvelclient.service.client.implementation;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.service.client.CharacterService;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.testutil.CleanupTestFolder;
import com.tobiascode.marvelclient.testutil.JsonTestDataLoader;

public class CharacterServiceImplTest {
    private static String eventCollectionUri;
    private static String eventResourceUri;
    private static String storyCollectionUri;
    private static String storyResourceUri;
    private static final List<Integer> CHARACTER_IDS_FOR_EVENT = asList(1010370, 1009152, 1009156, 1009159, 1009165, 1009168, 1009175, 1009179, 1009186, 1009213, 1009220, 1010338, 1009239, 1009243, 1009245, 1009257, 1009262, 1009267, 1009281, 1009282, 1009299, 1009306, 1009309, 1009324, 1009337, 1009347, 1009351, 1009356, 1009362, 1009366, 1009368, 1009382, 1009385, 1009389, 1009399, 1009402, 1009407, 1009417, 1009421, 1009427, 1009448, 1009454, 1009459, 1010347, 1009469, 1011336, 1009471, 1010684, 1009477, 1009493, 1009512, 1009515, 1009523, 1009535, 1009546, 1009572, 1009573, 1009592, 1009610, 1009614, 1009629, 1009662, 1009664, 1009685, 1009718, 1009720, 1009726);
    private static final List<Integer> CHARACTER_IDS_FOR_STORY = new ArrayList<>();

    private CharacterService characterService = new CharacterServiceImpl();

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8080);

    @BeforeClass
    public static void initForAllTests() {
        eventResourceUri = ResourceUrl.EVENT + "/" + RestFile.VALID_EVENT_ID + "/characters";
        eventCollectionUri = Configuration.getProperty(Property.HOST).get() + eventResourceUri;
        storyResourceUri = ResourceUrl.STORY + "/" + RestFile.VALID_STORY_ID + "/characters";
        storyCollectionUri = Configuration.getProperty(Property.HOST).get() + storyResourceUri;

        createSingleEventCharacterResource();
        createSingleStoryCharacterResource();
        createSingleCharacterResource();
        createMultiCharacterResource();
        createSearchCharacterResource();
    }

    @Before
    public void init(){
        CleanupTestFolder.CleanFiles("testbackup");
    }

    @After
    public void cleanUp(){
        CleanupTestFolder.CleanFiles("testbackup");
    }

    @Test
    public void getTotalCharacters_should_return_value_greater_than_zero() {
        int expectedTotal = 1;

        int total = characterService.getTotalCharacters();

        assertEquals(expectedTotal, total);
    }

    @Test
    public void getAllCharacters_should_return_characters() {
        int expectedTotal = 11;

        List<Character> characters = characterService.getAllCharacters(expectedTotal);

        assertEquals(expectedTotal, characters.size());
    }

    @Test
    public void getCharacterIdsForAllEvents_should_return_all_charactersids() {
        Event event = new Event();
        event.setTitle("Acts of Vengeance!");
        event.setCharacterCollectionUri(eventCollectionUri);
        event.setAvailableCharacters(69);

        characterService.getCharacterIdsForGivenResources(Collections.singletonList(event));

        assertEquals(CHARACTER_IDS_FOR_EVENT, event.getCharacterIds());
    }

    @Test
    public void getCharacterIdsForAllStories_should_return_all_charactersids() {
        Story story = new Story();
        story.setTitle("Investigating the murder of a teenage girl, Cage suddenly learns that a three-way gang war is under way for control of the turf");
        story.setCharacterCollectionUri(storyCollectionUri);
        story.setAvailableCharacters(0);

        characterService.getCharacterIdsForGivenResources(Collections.singletonList(story));

        assertEquals(CHARACTER_IDS_FOR_STORY, story.getCharacterIds());
    }

    @Test
    public void searchForCharacter_should_return_character() {
        int expectedTotal = 1;

        List<Character> characters = characterService.searchForCharacter("Spider-Man");

        assertEquals(expectedTotal, characters.size());
    }

    @Test
    public void getAllCharacters_from_backup_should_return_characters() {
        int expectedTotal = 11;

        // Calling it twice makes it read from the backup file the second time
        List<Character> characters = characterService.getAllCharacters(expectedTotal);
        characters = characterService.getAllCharacters(expectedTotal);

        assertEquals(expectedTotal, characters.size());
    }

    @Test
    public void getCharacterIdsForAllEvents_from_backup_should_return_all_charactersids() {
        Event event = new Event();
        event.setTitle("Acts of Vengeance!");
        event.setCharacterCollectionUri(eventCollectionUri);
        event.setAvailableCharacters(69);

        // Calling it twice makes it read from the backup file the second time
        characterService.getCharacterIdsForGivenResources(Collections.singletonList(event));
        characterService.getCharacterIdsForGivenResources(Collections.singletonList(event));

        assertEquals(CHARACTER_IDS_FOR_EVENT, event.getCharacterIds());
    }

    @Test
    public void getAllCharacters_and_getCharacterIdsForAllEvents_from_backup_shouldnt_create_same_backup_file_name() {
        int expectedTotal = 11;
        Event event = new Event();
        event.setTitle("Acts of Vengeance!");
        event.setCharacterCollectionUri(eventCollectionUri);
        event.setAvailableCharacters(69);
        File allResourcesBackup = new File("./testbackup/CharacterDataWrapper_getAllResources.bck");
        File characterIdBackup = new File("./testbackup/CharacterDataWrapper_getResourceIdsForGivenResources_Event.bck");

        List<Character> characters = characterService.getAllCharacters(expectedTotal);
        // Will get a classcast exception if same filename is used.
        characterService.getCharacterIdsForGivenResources(Collections.singletonList(event));

        assertTrue(allResourcesBackup.exists());
        assertTrue(characterIdBackup.exists());
    }

    @Test
    public void getCharacterIdsForGivenResources_should_not_break_with_empty_list() throws Exception {
        characterService.getCharacterIdsForGivenResources(emptyList());
    }

    @Test
    public void getCharacterIdsForGivenResources_should_not_break_with_null_list() throws Exception {
        characterService.getCharacterIdsForGivenResources(null);
    }

    private static void createMultiCharacterResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.CHARACTER))
                .withQueryParam(Parameter.LIMIT, notMatching("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.MULTI_CHARACTER).getData().get())));
    }

    private static void createSingleCharacterResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.CHARACTER))
                .withQueryParam(Parameter.LIMIT, equalTo("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_CHARACTER).getData().get())));
    }

    private static void createSingleEventCharacterResource() {
        stubFor(get(urlPathEqualTo(eventResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_EVENT_CHARACTERS).getData().get())));
    }

    private static void createSingleStoryCharacterResource() {
        stubFor(get(urlPathEqualTo(storyResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_STORY_CHARACTERS).getData().get())));
    }

    private static void createSearchCharacterResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.CHARACTER))
                .withQueryParam(Parameter.DEFAULT_SEARCH_BY, matching("Spider-Man"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_CHARACTER).getData().get())));
    }
}