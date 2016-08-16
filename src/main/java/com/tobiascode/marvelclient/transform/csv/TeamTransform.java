package com.tobiascode.marvelclient.transform.csv;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.service.model.Team;

public class TeamTransform {
    private static final Logger logger = LoggerFactory.getLogger(TeamTransform.class);

    private static Function<Team, List<List<Object>>> teamToSql = team -> {
        logger.info("Transforming [" + team.getName() + "] to Sql csv");
        List<Object> record = new ArrayList<>();

        String group = "Villains";

        if (team.getCategories().contains(Categories.HEROES)) {
            group = "Heroes";
        }

        record.add(team.getId());
        record.add(team.getName());
        record.add(team.getDescription());
        record.add(team.getThumbnail().getFullPath().orElse(""));
        record.add(group);

        return asList(record);
    };

    private static Function<Team, List<List<Object>>> teamToNeo4j = team -> {
        logger.info("Transforming [" + team.getName() + "] to Neo4j csv");

        List<List<Object>> records = teamToSql.apply(team);
        records.forEach(r -> r.add("Team"));

        return records;
    };

    private static Function<Team, List<List<Object>>> teamEventToSql = team -> {
        logger.info("Transforming [" + team.getName() + "] eventIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int eventId : team.getEventIds()) {
            List<Object> record = new ArrayList<>();

            record.add(team.getId());
            record.add(eventId);

            records.add(record);
        }

        return records;
    };

    private static Function<Team, List<List<Object>>> teamEventToNeo4j = team -> {
        logger.info("Transforming [" + team.getName() + "] eventIds to Neo4j csv");

        List<List<Object>> records = teamEventToSql.apply(team);
        records.forEach(r -> r.add("WAS_PART_OF"));

        return records;
    };

    private static Function<Team, List<List<Object>>> teamStoryToSql = team -> {
        logger.info("Transforming [" + team.getName() + "] storyIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int storyId : team.getStoryIds()) {
            List<Object> record = new ArrayList<>();

            record.add(team.getId());
            record.add(storyId);

            records.add(record);
        }

        return records;
    };

    private static Function<Team, List<List<Object>>> teamStoryToNeo4j = team -> {
        logger.info("Transforming [" + team.getName() + "] storyIds to Neo4j csv");

        List<List<Object>> records = teamStoryToSql.apply(team);
        records.forEach(r -> r.add("WAS_IN"));

        return records;
    };

    private TeamTransform() {
    }

    public static Function<Team, List<List<Object>>> getTeamToSql() {
        return teamToSql;
    }

    public static Function<Team, List<List<Object>>> getTeamToNeo4j() {
        return teamToNeo4j;
    }

    public static Function<Team, List<List<Object>>> getTeamEventToSql() {
        return teamEventToSql;
    }

    public static Function<Team, List<List<Object>>> getTeamEventToNeo4j() {
        return teamEventToNeo4j;
    }

    public static Function<Team, List<List<Object>>> getTeamStoryToSql() {
        return teamStoryToSql;
    }

    public static Function<Team, List<List<Object>>> getTeamStoryToNeo4j() {
        return teamStoryToNeo4j;
    }
}
