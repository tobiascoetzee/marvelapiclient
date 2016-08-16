package com.tobiascode.marvelclient.transform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.el.PropertyNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.util.Configuration;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Event;
import com.tobiascode.marvelclient.service.model.Story;
import com.tobiascode.marvelclient.service.model.Team;
import com.tobiascode.marvelclient.transform.constants.DataType;
import com.tobiascode.marvelclient.transform.constants.TransformType;

public class TransformToCsvFile {
    private final Logger logger = LoggerFactory.getLogger(TransformToCsvFile.class);
    private final String importDataFolder;
    private static final String NEO4J_EXT = "_neo4j.csv";
    private static final String SQL_EXT = "_sql.csv";

    public TransformToCsvFile() {
        importDataFolder = Configuration.getProperty(Property.IMPORT_DATA_FOLDER).orElseThrow(() -> new PropertyNotFoundException(Property.IMPORT_DATA_FOLDER));
    }

    public void writeCharacterFile(List<Character> characters, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.CHARACTER, TransformType.NEO4J).getCsv(characters), file);
        writeSqlFile(TransformFactory.instantiate(DataType.CHARACTER, TransformType.SQL).getCsv(characters), file);
    }

    public void writeTeamFile(List<Team> teams, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.TEAM, TransformType.NEO4J).getCsv(teams), file);
        writeSqlFile(TransformFactory.instantiate(DataType.TEAM, TransformType.SQL).getCsv(teams), file);
    }

    public void writeEventFile(List<Event> events, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.EVENT, TransformType.NEO4J).getCsv(events), file);
        writeSqlFile(TransformFactory.instantiate(DataType.EVENT, TransformType.SQL).getCsv(events), file);
    }

    public void writeStoryFile(List<Story> stories, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.STORY, TransformType.NEO4J).getCsv(stories), file);
        writeSqlFile(TransformFactory.instantiate(DataType.STORY, TransformType.SQL).getCsv(stories), file);
    }

    public void writeCharacterEventFile(List<Character> characters, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.CHARACTER_EVENT, TransformType.NEO4J).getCsv(characters), file);
        writeSqlFile(TransformFactory.instantiate(DataType.CHARACTER_EVENT, TransformType.SQL).getCsv(characters), file);
    }

    public void writeCharacterStoryFile(List<Character> characters, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.CHARACTER_STORY, TransformType.NEO4J).getCsv(characters), file);
        writeSqlFile(TransformFactory.instantiate(DataType.CHARACTER_STORY, TransformType.SQL).getCsv(characters), file);
    }

    public void writeCharacterTeamFile(List<Character> characters, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.CHARACTER_TEAM, TransformType.NEO4J).getCsv(characters), file);
        writeSqlFile(TransformFactory.instantiate(DataType.CHARACTER_TEAM, TransformType.SQL).getCsv(characters), file);
    }

    public void writeEventStoryFile(List<Event> events, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.EVENT_STORY, TransformType.NEO4J).getCsv(events), file);
        writeSqlFile(TransformFactory.instantiate(DataType.EVENT_STORY, TransformType.SQL).getCsv(events), file);
    }

    public void writeTeamEventFile(List<Team> teams, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.TEAM_EVENT, TransformType.NEO4J).getCsv(teams), file);
        writeSqlFile(TransformFactory.instantiate(DataType.TEAM_EVENT, TransformType.SQL).getCsv(teams), file);
    }

    public void writeTeamStoryFile(List<Team> teams, String file) throws IOException {
        writeNeo4jFile(TransformFactory.instantiate(DataType.TEAM_STORY, TransformType.NEO4J).getCsv(teams), file);
        writeSqlFile(TransformFactory.instantiate(DataType.TEAM_STORY, TransformType.SQL).getCsv(teams), file);
    }

    private void writeSqlFile(String data, String file) throws IOException {
        String sqlPath = importDataFolder + file + SQL_EXT;

        Files.write(Paths.get(sqlPath), data.getBytes());
        logger.info("Wrote data to: " + sqlPath);
    }

     private void writeNeo4jFile(String data, String file) throws IOException {
        String neo4jPath = importDataFolder + file + NEO4J_EXT;

        Files.write(Paths.get(neo4jPath), data.getBytes());
        logger.info("Wrote data to: " + neo4jPath);
    }
}
