package com.tobiascode.marvelclient.enrich;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tobiascode.marvelclient.constants.HtmlFile;
import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.testutil.DataBuilder;
import com.tobiascode.marvelclient.testutil.HtmlTestDataLoader;

public class EnricherTest {
    private final String CHARACTER_RESOURCE_URI = "/universe/.*";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Before
    public void init() {
        stubFor(get(urlMatching(CHARACTER_RESOURCE_URI))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody(new HtmlTestDataLoader(HtmlFile.CHARACTER).getData().get())));
    }

    @Test
    public void addCategories_should_add_categories() throws Exception {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        Enricher enricher = new Enricher();
        enricher.addCategories(allCharacters);

        for (Character character : allCharacters) {
            assertEquals(asList(Categories.PEOPLE, Categories.HEROES), character.getCategories());
        }
    }

    @Test
    public void enrichCharacter_should_enrich_character() throws Exception {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        Enricher enricher = new Enricher();
        enricher.enrichCharacter(allCharacters);

        for (Character character : allCharacters) {
            assertEquals("Peter Benjamin Parker", character.getRealName());
            assertEquals("Secret", character.getIdentity());
            assertEquals("U.S.A.", character.getCitizenship());
            assertEquals("Forest Hills, New York", character.getPlaceOfBirth());
            assertEquals(177.8, character.getHeight(), 0);
            assertEquals(75.749864, character.getWeight(), 0);
            assertEquals("Hazel", character.getEyeColour());
            assertEquals("Brown TEST TEST", character.getHairColour());
            assertEquals(asList("Avengers", "Outlaws"), character.getTeamNames());
        }
    }
}