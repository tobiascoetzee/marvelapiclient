package com.tobiascode.marvelclient.scrape;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.constants.HtmlFile;
import com.tobiascode.marvelclient.testutil.HtmlTestDataLoader;

public class CharacterScrapperTest {
    private final String CHARACTER_RESOURCE_URI = "/universe/Spider-Man";
    private final String CHARACTER_URL = "http://localhost:8080" + CHARACTER_RESOURCE_URI;
    private CharacterScrapper scrapper;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Before
    public void init() {
        scrapper = new CharacterScrapper(CHARACTER_URL);

        stubFor(get(urlMatching(CHARACTER_RESOURCE_URI))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody(new HtmlTestDataLoader(HtmlFile.CHARACTER).getData().get())));
    }

    @Test
    public void getRealName_should_return_realname_for_a_character() throws IOException {
        String value = scrapper.getRealName();

        assertEquals("Peter Benjamin Parker", value);
    }

    @Test
    public void getIdentity_should_return_identity_for_a_character() throws IOException {
        String value = scrapper.getIdentity();

        assertEquals("Secret", value);
    }

    @Test
    public void getCitizenship_should_return_citizenship_for_a_character() throws IOException {
        String value = scrapper.getCitizenship();

        assertEquals("U.S.A.", value);
    }

    @Test
    public void getPlaceOfBirth_should_return_place_of_birth_for_a_character() throws IOException {
        String value = scrapper.getPlaceOfBirth();

        assertEquals("Forest Hills, New York", value);
    }

    @Test
    public void getHeight_should_return_height_for_a_character() throws IOException {
        double value = scrapper.getHeight();

        assertEquals(177.8, value, 0);
    }

    @Test
    public void getWeight_should_return_weight_for_a_character() throws IOException {
        double value = scrapper.getWeight();

        assertEquals(75.749864, value, 0);
    }

    @Test
    public void getEyes_should_return_eye_color_for_a_character() throws IOException {
        String value = scrapper.getEyes();

        assertEquals("Hazel", value);
    }

    @Test
    public void getHair_should_return_hair_color_for_a_character() throws IOException {
        String value = scrapper.getHair();

        assertEquals("Brown TEST TEST", value);
    }

    @Test
    public void getTeams_should_return_teams_for_a_character() throws IOException {
        List<String> value = scrapper.getTeams();

        assertEquals(asList("Avengers", "Outlaws"), value);
    }
}