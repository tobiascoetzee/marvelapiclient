package com.tobiascode.marvelclient.service.model;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class StoryTest {
    @Test
    public void equals_and_hash_are_valid() {
        EqualsVerifier.forClass(Story.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}