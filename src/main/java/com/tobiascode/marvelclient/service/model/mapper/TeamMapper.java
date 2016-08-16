package com.tobiascode.marvelclient.service.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Team;

public class TeamMapper {
    private final Logger logger = LoggerFactory.getLogger(TeamMapper.class);
    private final Team team;

    public TeamMapper(Character character) {
        team = createTeam(character);
    }

    public Team getTeam() {
        return team;
    }

    private Team createTeam(Character character) {
        logger.info("Mapping: " + character.getName());

        Team mappedTeam = new Team();

        mappedTeam.setId(character.getId());
        mappedTeam.setName(character.getName());
        mappedTeam.setDescription(character.getDescription());
        mappedTeam.setThumbnail(character.getThumbnail());
        mappedTeam.setEventIds(character.getEventIds());
        mappedTeam.setStoryIds(character.getStoryIds());
        mappedTeam.setCategories(character.getCategories());

        return mappedTeam;
    }
}
