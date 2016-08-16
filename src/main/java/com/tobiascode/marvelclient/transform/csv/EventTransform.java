package com.tobiascode.marvelclient.transform.csv;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Event;

public class EventTransform {
    private static final Logger logger = LoggerFactory.getLogger(EventTransform.class);

    private static Function<Event, List<List<Object>>> eventToSql = event -> {
        logger.info("Transforming [" + event.getTitle() + "] to Sql csv");
        List<Object> record = new ArrayList<>();

        record.add(event.getId());
        record.add(event.getTitle());
        record.add(event.getDescription());
        record.add(event.getThumbnail().getFullPath().orElse(""));

        return asList(record);
    };

    private static Function<Event, List<List<Object>>> eventToNeo4j = event -> {
        logger.info("Transforming [" + event.getTitle() + "] to Sql csv");

        List<List<Object>> records = eventToSql.apply(event);
        records.forEach(r -> r.add("Event"));

        return records;
    };

    private static Function<Event, List<List<Object>>> eventStoryToSql = event -> {
        logger.info("Transforming [" + event.getTitle() + "] storyIds to Sql csv");
        List<List<Object>> records = new ArrayList<>();
        for (int storyId : event.getStoryIds()) {
            List<Object> record = new ArrayList<>();

            record.add(event.getId());
            record.add(storyId);

            records.add(record);
        }

        return records;
    };

    private static Function<Event, List<List<Object>>> eventStoryToNeo4j = character -> {
        logger.info("Transforming [" + character.getTitle() + "] storyIds to Neo4j csv");

        List<List<Object>> records = eventStoryToSql.apply(character);
        records.forEach(r -> r.add("INCLUDED"));

        return records;
    };

    private EventTransform() {
    }

    public static Function<Event, List<List<Object>>> getEventToNeo4j() {
        return eventToNeo4j;
    }

    public static Function<Event, List<List<Object>>> getEventToSql() {
        return eventToSql;
    }

    public static Function<Event, List<List<Object>>> getEventStoryToSql() {
        return eventStoryToSql;
    }

    public static Function<Event, List<List<Object>>> getEventStoryToNeo4j() {
        return eventStoryToNeo4j;
    }
}
