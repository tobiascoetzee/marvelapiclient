package com.tobiascode.marvelclient.service.model.mapper;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.Test;

import com.tobiascode.marvelclient.service.model.Categories;
import com.tobiascode.marvelclient.service.model.Character;
import com.tobiascode.marvelclient.service.model.Team;
import com.tobiascode.marvelclient.service.model.ThumbNail;

public class TeamMapperTest {
    @Test
    public void getTeam_should_map_character_to_team() {
        TeamMapper teamMapper = new TeamMapper(buildCharacter());

        assertEquals(buildTeam(), teamMapper.getTeam());
    }

    private Character buildCharacter(){
        Character character = new Character();

        character.setId(1009165);
        character.setName("Avengers");
        character.setDescription("Earth's Mightiest Heroes joined forces to take on threats that were too big for any one hero to tackle. With a roster that has included Captain America, Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earth's No. 1 team.");
        character.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7","jpg"));
        character.setEventIds(asList(10,11,12));
        character.setStoryIds(asList(1056,1199,1234));
        character.setCategories(asList(Categories.TEAMS,Categories.HEROES));

        return character;
    }

    private Team buildTeam(){
        Team team = new Team();

        team.setId(1009165);
        team.setName("Avengers");
        team.setDescription("Earth's Mightiest Heroes joined forces to take on threats that were too big for any one hero to tackle. With a roster that has included Captain America, Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earth's No. 1 team.");
        team.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7","jpg"));
        team.setEventIds(asList(10,11,12));
        team.setStoryIds(asList(1056,1199,1234));
        team.setCategories(asList(Categories.TEAMS,Categories.HEROES));

        return team;
    }
}