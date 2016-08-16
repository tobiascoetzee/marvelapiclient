package com.tobiascode.marvelclient.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.util.Configuration;

public class ConfigurationTest {
    @Test
    public void getProperty_for_valid_property_should_return_value() {
        String property = Configuration.getProperty(Property.PUBLIC_KEY).get();

        assertEquals("abcd", property);
    }

    @Test
    public void getProperty_for_invalid_property_should_return_value() {
        Optional<String> property = Configuration.getProperty("NotValid");

        assertFalse(property.isPresent());
    }
}