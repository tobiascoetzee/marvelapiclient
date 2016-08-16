package com.tobiascode.marvelclient.testutil;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.util.List;

import com.tobiascode.marvelclient.service.model.*;
import com.tobiascode.marvelclient.service.model.Character;

public class DataBuilder {
    public static List<Character> buildCharacterList() {
        Character spiderman = new Character();
        spiderman.setId(1009610);
        spiderman.setName("Spider-Man");
        spiderman.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b", "jpg"));
        spiderman.getEventIds().addAll(asList(116, 314));
        spiderman.getStoryIds().addAll(singletonList(7));
        spiderman.setWikiUrl("http://localhost:8080/universe/Spider-Man");

        Character deadpool = new Character();
        deadpool.setId(1017474);
        deadpool.setName("Deadpool (X-Men: Battle of the Atom)");
        deadpool.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/6/20/52d72a5b55a55", "jpg"));
        deadpool.getEventIds().addAll(asList(227, 314));
        deadpool.getStoryIds().addAll(asList(7, 8, 10));
        deadpool.setWikiUrl("http://localhost:8080/universe/Deadpool");


        Character death = new Character();
        death.setId(1009269);
        death.setName("Death");
        death.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/5/80/526031f5a21eb", "jpg"));
        death.getEventIds().addAll(singletonList(314));
        death.getStoryIds().addAll(asList(8, 10));
        death.setWikiUrl("http://localhost:8080/universe/Death");

        return asList(spiderman, deadpool, death);
    }

    public static List<Character> buildCharacterListWithTeams() {
        List<Character> allCharacters = buildCharacterList();

        Character avengers = new Character();
        avengers.setId(1009165);
        avengers.setName("Avengers");
        avengers.setDescription("Earth's Mightiest Heroes joined forces to take on threats that were too big for any one hero to tackle. With a roster that has included Captain America, Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earth's No. 1 team.");
        avengers.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7", "jpg"));
        avengers.getEventIds().addAll(asList(116, 314));
        avengers.getStoryIds().addAll(singletonList(7));
        avengers.setWikiUrl("http://localhost:8080/universe/Avengers");

        Character fantasticFour = new Character();
        fantasticFour.setId(1009299);
        fantasticFour.setName("Fantastic Four");
        fantasticFour.setDescription("After being exposed to cosmic rays, Reed Richards, Susan Storm, Ben Grimm and Johnny Storm found they had amazing new powers. Reed Richards found he has the ability to stretch his body in any way he wanted, while Susan Storm can turn herself, objects and other people invisible. Ben Grimm transformed into a rocky, super-strong behemoth and Johnny Storm has the ability to set himself on fire. Dubbed the Fantastic Four, Mr. Fantastic, Invisible Woman, Thing and the Human Torch are Marvel's First Family.");
        fantasticFour.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/60/50febc4f55525", "jpg"));
        fantasticFour.getEventIds().addAll(asList(227, 314));
        fantasticFour.getStoryIds().addAll(asList(7, 8, 10));
        fantasticFour.setWikiUrl("http://localhost:8080/universe/Fantastic_Four");


        Character xMen = new Character();
        xMen.setId(1009726);
        xMen.setName("X-Men");
        xMen.setDescription("Feared and hated by humans because they're different, the X-Men are heroic mutants, individuals born with special powers who've sworn to use their gifts to protect mutants as well as humans.");
        xMen.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/8/03/510c08f345938", "jpg"));
        xMen.getEventIds().addAll(singletonList(314));
        xMen.getStoryIds().addAll(asList(8, 10));
        xMen.setWikiUrl("http://localhost:8080/universe/Death");

        allCharacters.addAll(asList(avengers, fantasticFour, xMen));

        return allCharacters;
    }

    public static void enrichCharacterList(List<Character> allCharacters) {
        Character spiderman = allCharacters.stream().filter(c -> c.getName().equalsIgnoreCase("Spider-Man")).findFirst().get();
        spiderman.setRealName("Peter Benjamin Parker");
        spiderman.setIdentity("Secret");
        spiderman.setCitizenship("U.S.A.");
        spiderman.setPlaceOfBirth("Forest Hills, New York");
        spiderman.setEyeColour("Hazel");
        spiderman.setHairColour("Brown");
        spiderman.setHeight(177.8);
        spiderman.setWeight(75.749864);
        spiderman.setTeamNames(singletonList("Avengers"));
        spiderman.setTeamIds(singletonList(1009165));
        spiderman.setCategories(asList(Categories.PEOPLE, Categories.HEROES));

        Character deadpool = allCharacters.stream().filter(c -> c.getName().equalsIgnoreCase("Deadpool (X-Men: Battle of the Atom)")).findFirst().get();
        deadpool.setRealName("Wade Wilson");
        deadpool.setIdentity("Known to Canadian government officials");
        deadpool.setCitizenship("Canada");
        deadpool.setPlaceOfBirth("Unrevealed location in Canada");
        deadpool.setEyeColour("Brown");
        deadpool.setHairColour("None, formerly brown");
        deadpool.setHeight(187.96);
        deadpool.setWeight(95.2544);
        deadpool.setTeamNames(singletonList("Fantastic Four"));
        deadpool.setTeamIds(singletonList(1009299));
        deadpool.setCategories(asList(Categories.PEOPLE, Categories.HEROES));

        Character death = allCharacters.stream().filter(c -> c.getName().equalsIgnoreCase("Death")).findFirst().get();
        death.setRealName("Inapplicable");
        death.setIdentity("Inapplicable");
        death.setCitizenship("Inapplicable");
        death.setPlaceOfBirth("Inapplicable");
        death.setEyeColour("Black");
        death.setHairColour("Black");
        death.setHeight(170.18);
        death.setWeight(63.0493);
        death.setTeamNames(singletonList("X-Men"));
        death.setTeamIds(singletonList(1009726));
        death.setCategories(asList(Categories.PEOPLE, Categories.VILLAINS));
    }

    public static List<Event> buildEventList() {
        Event actsOfVengeance = new Event();
        actsOfVengeance.setId(116);
        actsOfVengeance.setTitle("Acts of Vengeance!");
        actsOfVengeance.setDescription("Loki sets about convincing the super-villains of Earth to attack heroes other than those they normally fight in an attempt to destroy the Avengers to absolve his guilt over inadvertently creating the team in the first place.");
        actsOfVengeance.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/40/51ca10d996b8b", "jpg"));
        actsOfVengeance.getStoryIds().addAll(singletonList(7));

        Event ageOfApocalypse = new Event();
        ageOfApocalypse.setId(227);
        ageOfApocalypse.setTitle("Age of Apocalypse");
        ageOfApocalypse.setDescription("In a twisted version of the world they knew, the X-Men battle against the eternal mutant Apocalypse as Bishop seeks to repair the timeline. Legion, Xavier's own son, attempts to kill off all of Xavier's enemies; however, when Legion attempts to murder Magneto, Xavier sacrifices his own life to save Magnus. As a result, Magneto casts off his anti-human sentiments and carries on Xavier's dream of peaceful co-existence, thereby founding the X-Men.");
        ageOfApocalypse.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/5/e0/51ca0e08a6546", "jpg"));
        ageOfApocalypse.getStoryIds().addAll(asList(8, 10));

        Event ageOfUltron = new Event();
        ageOfUltron.setId(314);
        ageOfUltron.setTitle("Age of Ultron");
        ageOfUltron.setDescription("In a 10-issue event written by Brian Michael Bendis with art by Bryan Hitch, Carlos Pacheco, Brandon Peterson and more, the heroes of the Marvel Universe have already fallen with the robot Ultron remaking the world in his image. The surviving Avengers, X-Men and others must fight through time and space in the hope of defeating their foe by any means necessary!");
        ageOfUltron.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/c/10/51ca0fc4c83c8", "jpg"));
        ageOfUltron.getStoryIds().addAll(asList(7, 8, 10));


        return asList(actsOfVengeance, ageOfApocalypse, ageOfUltron);
    }

    public static List<Story> buildStoryList() {
        Story story1 = new Story();
        story1.setId(7);
        story1.setTitle("Investigating the murder of a teenage girl, Cage suddenly learns that a three-way gang war is under way for control of the turf");
        story1.setType("story");

        Story story2 = new Story();
        story2.setId(8);
        story2.setTitle("In the wake of September 11th, the world watched as firefighters, police officers and EMT workers selflessly risked their lives");
        story2.setType("story");

        Story story3 = new Story();
        story3.setId(10);
        story3.setTitle("In this thought-provoking anthology, a world-class collection of top comic-book creators from around the globe presents a series");
        story3.setType("story");

        return asList(story1, story2, story3);
    }

    public static List<Team> buildTeamList() {
        Team avengers = new Team();
        avengers.setId(1009165);
        avengers.setName("Avengers");
        avengers.setDescription("Earth's Mightiest Heroes joined forces to take on threats that were too big for any one hero to tackle. With a roster that has included Captain America, Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earth's No. 1 team.");
        avengers.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7", "jpg"));
        avengers.getEventIds().addAll(asList(116, 314));
        avengers.getStoryIds().addAll(singletonList(7));
        avengers.setWikiUrl("http://localhost:8080/universe/Avengers");
        avengers.setCategories(asList(Categories.TEAMS, Categories.HEROES));

        Team fantasticFour = new Team();
        fantasticFour.setId(1009299);
        fantasticFour.setName("Fantastic Four");
        fantasticFour.setDescription("After being exposed to cosmic rays, Reed Richards, Susan Storm, Ben Grimm and Johnny Storm found they had amazing new powers. Reed Richards found he has the ability to stretch his body in any way he wanted, while Susan Storm can turn herself, objects and other people invisible. Ben Grimm transformed into a rocky, super-strong behemoth and Johnny Storm has the ability to set himself on fire. Dubbed the Fantastic Four, Mr. Fantastic, Invisible Woman, Thing and the Human Torch are Marvel's First Family.");
        fantasticFour.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/9/60/50febc4f55525", "jpg"));
        fantasticFour.getEventIds().addAll(asList(227, 314));
        fantasticFour.getStoryIds().addAll(asList(7, 8, 10));
        fantasticFour.setWikiUrl("http://localhost:8080/universe/Fantastic_Four");
        fantasticFour.setCategories(asList(Categories.TEAMS, Categories.HEROES));

        Team xMen = new Team();
        xMen.setId(1009726);
        xMen.setName("X-Men");
        xMen.setDescription("Feared and hated by humans because they're different, the X-Men are heroic mutants, individuals born with special powers who've sworn to use their gifts to protect mutants as well as humans.");
        xMen.setThumbnail(new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/8/03/510c08f345938", "jpg"));
        xMen.getEventIds().addAll(singletonList(314));
        xMen.getStoryIds().addAll(asList(8, 10));
        xMen.setWikiUrl("http://localhost:8080/universe/Death");
        xMen.setCategories(asList(Categories.TEAMS, Categories.HEROES));

        return asList(avengers, fantasticFour, xMen);
    }
}
