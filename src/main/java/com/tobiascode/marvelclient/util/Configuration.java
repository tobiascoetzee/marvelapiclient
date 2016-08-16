package com.tobiascode.marvelclient.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static Properties properties;
    private static final String PROPERTY_FILE_NAME = "config.properties";

    private Configuration() {
        loadConfiguration(PROPERTY_FILE_NAME);
    }

    public static Optional<String> getProperty(String name) {
        if (properties == null) {
            new Configuration();
        }

        if (properties == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(properties.getProperty(name));
    }

    private static void loadConfiguration(String fileName) {
        properties = new Properties();

        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file not found");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
