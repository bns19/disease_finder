package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by bas on 7-5-16.
 */
public class PrimaryTreeBuilder {
    public PrimaryTreeBuilder() {
    };

    public String buildPrimaryTree(String requestedNodeChildren, String icon, HashMap collection) throws IOException {
        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();
        HashMap<String, String> icons = new HashMap<String, String>();
        icons.put("HP:0000005", "img/dna.png");
        icons.put("HP:0000118", "img/human.png");
        icons.put("HP:0040006", "img/skull.png");
        icons.put("HP:0012823", "img/clock.png");
        String jsonChildren = "";
        if (requestedNodeChildren.equals("#")) {
            jsonChildren = "["
                    + "{\"children\":true,\"icon\":\"glyphicon glyphicon-user\""
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
                if (icons.containsKey(child.getId())) {
                    childNode.put("icon", icons.get(child.getId()));

                } else {
                    //childNode.put("icon", request.getParameter("icon"));
                    childNode.put("icon", icon);
                }
                children.put(childNode);
            }
            jsonChildren = children.toString();
        }
        return jsonChildren;
    }


}

