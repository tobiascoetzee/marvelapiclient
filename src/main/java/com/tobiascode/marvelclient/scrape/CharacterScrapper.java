package com.tobiascode.marvelclient.scrape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tobiascode.marvelclient.scrape.util.HeightConverter;
import com.tobiascode.marvelclient.scrape.util.WeightConvert;

public class CharacterScrapper {
    private final CoreScrapper scrapper;

    public CharacterScrapper(String characterUrl) {
        scrapper = new CoreScrapper(characterUrl);
    }

    public String getRealName() {
        return getPowerBoxValue("Real Name");
    }

    public String getIdentity() {
        return getPowerBoxValue("Identity");
    }

    public String getCitizenship() {
        return getPowerBoxValue("Citizenship");
    }

    public String getPlaceOfBirth() {
        return getPowerBoxValue("Place of Birth");
    }

    public double getHeight() {
        String height = getPhysicalAttributes("Height");
        return new HeightConverter(height).heightInCm();
    }

    public double getWeight() {
        String weight = getPhysicalAttributes("Weight");
        return new WeightConvert(weight).weightInKg();
    }

    public String getEyes() {
        return getPhysicalAttributes("Eyes");
    }

    public String getHair() {
        return getPhysicalAttributes("Hair");
    }

    public List<String> getTeams(){
        List<String> teams = new ArrayList<>();

        if (scrapper.getDocument().isPresent()) {
            Element teamAffiliationElement = scrapper.getDocument().get()
                    .getElementById("char-affiliation-content");

            if(teamAffiliationElement != null) {
                Elements elements = teamAffiliationElement.getElementsByTag("a");

                if (elements != null) {
                    teams = elements.stream()
                            .map(e -> e.text())
                            .collect(Collectors.toList());
                }
            }
        }

        return teams;
    }

    private String getPowerBoxValue(String key) {
        if (scrapper.getDocument().isPresent()) {
            Element element = scrapper.getDocument().get().getElementById("powerbox");

            return element.children().stream()
                    .filter(e -> e.text().startsWith(key))
                    .map(e -> e.textNodes().get(1).text().trim())
                    .findFirst().orElse("");
        }

        return "";
    }

    private String getPhysicalAttributes(String key) {
        if (scrapper.getDocument().isPresent()) {
            Element element = scrapper.getDocument().get().getElementById("char-physicals-content");

            if(element != null) {
                return element.children().stream()
                        .filter(e -> e.text().startsWith(key))
                        .map(e -> e.textNodes().size() > 1 ? e.textNodes().get(1).text().trim() : e.textNodes().get(0).text().trim())
                        .findFirst().orElse("");
            }
        }

        return "";
    }
}
