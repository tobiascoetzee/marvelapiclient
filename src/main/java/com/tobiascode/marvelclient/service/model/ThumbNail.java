package com.tobiascode.marvelclient.service.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public final class ThumbNail implements Serializable {
    private String path;
    private String extension;

    public ThumbNail(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public Optional<String> getFullPath() {
        if (path != null && !path.isEmpty() && extension != null && !extension.isEmpty()) {
            return Optional.of(path + "/standard_small." + extension);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "ThumbNail{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThumbNail thumbNail = (ThumbNail) o;
        return Objects.equals(path, thumbNail.path) &&
                Objects.equals(extension, thumbNail.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, extension);
    }
}
