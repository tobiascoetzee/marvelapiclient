package com.tobiascode.marvelclient.transform.csv;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Story;

public class StoryTransform {
    private static final Logger logger = LoggerFactory.getLogger(StoryTransform.class);

    private static Function<Story, List<List<Object>>> storyToSql = story -> {
        logger.info("Transforming [" + story.getTitle() + "] to Sql csv");
        List<Object> record = new ArrayList<>();

        record.add(story.getId());
        record.add(story.getTitle());
        record.add(story.getType());

        return asList(record);
    };

    private static Function<Story, List<List<Object>>> storyToNeo4j = story -> {
        logger.info("Transforming [" + story.getTitle() + "] to Neo4j csv");

        List<List<Object>> records = storyToSql.apply(story);
        records.forEach(r -> r.add("Story"));

        return records;
    };

    private StoryTransform() {
    }

    public static Function<Story, List<List<Object>>> getStoryToSql() {
        return storyToSql;
    }

    public static Function<Story, List<List<Object>>> getStoryToNeo4j() {
        return storyToNeo4j;
    }
}
