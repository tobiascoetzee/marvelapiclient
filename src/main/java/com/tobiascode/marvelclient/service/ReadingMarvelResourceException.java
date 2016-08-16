package com.tobiascode.marvelclient.service;

public class ReadingMarvelResourceException extends RuntimeException {
    private final int lastOffset;
    private final int lastMaximumLimit;

    public ReadingMarvelResourceException(String message, int lastOffset, int lastMaximumLimit) {
        super(message);
        this.lastOffset = lastOffset;
        this.lastMaximumLimit = lastMaximumLimit;
    }
}
