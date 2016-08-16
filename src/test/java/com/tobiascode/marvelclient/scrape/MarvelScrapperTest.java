package com.tobiascode.marvelclient.scrape;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.constants.HtmlFile;
import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.testutil.HtmlTestDataLoader;

public class MarvelScrapperTest {
    private final String CHARACTER_RESOURCE_URI = "/universe/Spider-Man";
    private final String CHARACTER_URL = "http://localhost:8080" + CHARACTER_RESOURCE_URI;
    private final String TEAM_RESOURCE_URI = "/universe/Avengers";
    private final String TEAM_URL = "http://localhost:8080" + TEAM_RESOURCE_URI;

    @Before
    public void init() {
        stubFor(get(urlMatching(CHARACTER_RESOURCE_URI))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody(new HtmlTestDataLoader(HtmlFile.CHARACTER).getData().get())));

        stubFor(get(urlMatching(TEAM_RESOURCE_URI))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody(new HtmlTestDataLoader(HtmlFile.TEAM).getData().get())));
    }

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void getCategories_for_character_should_return_categories_for_a_character() throws IOException {
        MarvelScrapper scrapper = new MarvelScrapper(CHARACTER_URL);

        List<Categories> categories = scrapper.getCategories();

        assertEquals(asList(Categories.PEOPLE, Categories.HEROES), categories);
    }

    @Test
    public void getCategories_for_team_should_return_categories_for_team() throws IOException {
        MarvelScrapper scrapper = new MarvelScrapper(TEAM_URL);

        List<Categories> categories = scrapper.getCategories();

        assertEquals(asList(Categories.TEAMS, Categories.HEROES), categories);
    }
}