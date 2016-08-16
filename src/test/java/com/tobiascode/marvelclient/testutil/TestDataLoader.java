package com.tobiascode.marvelclient.testutil;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

public class TestDataLoader {
    private final String fileName;
    private String folder;

    public TestDataLoader(String fileName) {
        this.fileName = fileName;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Optional<String> getData(){
        String data = null;

        try {
            data = IOUtils.toString(TestDataLoader.class.getClassLoader().getResourceAsStream(folder + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(data);
    }
}
