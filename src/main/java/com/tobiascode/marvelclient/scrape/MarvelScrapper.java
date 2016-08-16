package com.tobiascode.marvelclient.scrape;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.tobiascode.marvelclient.service.model.Categories;

public class MarvelScrapper {
    private final CoreScrapper scrapper;

    public MarvelScrapper(String characterUrl) {
        scrapper = new CoreScrapper(characterUrl);
    }

    public List<Categories> getCategories() {
        List<Categories> categories = new ArrayList<>();

        if (scrapper.getDocument().isPresent()) {
            categories = processDocumentForCategories();
        }

        return categories;
    }

    private List<Categories> processDocumentForCategories() {
        List<Categories> categories = new ArrayList<>();
        Element element = scrapper.getDocument().get().getElementById("mw-normal-catlinks");

        if (element != null) {
            categories.addAll(processElementForCategories(element));
        }

        return categories;
    }

    private List<Categories> processElementForCategories(Element element) {
        List<Categories> categories = new ArrayList<>();

        for (Element child : element.children()) {
            if ("People".equals(child.text())) {
                categories.add(Categories.PEOPLE);
            } else if ("Teams".equals(child.text())) {
                categories.add(Categories.TEAMS);
            } else if ("Heroes".equals(child.text())) {
                categories.add(Categories.HEROES);
            } else if ("Villains".equals(child.text())) {
                categories.add(Categories.VILLAINS);
            }
        }

        return categories;
    }
}
