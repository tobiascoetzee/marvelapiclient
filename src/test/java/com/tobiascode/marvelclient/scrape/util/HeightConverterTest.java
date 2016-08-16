package com.tobiascode.marvelclient.scrape.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeightConverterTest {
    @Test
    public void heightInCm_given_feet_and_inches_should_return_height_in_cm() {
        double expectedHeight = 177.8;

        double height = new HeightConverter("5'10\"").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_null_should_return_zero_cm() {
        double expectedHeight = 0;

        double height = new HeightConverter(null).heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_empty_should_return_zero_cm() {
        double expectedHeight = 0;

        double height = new HeightConverter("").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_funky_value_should_return_height_in_cm() {
        double expectedHeight = 203.2;

        double height = new HeightConverter("(Abomination) 6'8\"; (Blonsky) 5'10\"").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_funky_value_should_return_height_in_cmx() {
        double expectedHeight = 182.88;

        double height = new HeightConverter("6' (6' 7\" with wings)").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_funky_value_with_spaces_should_return_height_in_cm() {
        double expectedHeight = 177.8;

        double height = new HeightConverter("5 '10\"").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }

    @Test
    public void heightInCm_given_funky_big_value_should_return_height_in_cm() {
        double expectedHeight = 185135.52;

        double height = new HeightConverter("(Abomination) 6000'888\"; (Blonsky) 5000'100\"").heightInCm();

        assertEquals(expectedHeight, height, 0);
    }
}