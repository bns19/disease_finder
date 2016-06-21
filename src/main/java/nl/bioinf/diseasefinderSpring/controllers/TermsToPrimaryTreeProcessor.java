/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bas on 7-5-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import java.util.HashMap;

/**
 *
 */
public class TermsToPrimaryTreeProcessor {


    public TermsToPrimaryTreeProcessor(HashMap collection, String autoComp) {
        procesTermsToTree(collection, autoComp);

    }

    private JSONArray nodes;
    private void procesTermsToTree(HashMap collection, String autoComp) {
        JSONArray nodesToShow = new JSONArray();
        String id = "";
        for (Object key: collection.keySet()) {
            HPOTerm node = (HPOTerm) collection.get(key.toString());
            if (node.getName().equalsIgnoreCase(autoComp)) {
                id = node.getId();
            }
        }

        while (!id.equals("HP:0000001")) {
            HPOTerm term = (HPOTerm) collection.get(id);
            nodesToShow.put(term.getId());
            id = term.getIsA().get(0).toString();
        }
        this.nodes = nodesToShow;
    }

    public JSONArray getNodes() {
        return this.nodes;
    }
}
