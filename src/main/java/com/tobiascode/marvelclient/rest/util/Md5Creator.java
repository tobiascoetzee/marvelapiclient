package com.tobiascode.marvelclient.rest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class Md5Creator {
    private final String original;

    public Md5Creator(String original) {
        this.original = original;
    }

    public Optional<String> getHash() throws NoSuchAlgorithmException {
        if (original == null) {
            return Optional.empty();
        }

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(original.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder digested = new StringBuilder();

        for (byte b : digest) {
            digested.append(String.format("%02x", b & 0xff));
        }

        return Optional.of(digested.toString());
    }
}
