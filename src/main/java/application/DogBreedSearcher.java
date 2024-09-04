package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DogBreedSearcher {

    public Map.Entry<String, List<String>> searchBreedsInTwoFiles(String urlString1, String urlString2, String[] keywords) {
        List<String> matches1 = searchBreeds(urlString1, keywords);
        List<String> matches2 = searchBreeds(urlString2, keywords);

        if (matches1.size() >= matches2.size()) {
            return new SimpleEntry<>(urlString1, matches1);
        } else {
            return new SimpleEntry<>(urlString2, matches2);
        }
    }

    private List<String> searchBreeds(String urlString, String[] keywords) {
        List<String> matches = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    boolean match = true;
                    for (String keyword : keywords) {
                        if (!line.toLowerCase().contains(keyword)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        matches.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
}