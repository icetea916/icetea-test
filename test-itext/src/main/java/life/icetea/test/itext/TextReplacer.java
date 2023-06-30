package life.icetea.test.itext;

import java.util.HashMap;
import java.util.Map;

public class TextReplacer {

    private final Map<String, String> replacements = new HashMap<>();

    public void addReplacement(String searchText, String replaceText) {
        replacements.put(searchText, replaceText);
    }

    public boolean hasReplacement(String searchText) {
        return replacements.containsKey(searchText);
    }

    public String replace(String searchText) {
        return replacements.getOrDefault(searchText, searchText);
    }
}
