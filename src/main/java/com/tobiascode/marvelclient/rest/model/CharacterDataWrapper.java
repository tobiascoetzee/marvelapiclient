package com.tobiascode.marvelclient.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CharacterDataWrapper {
    private CharacterDataContainer data;

    public CharacterDataContainer getData() {
        return data;
    }

    public void setData(CharacterDataContainer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CharacterDataWrapper{" +
                "data=" + data +
                '}';
    }
}
