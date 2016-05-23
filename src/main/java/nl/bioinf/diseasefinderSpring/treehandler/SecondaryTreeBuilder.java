package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


/**
 * + * Created by henridupon on 20-05-16.
 * +
 */
public class SecondaryTreeBuilder {

    public String buildsecondaryTree(String requestedNodeChildren, HashMap collection) throws IOException, JSONException {
        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();
        String jsonChildren = "";


        HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);
        JSONArray children = new JSONArray();

        jsonChildren = children.toString();


        System.out.println(jsonChildren);
        return jsonChildren;
    }


}