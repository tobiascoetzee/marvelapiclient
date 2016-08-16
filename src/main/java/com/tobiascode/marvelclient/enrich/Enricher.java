package com.tobiascode.marvelclient.enrich;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.scrape.CharacterScrapper;
import com.tobiascode.marvelclient.scrape.MarvelScrapper;
import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.service.model.Character;

public class Enricher {
    private final Logger logger = LoggerFactory.getLogger(Enricher.class);

    public void addCategories(List<Character> allCharacters) {
        for (Character character : allCharacters) {
            if (!character.getWikiUrl().isEmpty()) {
                logger.info("Checking for categories for: " + character.getName());

                if (character.getCategories().contains(Categories.NONE)) {
                    MarvelScrapper scrapper = new MarvelScrapper(character.getWikiUrl());
                    List<Categories> categories = scrapper.getCategories();

                    if (!categories.isEmpty()) {
                        character.setCategories(categories);
                    }

                } else {
                    logger.info("Skipping [" + character.getName() + "], already has categories");
                }
            } else {
                logger.info("No wikiUrl for: " + character.getName());
            }
        }
    }

    public void enrichCharacter(List<Character> allCharacters) {
        for (Character character : allCharacters) {
            if (!character.getWikiUrl().isEmpty()) {
                logger.info("Enriching: " + character.getName());

                CharacterScrapper characterScrapper = new CharacterScrapper(character.getWikiUrl());

                character.setRealName(characterScrapper.getRealName());
                character.setIdentity(characterScrapper.getIdentity());
                character.setCitizenship(characterScrapper.getCitizenship());
                character.setPlaceOfBirth(characterScrapper.getPlaceOfBirth());
                character.setHeight(characterScrapper.getHeight());
                character.setWeight(characterScrapper.getWeight());
                character.setEyeColour(characterScrapper.getEyes());
                character.setHairColour(characterScrapper.getHair());
                character.setTeamNames(characterScrapper.getTeams());
            } else {
                logger.info("No wikiUrl for: " + character.getName());
            }
        }
    }
}
