package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreatorSecondaryTree;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOgetParentInformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hjdupon on 7-6-16.
 */
public class ParentInformation {
    JSONArray children = new JSONArray();

    public String getParentInformation(String requestedNodeChildren, final HashMap collection) throws IOException, JSONException {
        requestedNodeChildren = requestedNodeChildren.replaceAll("^\"|\"$", "");

        HPOgetParentInformation hpi = new HPOgetParentInformation();

        HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);

        JSONObject childNode = new JSONObject();

        childNode.put("id", parent.getId());
        childNode.put("name", parent.getName());

        children.put(childNode);

        String jschilds = children.toString();

        return jschilds;
    }


}

