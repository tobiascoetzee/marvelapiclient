package com.tobiascode.marvelclient.service.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ThumbNailTest {
    @Test
    public void equals_and_hash_are_valid() {
        EqualsVerifier.forClass(ThumbNail.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void getFullPath_should_return_valid_path() {
        ThumbNail thumbNail = new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73", "jpg");

        assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/standard_small.jpg",thumbNail.getFullPath().get());
    }

    @Test
    public void getFullPath_should_return_empty_path_if_no_path() {
        ThumbNail thumbNail = new ThumbNail("", "jpg");

        assertFalse(thumbNail.getFullPath().isPresent());
    }

    @Test
    public void getFullPath_should_return_empty_path_if_no_extention() {
        ThumbNail thumbNail = new ThumbNail("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73", "");

        assertFalse(thumbNail.getFullPath().isPresent());
    }

    @Test
    public void getFullPath_should_return_empty_path_if_null_value() {
        ThumbNail thumbNail = new ThumbNail(null, null);

        assertFalse(thumbNail.getFullPath().isPresent());
    }
}