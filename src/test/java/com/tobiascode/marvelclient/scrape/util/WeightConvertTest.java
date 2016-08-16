package com.tobiascode.marvelclient.scrape.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeightConvertTest {
    @Test
    public void weightInKg_given_pounds_should_return_kg() {
        double expectedKg = 75.749864;

        double kg = new WeightConvert("167 lb.").weightInKg();

        assertEquals(expectedKg, kg, 0);
    }

    @Test
    public void weightInKg_given_null_should_return_zero_kg() {
        double expectedKg = 0;

        double kg = new WeightConvert(null).weightInKg();

        assertEquals(expectedKg, kg, 0);
    }

    @Test
    public void weightInKg_given_empty_should_return_zero_kg() {
        double expectedKg = 0;

        double kg = new WeightConvert("").weightInKg();

        assertEquals(expectedKg, kg, 0);
    }
}