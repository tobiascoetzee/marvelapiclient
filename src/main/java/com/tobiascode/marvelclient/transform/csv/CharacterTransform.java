package com.tobiascode.marvelclient.transform.csv;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.service.model.Character;

public class CharacterTransform {
    private static final Logger logger = LoggerFactory.getLogger(CharacterTransform.class);

    private static Function<Character, List<List<Object>>> characterToSql = character -> {
        logger.info("Transforming [" + character.getName() + "] to Sql csv");
        List<Object> record = new ArrayList<>();

        String group = "Villains";

        if (character.getCategories().contains(Categories.HEROES)) {
            group = "Heroes";
        }

        record.add(character.getId());
        record.add(character.getName());
        record.add(character.getThumbnail().getFullPath().orElse(""));
        record.add(character.getRealName());
        record.add(character.getIdentity());
        record.add(character.getPlaceOfBirth());
        record.add(character.getEyeColour());
        record.add(character.getHairColour());
        record.add(character.getHeight());
        record.add(character.getWeight());
        record.add(group);

        return asList(record);
    };

    private static Function<Character, List<List<Object>>> characterToNeo4j = character -> {
        logger.info("Transforming [" + character.getName() + "] to Neo4j csv");

        List<List<Object>> records = getCharacterToSql().apply(character);
        records.forEach(r -> r.add("Character"));

        return records;
    };

    private static Function<Character, List<List<Object>>> characterEventToSql = character -> {
        logger.info("Transforming [" + character.getName() + "] eventIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int eventId : character.getEventIds()) {
            List<Object> record = new ArrayList<>();

            record.add(character.getId());
            record.add(eventId);

            records.add(record);
        }

        return records;
    };

    private static Function<Character, List<List<Object>>> characterEventToNeo4j = character -> {
        logger.info("Transforming [" + character.getName() + "] eventIds to Neo4j csv");

        List<List<Object>> records = characterEventToSql.apply(character);
        records.forEach(r -> r.add("WAS_PART_OF"));

        return records;
    };

    private static Function<Character, List<List<Object>>> characterStoryToSql = character -> {
        logger.info("Transforming [" + character.getName() + "] storyIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int storyId : character.getStoryIds()) {
            List<Object> record = new ArrayList<>();

            record.add(character.getId());
            record.add(storyId);

            records.add(record);
        }

        return records;
    };

    private static Function<Character, List<List<Object>>> characterStoryToNeo4j = character -> {
        logger.info("Transforming [" + character.getName() + "] storyIds to Neo4j csv");

        List<List<Object>> records = characterStoryToSql.apply(character);
        records.forEach(r -> r.add("WAS_IN"));

        return records;
    };

    private static Function<Character, List<List<Object>>> characterTeamToSql = character -> {
        logger.info("Transforming [" + character.getName() + "] teamIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int teamId : character.getTeamIds()) {
            List<Object> record = new ArrayList<>();

            record.add(character.getId());
            record.add(teamId);

            records.add(record);
        }

        return records;
    };

    private static Function<Character, List<List<Object>>> characterTeamToNeo4j = character -> {
        logger.info("Transforming [" + character.getName() + "] teamIds to Neo4j csv");

        List<List<Object>> records = characterTeamToSql.apply(character);
        records.forEach(r -> r.add("MEMBER_OF"));

        return records;
    };

    private CharacterTransform() {
    }

    public static Function<Character, List<List<Object>>> getCharacterToNeo4j() {
        return characterToNeo4j;
    }

    public static Function<Character, List<List<Object>>> getCharacterToSql() {
        return characterToSql;
    }

    public static Function<Character, List<List<Object>>> getCharacterEventToNeo4j() {
        return characterEventToNeo4j;
    }

    public static Function<Character, List<List<Object>>> getCharacterEventToSql() {
        return characterEventToSql;
    }

    public static Function<Character, List<List<Object>>> getCharacterStoryToSql() {
        return characterStoryToSql;
    }

    public static Function<Character, List<List<Object>>> getCharacterStoryToNeo4j() {
        return characterStoryToNeo4j;
    }

    public static Function<Character, List<List<Object>>> getCharacterTeamToSql() {
        return characterTeamToSql;
    }

    public static Function<Character, List<List<Object>>> getCharacterTeamToNeo4j() {
        return characterTeamToNeo4j;
    }
}
