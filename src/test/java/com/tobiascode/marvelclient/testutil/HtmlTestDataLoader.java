package com.tobiascode.marvelclient.testutil;

public class HtmlTestDataLoader extends TestDataLoader {
    public HtmlTestDataLoader(String fileName) {
        super(fileName);
        setFolder("htmldata");
    }
}
