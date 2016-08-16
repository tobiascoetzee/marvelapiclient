package com.tobiascode.marvelclient.transform;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.fxml.PropertyNotFoundException;
import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.testutil.CleanupTestFolder;
import com.tobiascode.marvelclient.testutil.DataBuilder;

public class TransformToCsvFileTest {
    private String importDataFolder;

    @Before
    public void init(){
        importDataFolder = Configuration.getProperty(Property.IMPORT_DATA_FOLDER).orElseThrow(() -> new PropertyNotFoundException(Property.IMPORT_DATA_FOLDER));
        CleanupTestFolder.CleanFiles("testimportdata");
    }


    @After
    public void cleanUp(){
        CleanupTestFolder.CleanFiles("testimportdata");
    }

    @Test
    public void writeCharacterFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "characters_neo4j.csv");
        File sqlFile = new File(importDataFolder + "characters_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeCharacterFile(allCharacters, "characters");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeTeamFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "teams_neo4j.csv");
        File sqlFile = new File(importDataFolder + "teams_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeTeamFile(DataBuilder.buildTeamList(), "teams");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeEventFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "events_neo4j.csv");
        File sqlFile = new File(importDataFolder + "events_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeEventFile(DataBuilder.buildEventList(), "events");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeStoryFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "stories_neo4j.csv");
        File sqlFile = new File(importDataFolder + "stories_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeStoryFile(DataBuilder.buildStoryList(), "stories");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeCharacterEventFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "characters_events_neo4j.csv");
        File sqlFile = new File(importDataFolder + "characters_events_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeCharacterEventFile(DataBuilder.buildCharacterList(), "characters_events");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeCharacterStoryFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "characters_stories_neo4j.csv");
        File sqlFile = new File(importDataFolder + "characters_stories_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeCharacterStoryFile(DataBuilder.buildCharacterList(), "characters_stories");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeEventStoryFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "events_stories_neo4j.csv");
        File sqlFile = new File(importDataFolder + "events_stories_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeEventStoryFile(DataBuilder.buildEventList(), "events_stories");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }


    @Test
    public void writeTeamEventFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "teams_events_neo4j.csv");
        File sqlFile = new File(importDataFolder + "teams_events_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeTeamEventFile(DataBuilder.buildTeamList(), "teams_events");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeTeamStoryFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "teams_stories_neo4j.csv");
        File sqlFile = new File(importDataFolder + "teams_stories_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeTeamStoryFile(DataBuilder.buildTeamList(), "teams_stories");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }

    @Test
    public void writeCharacterTeamFile_should_write_ne04j_and_sql_file() throws Exception {
        File neo4jFile = new File(importDataFolder + "characters_teams_neo4j.csv");
        File sqlFile = new File(importDataFolder + "characters_teams_sql.csv");

        if (neo4jFile.exists()) {
            neo4jFile.delete();
        }

        if (sqlFile.exists()) {
            sqlFile.delete();
        }

        List<Character> allCharacters = DataBuilder.buildCharacterList();
        DataBuilder.enrichCharacterList(allCharacters);

        TransformToCsvFile transformToCsvFile = new TransformToCsvFile();
        transformToCsvFile.writeCharacterTeamFile(allCharacters, "characters_teams");

        assertTrue(neo4jFile.exists());
        assertTrue(sqlFile.exists());
    }
}