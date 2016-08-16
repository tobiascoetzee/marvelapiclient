package com.tobiascode.marvelclient.service.client.implementation;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.testutil.CleanupTestFolder;
import com.tobiascode.marvelclient.testutil.JsonTestDataLoader;

public class EventServiceImplTest {
    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8080);

    private static String characterCollectionUri;
    private static String characterResourceUri;
    private static String storyCollectionUri;
    private static String storyResourceUri;
    private final static List<Integer> EVENT_IDS_FOR_CHARACTER = asList(116, 314, 233, 234, 310, 296, 238, 318, 240, 246, 302, 251, 252, 253, 255, 258, 151, 37, 154, 266);
    private static final List<Integer> EVENT_IDS_FOR_STORY = new ArrayList<>();

    private EventServiceImpl eventService = new EventServiceImpl();

    @BeforeClass
    public static void initForAllTests() {
        characterResourceUri = ResourceUrl.CHARACTER + "/" + RestFile.VALID_CHARACTER_ID + "/events";
        characterCollectionUri = Configuration.getProperty(Property.HOST).get() + characterResourceUri;
        storyResourceUri = ResourceUrl.CHARACTER + "/" + RestFile.VALID_STORY_ID + "/events";
        storyCollectionUri = Configuration.getProperty(Property.HOST).get() + storyResourceUri;

        createSingleCharacterEventResource();
        createSingleEventResource();
        createMultiEventResource();
        createSingleStoryEventResource();
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
    public void getTotalEvents_should_return_value_greater_than_zero(){
        int expectedTotal = 69;

        int result = eventService.getTotalEvents();

        assertEquals(expectedTotal,result);
    }

    @Test
    public void getAllEvents_should_return_events(){
        int expectedTotal = 10;

        List<Event> events = eventService.getAllEvents(expectedTotal);

        assertEquals(expectedTotal, events.size());
    }

    @Test
    public void getEventIdsForAllCharacters_should_return_all_eventids() throws Exception {
        Character character = new Character();
        character.setName("Spider-Man");
        character.setEventCollectionUri(characterCollectionUri);
        character.setAvailableEvents(EVENT_IDS_FOR_CHARACTER.size());

        eventService.getEventIdsForGivenResources(singletonList(character));

        assertEquals(EVENT_IDS_FOR_CHARACTER, character.getEventIds());
    }

    @Test
    public void getEventIdsForAllStories_should_return_all_eventids() throws Exception {
        Story story = new Story();
        story.setTitle("Investigating the murder of a teenage girl, Cage suddenly learns that a three-way gang war is under way for control of the turf");
        story.setCharacterCollectionUri(storyCollectionUri);
        story.setAvailableCharacters(0);

        eventService.getEventIdsForGivenResources(singletonList(story));

        assertEquals(EVENT_IDS_FOR_STORY, story.getEventIds());
    }

    @Test
    public void getEventIdsForGivenResources_should_not_break_with_empty_list() throws Exception {
        eventService.getEventIdsForGivenResources(emptyList());
    }

    @Test
    public void getEventIdsForGivenResources_should_not_break_with_null_list() throws Exception {
        eventService.getEventIdsForGivenResources(null);
    }

    private static void createSingleCharacterEventResource() {
        stubFor(get(urlPathEqualTo(characterResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_CHARACTER_EVENTS).getData().get())));
    }

    private static void createSingleStoryEventResource() {
        stubFor(get(urlPathEqualTo(storyResourceUri))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_STORY_EVENTS).getData().get())));
    }

    private static void createSingleEventResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.EVENT))
                .withQueryParam(Parameter.LIMIT, equalTo("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_EVENT).getData().get())));
    }

    private static void createMultiEventResource() {
        stubFor(get(urlPathEqualTo(ResourceUrl.EVENT))
                .withQueryParam(Parameter.LIMIT, notMatching("1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.MULTI_EVENT).getData().get())));
    }
}