package com.tobiascode.marvelclient.scrape;

import java.util.Optional;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreScrapper {
    private final Logger logger = LoggerFactory.getLogger(CharacterScrapper.class);
    private final String characterUrl;
    private Document document;

    public CoreScrapper(String characterUrl) {
        this.characterUrl = characterUrl;
    }

    public Optional<Document> getDocument() {
        if (document == null && characterUrl != null && !characterUrl.isEmpty()) {
            try {
                document = Jsoup.connect(characterUrl).timeout(5000).followRedirects(true).get();
            } catch (HttpStatusException e) {
                if (e.getStatusCode() == 404) {
                    logger.error(characterUrl + " - 404, not found");
                } else {
                    logger.error(characterUrl, e);
                }
            } catch (Exception e) {
                logger.error(characterUrl, e);
            }
        }

        return Optional.ofNullable(document);
    }
}
