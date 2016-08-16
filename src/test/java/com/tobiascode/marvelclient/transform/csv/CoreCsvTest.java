package com.tobiascode.marvelclient.transform.csv;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.tobiascode.marvelclient.constants.CsvFile;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.service.model.Team;
import com.tobiascode.marvelclient.transform.TransformFactory;
import com.tobiascode.marvelclient.transform.constants.DataType;
import com.tobiascode.marvelclient.transform.constants.TransformType;
import com.tobiascode.marvelclient.testutil.CsvTestDataLoader;
import com.tobiascode.marvelclient.testutil.DataBuilder;

public class CoreCsvTest {
    @Test
    public void getCsv_for_character_sql_should_return_valid_sql_csv_file() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        CoreCsv<Character> characterToSql = TransformFactory.instantiate(DataType.CHARACTER, TransformType.SQL);
        String csv = characterToSql.getCsv(allCharacters);

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_CHARACTERS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_neo4j_should_return_valid_neo4j_csv_file() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        CoreCsv<Character> characterToNeo4j = TransformFactory.instantiate(DataType.CHARACTER, TransformType.NEO4J);
        String csv = characterToNeo4j.getCsv(allCharacters);

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_CHARACTERS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Team> characterToSql = TransformFactory.instantiate(DataType.TEAM, TransformType.SQL);
        String csv = characterToSql.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_TEAMS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Team> characterToNeo4j = TransformFactory.instantiate(DataType.TEAM, TransformType.NEO4J);
        String csv = characterToNeo4j.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_TEAMS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_event_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Event> eventToNeo4j = TransformFactory.instantiate(DataType.EVENT, TransformType.NEO4J);
        String csv = eventToNeo4j.getCsv(DataBuilder.buildEventList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_event_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Event> eventToSql = TransformFactory.instantiate(DataType.EVENT, TransformType.SQL);
        String csv = eventToSql.getCsv(DataBuilder.buildEventList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_event_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Character> characterEventToNeo4j = TransformFactory.instantiate(DataType.CHARACTER_EVENT, TransformType.NEO4J);
        String csv = characterEventToNeo4j.getCsv(DataBuilder.buildCharacterList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_CHARACTERS_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_event_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Character> characterEventToSql = TransformFactory.instantiate(DataType.CHARACTER_EVENT, TransformType.SQL);
        String csv = characterEventToSql.getCsv(DataBuilder.buildCharacterList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_CHARACTERS_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_story_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Story> storyToNeo4j = TransformFactory.instantiate(DataType.STORY, TransformType.NEO4J);
        String csv = storyToNeo4j.getCsv(DataBuilder.buildStoryList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_story_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Story> storyToSql = TransformFactory.instantiate(DataType.STORY, TransformType.SQL);
        String csv = storyToSql.getCsv(DataBuilder.buildStoryList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_story_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Character> characterStoryToNeo4j = TransformFactory.instantiate(DataType.CHARACTER_STORY, TransformType.NEO4J);
        String csv = characterStoryToNeo4j.getCsv(DataBuilder.buildCharacterList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_CHARACTERS_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_story_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Character> characterStoryToSql = TransformFactory.instantiate(DataType.CHARACTER_STORY, TransformType.SQL);
        String csv = characterStoryToSql.getCsv(DataBuilder.buildCharacterList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_CHARACTERS_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_event_story_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Event> eventStoryToNeo4j = TransformFactory.instantiate(DataType.EVENT_STORY, TransformType.NEO4J);
        String csv = eventStoryToNeo4j.getCsv(DataBuilder.buildEventList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_EVENTS_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_event_story_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Event> eventStoryToSql = TransformFactory.instantiate(DataType.EVENT_STORY, TransformType.SQL);
        String csv = eventStoryToSql.getCsv(DataBuilder.buildEventList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_EVENTS_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_event_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Team> characterEventToNeo4j = TransformFactory.instantiate(DataType.TEAM_EVENT, TransformType.NEO4J);
        String csv = characterEventToNeo4j.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_TEAMS_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_event_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Team> characterEventToSql = TransformFactory.instantiate(DataType.TEAM_EVENT, TransformType.SQL);
        String csv = characterEventToSql.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_TEAMS_EVENTS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_story_neo4j_should_return_valid_neo4j_csv_file() {
        CoreCsv<Team> characterStoryToNeo4j = TransformFactory.instantiate(DataType.TEAM_STORY, TransformType.NEO4J);
        String csv = characterStoryToNeo4j.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_TEAMS_STORIES).getData().get(), csv);
    }

    @Test
    public void getCsv_for_team_story_sql_should_return_valid_sql_csv_file() {
        CoreCsv<Team> characterStoryToSql = TransformFactory.instantiate(DataType.TEAM_STORY, TransformType.SQL);
        String csv = characterStoryToSql.getCsv(DataBuilder.buildTeamList());

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_TEAMS_STORIES).getData().get(), csv);
    }



    @Test
    public void getCsv_for_character_team_sql_should_return_valid_sql_csv_file() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        CoreCsv<Character> characterToSql = TransformFactory.instantiate(DataType.CHARACTER_TEAM, TransformType.SQL);
        String csv = characterToSql.getCsv(allCharacters);

        assertEquals(new CsvTestDataLoader(CsvFile.SQL_CHARACTERS_TEAMS).getData().get(), csv);
    }

    @Test
    public void getCsv_for_character_team_neo4j_should_return_valid_neo4j_csv_file() {
        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        CoreCsv<Character> characterToNeo4j = TransformFactory.instantiate(DataType.CHARACTER_TEAM, TransformType.NEO4J);
        String csv = characterToNeo4j.getCsv(allCharacters);

        assertEquals(new CsvTestDataLoader(CsvFile.NEO4J_CHARACTERS_TEAMS).getData().get(), csv);
    }
}