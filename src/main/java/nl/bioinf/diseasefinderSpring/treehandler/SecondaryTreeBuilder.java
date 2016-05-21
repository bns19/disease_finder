package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by henridupon on 20-05-16.
 */
public class SecondaryTreeBuilder {

    public String buildsecondaryTree(String requestedNodeChildren, HashMap collection) throws IOException {
        String icon = "img/human.png";
        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();

        String jsonChildren = "";
        if (requestedNodeChildren.equals("#")) {

            jsonChildren = "["
                    + "{\"children\":true,\""
                    + ",\"id\":\"HP:0000001\", \"text\": \"All\", "
                    + "\"state\": {\"opened\": true,"
                    + " \"selected\": false}}"
                    + "]";
        } else {
            HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);
            JSONArray children = new JSONArray();
            for (HPOTerm child : parent.getChildren()) {
                JSONObject childNode = new JSONObject(hj.createSubTree(
                        child, parent.getId()));

                children.put(childNode);
            }
            jsonChildren = children.toString();
        }
        return jsonChildren;
    }


}