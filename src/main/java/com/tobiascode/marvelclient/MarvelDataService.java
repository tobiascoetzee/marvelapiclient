package com.tobiascode.marvelclient;

import java.io.IOException;

/**
 * Created by tobias on 2016/08/10.
 */
public interface MarvelDataService {
    void processCharacters() throws IOException;

    void processStories() throws IOException;

    void processEvents() throws IOException;
}
