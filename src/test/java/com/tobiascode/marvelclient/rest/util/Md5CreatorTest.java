package com.tobiascode.marvelclient.rest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

import com.tobiascode.marvelclient.rest.util.Md5Creator;

public class Md5CreatorTest {
    @Test
    public void getHash_given_valid_string_should_return_hash() throws Exception {
        String expected = "ffd275c5130566a2916217b101f26150";

        Md5Creator md5Creator = new Md5Creator("1" + "abcd" + "1234");
        String hash = md5Creator.getHash().get();

        assertEquals(expected, hash);
    }

    @Test
    public void getHash_given_empty_string_should_return_hash() throws Exception {
        String expected = "d41d8cd98f00b204e9800998ecf8427e";

        Md5Creator md5Creator = new Md5Creator("");
        String hash = md5Creator.getHash().get();

        assertEquals(expected, hash);
    }

    @Test
    public void getHash_given_null_string_should_return_empty_optional() throws Exception {
        String expected = "d41d8cd98f00b204e9800998ecf8427e";

        Md5Creator md5Creator = new Md5Creator(null);
        Optional<String> hash = md5Creator.getHash();

        assertFalse(hash.isPresent());
    }
}