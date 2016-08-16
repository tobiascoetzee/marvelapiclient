package com.tobiascode.marvelclient.rest.client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.constants.RestFile;
import com.tobiascode.marvelclient.rest.constants.ResourceUrl;
import com.tobiascode.marvelclient.rest.model.CharacterDataWrapper;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.testutil.JsonTestDataLoader;

public class ApiClientTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    private String resourceUri;

    @Before
    public void init() {
        resourceUri = Configuration.getProperty(Property.HOST).get() + ResourceUrl.CHARACTER;
    }

    @Test
    public void get_with_valid_request_should_return_data() {
        createResourceWithValidResponse();

        ApiClient<CharacterDataWrapper> apiClient = new ApiClient<>();
        Optional<CharacterDataWrapper> dataWrapper = apiClient.get(resourceUri, new HashMap<>(), CharacterDataWrapper.class);

        assertTrue(dataWrapper.isPresent());
    }

    @Test
    public void get_with_invalid_request_should_return_empty_optional() {
        createResourceWithInvalidResponse();

        ApiClient<CharacterDataWrapper> apiClient = new ApiClient<>();
        Optional<CharacterDataWrapper> dataWrapper = apiClient.get(resourceUri, new HashMap<>(), CharacterDataWrapper.class);

        assertFalse(dataWrapper.isPresent());
    }

    private void createResourceWithValidResponse() {
        stubFor(get(urlMatching(ResourceUrl.CHARACTER + ".*"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(new JsonTestDataLoader(RestFile.SINGLE_CHARACTER).getData().get())));
    }

    private void createResourceWithInvalidResponse() {
        stubFor(get(urlMatching(ResourceUrl.CHARACTER + ".*"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(409)));
    }
}