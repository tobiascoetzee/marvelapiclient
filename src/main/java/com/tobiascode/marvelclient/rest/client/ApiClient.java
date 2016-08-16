package com.tobiascode.marvelclient.rest.client;

import java.util.Map;
import java.util.Optional;

import javax.el.PropertyNotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.tobiascode.marvelclient.rest.model.MarvelAuthorisationKey;
import com.tobiascode.marvelclient.service.client.implementation.CharacterServiceImpl;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.util.Configuration;

public class ApiClient<T> {
    private final String host;
    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);

    public ApiClient() {
        host = Configuration.getProperty(Property.HOST).orElseThrow(() -> new PropertyNotFoundException(Property.HOST));
        logger.debug("Using host [" + host + "] for api call.");
    }

    public Optional<T> get(String resource, Map<String, Object> parameters, Class<T> type) {
        String fullResource = determineFullUrl(resource);

        MarvelAuthorisationKey key = new MarvelAuthorisationKey();
        Client client = ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();

        WebTarget target = addQueryParameters(fullResource, parameters, key, client);
        Response response = getResponse(target);

        return processResponse(type, target, response);
    }

    private Response getResponse(WebTarget target) {
        return target.request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }

    private WebTarget addQueryParameters(String resource, Map<String, Object> parameters, MarvelAuthorisationKey key, Client client) {
        String publicKey = Configuration.getProperty(Property.PUBLIC_KEY).orElseThrow(() -> new PropertyNotFoundException(Property.PUBLIC_KEY));

        WebTarget target = client.target(resource)
                .queryParam("apikey", publicKey)
                .queryParam("hash", key.getHash().orElse(""))
                .queryParam("ts", key.getTs().orElse(""));

        for (Map.Entry<String, Object> k : parameters.entrySet()) {
            target = target.queryParam(k.getKey(), k.getValue());
        }

        return target;
    }

    private Optional<T> processResponse(Class<T> type, WebTarget target, Response response) {
        Optional<T> result = Optional.empty();

        try {
            if (response.getStatus() == 200) {
                result = Optional.ofNullable(response.readEntity(type));
            } else {
                logger.warn(target.toString() + " - " + response.getStatus() + ": " + response.getStatusInfo());
            }
        } finally {
            response.close();
        }

        return result;
    }

    private String determineFullUrl(String resource) {
        boolean addHost = !resource.toLowerCase().startsWith("http");
        String fullResource = resource;

        if (addHost) {
            fullResource = host + resource;
        }

        return fullResource;
    }
}
