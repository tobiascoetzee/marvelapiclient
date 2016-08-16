package com.tobiascode.marvelclient.transform;

import com.tobiascode.marvelclient.transform.constants.DataType;
import com.tobiascode.marvelclient.transform.constants.TransformType;
import com.tobiascode.marvelclient.transform.csv.*;

public class TransformFactory {
    private static final String ID = ":ID";
    private static final String NAME = "name";
    private static final String THUMBNAIL = "thumbnail";
    private static final String REAL_NAME = "realname";
    private static final String CHARACTER_ID = "characterid";
    private static final String ID_SMALL = "id";
    private static final String START_ID = ":START_ID";
    private static final String END_ID = ":END_ID";
    private static final String TEAM_ID = "teamid";
    private static final String EVENT_ID = "eventid";
    private static final String STORY_ID = "storyid";
    private static final String TYPE_LABEL = ":TYPE";
    private static final String LABEL = ":LABEL";
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String ALLIANCE = "alliance";
    private static final String IDENTITY = "identity";
    private static final String PLACE_OF_BIRTH = "placeofbirth";
    private static final String EYE_COLOUR = "eyecolour";
    private static final String HAIR_COLOUR = "haircolour";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";

    private static final Object[] CHARACTER_NEO4J = {ID, NAME, THUMBNAIL, REAL_NAME, IDENTITY, PLACE_OF_BIRTH, EYE_COLOUR, HAIR_COLOUR, HEIGHT, WEIGHT, ALLIANCE, LABEL};
    private static final Object[] CHARACTER_SQL = {ID_SMALL, NAME, THUMBNAIL, REAL_NAME, IDENTITY, PLACE_OF_BIRTH, EYE_COLOUR, HAIR_COLOUR, HEIGHT, WEIGHT, ALLIANCE};
    private static final Object[] TEAM_NEO4J = {ID, NAME, DESCRIPTION, THUMBNAIL, ALLIANCE, LABEL};
    private static final Object[] TEAM_SQL = {ID_SMALL, NAME, DESCRIPTION, THUMBNAIL, ALLIANCE};
    private static final Object[] EVENT_NEO4J = {ID, TITLE, DESCRIPTION, THUMBNAIL, LABEL};
    private static final Object[] EVENT_SQL = {ID_SMALL, TITLE, DESCRIPTION, THUMBNAIL};
    private static final Object[] STORY_NEO4J = {ID, TITLE, TYPE, LABEL};
    private static final Object[] STORY_SQL = {ID_SMALL, TITLE, TYPE};
    private static final Object[] CHARACTER_TEAM_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] CHARACTER_TEAM_SQL = {CHARACTER_ID, TEAM_ID};
    private static final Object[] CHARACTER_EVENT_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] CHARACTER_EVENT_SQL = {CHARACTER_ID, EVENT_ID};
    private static final Object[] CHARACTER_STORY_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] CHARACTER_STORY_SQL = {CHARACTER_ID, STORY_ID};
    private static final Object[] TEAM_EVENT_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] TEAM_EVENT_SQL = {TEAM_ID, EVENT_ID};
    private static final Object[] TEAM_STORY_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] TEAM_STORY_SQL = {TEAM_ID, STORY_ID};
    private static final Object[] EVENT_STORY_NEO4J = {START_ID, END_ID, TYPE_LABEL};
    private static final Object[] EVENT_STORY_SQL = {EVENT_ID, STORY_ID};

    private TransformFactory() {
    }

    public static CoreCsv instantiate(DataType dataType, TransformType transformType) {
        if (dataType == DataType.CHARACTER) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(CHARACTER_NEO4J, CharacterTransform.getCharacterToNeo4j());
            }
            return new CoreCsv<>(CHARACTER_SQL, CharacterTransform.getCharacterToSql());
        }

        if (dataType == DataType.TEAM) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(TEAM_NEO4J, TeamTransform.getTeamToNeo4j());
            }
            return new CoreCsv<>(TEAM_SQL, TeamTransform.getTeamToSql());
        }

        if (dataType == DataType.EVENT) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(EVENT_NEO4J, EventTransform.getEventToNeo4j());
            }
            return new CoreCsv<>(EVENT_SQL, EventTransform.getEventToSql());
        }

        if (dataType == DataType.STORY) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(STORY_NEO4J, StoryTransform.getStoryToNeo4j());
            }
            return new CoreCsv<>(STORY_SQL, StoryTransform.getStoryToSql());
        }

        if (dataType == DataType.CHARACTER_TEAM) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(CHARACTER_TEAM_NEO4J, CharacterTransform.getCharacterTeamToNeo4j());
            }
            return new CoreCsv<>(CHARACTER_TEAM_SQL, CharacterTransform.getCharacterTeamToSql());
        }

        if (dataType == DataType.CHARACTER_EVENT) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(CHARACTER_EVENT_NEO4J, CharacterTransform.getCharacterEventToNeo4j());
            }
            return new CoreCsv<>(CHARACTER_EVENT_SQL, CharacterTransform.getCharacterEventToSql());
        }

        if (dataType == DataType.CHARACTER_STORY) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(CHARACTER_STORY_NEO4J, CharacterTransform.getCharacterStoryToNeo4j());
            }
            return new CoreCsv<>(CHARACTER_STORY_SQL, CharacterTransform.getCharacterStoryToSql());
        }

        if (dataType == DataType.TEAM_EVENT) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(TEAM_EVENT_NEO4J, TeamTransform.getTeamEventToNeo4j());
            }
            return new CoreCsv<>(TEAM_EVENT_SQL, TeamTransform.getTeamEventToSql());
        }

        if (dataType == DataType.TEAM_STORY) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(TEAM_STORY_NEO4J, TeamTransform.getTeamStoryToNeo4j());
            }
            return new CoreCsv<>(TEAM_STORY_SQL, TeamTransform.getTeamStoryToSql());
        }

        if (dataType == DataType.EVENT_STORY) {
            if (transformType == TransformType.NEO4J) {
                return new CoreCsv<>(EVENT_STORY_NEO4J, EventTransform.getEventStoryToNeo4j());
            }
            return new CoreCsv<>(EVENT_STORY_SQL, EventTransform.getEventStoryToSql());
        }

        throw new IllegalArgumentException("No transformer found for the given combination");
    }
}
