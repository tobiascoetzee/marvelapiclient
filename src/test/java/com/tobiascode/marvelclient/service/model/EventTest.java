package com.tobiascode.marvelclient.service.model;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class EventTest {
    @Test
    public void equals_and_hash_are_valid() {
        EqualsVerifier.forClass(Event.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}