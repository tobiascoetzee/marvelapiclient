package com.tobiascode.marvelclient.rest.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.rest.util.Md5Creator;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.util.Configuration;

public final class MarvelAuthorisationKey {
    private final Logger logger = LoggerFactory.getLogger(MarvelAuthorisationKey.class);
    private final String ts;
    private final String privateKey;
    private final String publicKey;

    public MarvelAuthorisationKey() {
        privateKey = Configuration.getProperty(Property.PRIVATE_KEY).orElseThrow(() -> new PropertyNotFoundException(Property.PRIVATE_KEY));
        publicKey = Configuration.getProperty(Property.PUBLIC_KEY).orElseThrow(() -> new PropertyNotFoundException(Property.PUBLIC_KEY));
        ts = LocalTime.now().toString().replace(":", "").replace(".", "");
    }

    public MarvelAuthorisationKey(String ts, String privateKey, String publicKey) {
        this.ts = ts;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public Optional<String> getTs() {
        return Optional.ofNullable(ts);
    }

    public Optional<String> getHash() {
        try {
            return new Md5Creator(ts + privateKey + publicKey).getHash();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }

        return Optional.empty();
    }
}
