package com.tobiascode.marvelclient.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CharacterList {
    private int available;
    private String collectionURI;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    @Override
    public String toString() {
        return "CharacterList{" +
                "available=" + available +
                ", collectionURI='" + collectionURI + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterList that = (CharacterList) o;
        return available == that.available &&
                Objects.equals(collectionURI, that.collectionURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, collectionURI);
    }
}
