package com.tobiascode.marvelclient.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CharacterUrl {
    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CharacterUrl{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterUrl characterUrl1 = (CharacterUrl) o;
        return Objects.equals(type, characterUrl1.type) &&
                Objects.equals(url, characterUrl1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, url);
    }
}
