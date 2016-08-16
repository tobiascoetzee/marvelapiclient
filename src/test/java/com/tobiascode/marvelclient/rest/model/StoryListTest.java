package com.tobiascode.marvelclient.rest.model;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class StoryListTest {
    @Test
    public void equals_and_hash_are_valid() {
        EqualsVerifier.forClass(CharacterUrl.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}