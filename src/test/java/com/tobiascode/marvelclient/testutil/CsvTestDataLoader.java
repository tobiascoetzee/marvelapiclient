package com.tobiascode.marvelclient.testutil;

public class CsvTestDataLoader extends TestDataLoader {
    public CsvTestDataLoader(String fileName) {
        super(fileName);
        setFolder("csvdata");
    }
}
