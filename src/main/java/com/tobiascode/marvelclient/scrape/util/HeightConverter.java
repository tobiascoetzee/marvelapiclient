package com.tobiascode.marvelclient.scrape.util;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeightConverter {
    private static final Logger logger = LoggerFactory.getLogger(HeightConverter.class);
    private final String height;
    private static final double FEET_TO_CM = 30.48;
    private static final double INCHES_TO_CM = 2.54;

    public HeightConverter(String height) {
        this.height = cleanHeight(height);
    }

    public double heightInCm() {
        if (height == null || height.isEmpty()) {
            return 0;
        }

        double feet = getFeet(height);
        double inches = getInches(height);

        double feetInCm = 0;
        double inchesInCm = 0;

        try {
            feetInCm = feet * FEET_TO_CM;
            inchesInCm = inches * INCHES_TO_CM;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return feetInCm + inchesInCm;
    }

    private static String cleanHeight(String height) {
        if (height == null) {
            return "";
        }

        return height.replaceAll("’", "'").replaceAll("”", "\"");
    }

    private static double getFeet(String height) {
        double feet = 0;

        try {
            Scanner scanner = new Scanner(height).useDelimiter("\\D+");
            feet = scanner.nextInt();
        } catch (Exception e) {
            logger.error("[" + height + "] caused error getting feet: " + e.getMessage(), e);
        }

        return feet;
    }

    private static double getInches(String height) {
        double inches = 0;

        try {
            inches = Integer.valueOf((height.substring(height.indexOf('\'') + 1, height.indexOf('"'))).trim());
        } catch (Exception e) {
            logger.error("[" + height + "] caused error getting inches: " + e.getMessage(), e);
        }

        return inches;
    }
}
