/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 7-5-16.
 */
package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Autocompletes the symptoms that are searched in the search bar in the application.
 */
public class AutoCompleteSystem {
    public AutoCompleteSystem(String query, HashMap collection) {
        createAutoCompleteObject(query, collection);
    }

    private JSONArray autoCompleteObject;

    private void createAutoCompleteObject(String query, HashMap collection) {
        ArrayList<String> terms = new ArrayList<String>();
        for (Object term : collection.values()) {
            HPOTerm idGetter = (HPOTerm) term;
            terms.add(idGetter.getName());
        }
        JSONArray arrayObj = new JSONArray();
        query = query.toLowerCase();
        for (String term : terms) {
            String country = term.toLowerCase();
            if (country.startsWith(query.toLowerCase())) {
                arrayObj.put(term);
            }
        }
        this.autoCompleteObject = arrayObj;

    }

    public JSONArray getAutoCompleteObject() {
        return autoCompleteObject;
    }
}
