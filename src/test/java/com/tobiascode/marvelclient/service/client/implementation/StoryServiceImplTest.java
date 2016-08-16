package com.tobiascode.marvelclient.service.client.implementation;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.service.client.StoryService;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.testutil.CleanupTestFolder;
import com.tobiascode.marvelclient.testutil.JsonTestDataLoader;

public class StoryServiceImplTest {
    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8080);

    private static String characterCollectionUri;
    private static String characterResourceUri;
    private static String eventCollectionUri;
    private static String eventResourceUri;
    private final static List<Integer> STORY_IDS_FOR_CHARACTER = asList(483, 486, 487, 499, 599, 805, 824, 838, 842, 867, 1018, 1019, 1020, 1021, 1022, 1023, 1076, 1125, 1126, 1134);
    private final static List<Integer> STORY_IDS_FOR_EVENT = asList(12960, 12961, 12962, 12963, 12964, 12965, 14056, 14057, 14058, 14059);

    private StoryService storyService = new StoryServiceImpl();

    @BeforeClass
    public static void initForAllTests() {
        characterResourceUri = ResourceUrl.CHARACTER + "/" + RestFile.VALID_CHARACTER_ID + "/stories";
        characterCollectionUri = Configuration.getProperty(Property.HOST).get() + characterResourceUri;
        eventResourceUri = ResourceUrl.EVENT + "/" + RestFile.VALID_EVENT_ID + "/stories";
        eventCollectionUri = Configuration.getProperty(Property.HOST).get() + eventResourceUri;

        createSingleCharacterStoryResource();
        createSingleEventStoryResource();
        createSingleStoryResource();
        createMultiStoryResource();
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
    public void getStoryIdsForAllCharacters_should_return_all_storyids() throws Exception {
        Character character = new Character();
        character.setName("Spider-Man");
        character.setStoryCollectionUri(characterCollectionUri);
        character.setAvailableStories(STORY_IDS_FOR_CHARACTER.size());

        storyService.getStoryIdsForGivenResources(singletonList(character));

        assertEquals(STORY_IDS_FOR_CHARACTER, character.getStoryIds());
    }

    @Test
    public void getStoryIdsForAllEvents_should_return_all_storyids() throws Exception {
        Event event = new Event();
        event.setTitle("Acts of Vengeance!");
        event.setStoryCollectionUri(eventCollectionUri);
        event.setAvailableStories(STORY_IDS_FOR_EVENT.size());

        storyService.getStoryIdsForGivenResources(singletonList(event));

        assertEquals(STORY_IDS_FOR_EVENT, event.getStoryIds());
    }

    @Test
    public void getTotalStories_should_return_value_greater_than_zero() {
        int expectedTotal = 83203;

        int total = storyService.getTotalStories();

        assertEquals(expectedTotal, total);
    }

    @Test
    public void getAllStories_should_return_stories() {
        int expectedTotal = 10;

        List<Story> stories = storyService.getAllStories(expectedTotal);

        assertEquals(expectedTotal, stories.size());
    }

    @Test
    public void getStoryIdsForAllCharacters_and_getStoryIdsForAllEvents_should_not_use_the_same_backup_file_name() throws Exception {
        Character character = new Character();
        character.setName("Spider-Man");
        character.setStoryCollectionUri(characterCollectionUri);
        character.setAvailableStories(STORY_IDS_FOR_CHARACTER.size());

        Event event = new Event();
        event.setTitle("Acts of Vengeance!");
        event.setStoryCollectionUri(eventCollectionUri);
        event.setAvailableStories(STORY_IDS_FOR_EVENT.size());

        File eventIdBackup = new File("./testbackup/StoryDataWrapper_getResourceIdsForGivenResources_Event.bck");
        File characterIdBackup = new File("./testbackup/StoryDataWrapper_getResourceIdsForGivenResources_Character.bck");

        storyService.getStoryIdsForGivenResources(singletonList(character));
        // Will get a NoSuchElementException if using the same filename
        storyService.getStoryIdsForGivenResources(singletonList(event));

        assertTrue(eventIdBackup.exists());
        assertTrue(characterIdBackup.exists());
    }

    @Test
    public void getStoryIdsForGivenResources_should_not_break_with_empty_list() throws Exception {
        storyService.getStoryIdsForGivenResources(emptyList());
    }

    @Test
    public void getStoryIdsForGivenResources_should_not_break_with_null_list() throws Exception {
        storyService.getStoryIdsForGivenResources(null);
    }

    private static void createSingleCharacterStoryResource() {
        stubFor(get(urlPathEqualTo(characterResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_CHARACTER_STORIES).getData().get())));
    }

    private static void createSingleEventStoryResource() {
        stubFor(get(urlPathEqualTo(eventResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_EVENT_STORIES).getData().get())));
    }

    private static void createSingleStoryResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.STORY))
                .withQueryParam(Parameter.LIMIT, equalTo("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_STORY).getData().get())));
    }

    private static void createMultiStoryResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.STORY))
                .withQueryParam(Parameter.LIMIT, notMatching("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.MULTI_STORY).getData().get())));
    }
}