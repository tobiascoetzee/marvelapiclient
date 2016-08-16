package com.tobiascode.marvelclient.testutil;

public class JsonTestDataLoader extends TestDataLoader {
    public JsonTestDataLoader(String fileName) {
        super(fileName);
        setFolder("restdata");
    }
}
