package com.tobiascode.marvelclient.rest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class MarvelAuthorisationKeyTest {
    @Test
    public void getTs_should_return_value_from_constructor() {
        String expectedTs = "1";

        MarvelAuthorisationKey marvelAuthorisationKey = new MarvelAuthorisationKey(expectedTs, "abcd", "1234");
        String ts = marvelAuthorisationKey.getTs().get();

        assertEquals(expectedTs, ts);
    }

    @Test
    public void getTs_should_return_value_if_no_value_was_passed_to_constructor() {
        MarvelAuthorisationKey marvelAuthorisationKey = new MarvelAuthorisationKey();
        Optional<String> ts = marvelAuthorisationKey.getTs();

        assertTrue(ts.isPresent());
    }

    @Test
    public void getHash() {
        String expectedHash = "ffd275c5130566a2916217b101f26150";

        MarvelAuthorisationKey marvelAuthorisationKey = new MarvelAuthorisationKey("1", "abcd", "1234");
        String hash = marvelAuthorisationKey.getHash().get();

        assertEquals(expectedHash, hash);
    }

}