package com.tobiascode.marvelclient.scrape.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightConvert {
    private final Logger logger = LoggerFactory.getLogger(WeightConvert.class);
    private final String weight;
    private static final double POUNDS_TO_KG = 0.453592;

    public WeightConvert(String weight) {
        this.weight = weight;
    }

    public double weightInKg() {
        double pounds = 0;

        if (weight == null || weight.isEmpty()) {
            return 0;
        }

        try {
            pounds = Integer.valueOf(weight.replaceAll("\\D+", ""));
        } catch (Exception e) {
            logger.error("[" + weight + "] caused error: " + e.getMessage(), e);
        }

        return pounds * POUNDS_TO_KG;
    }
}
