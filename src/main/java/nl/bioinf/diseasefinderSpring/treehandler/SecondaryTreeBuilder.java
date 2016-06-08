package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreatorSecondaryTree;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * + * Created by henridupon on 20-05-16.
 * +
 */
public class SecondaryTreeBuilder {
    JSONArray children = new JSONArray();

    public String buildsecondaryTree(String requestedNodeChildren, final HashMap collection) throws IOException, JSONException {
        requestedNodeChildren = requestedNodeChildren.replaceAll("^\"|\"$", "");

        HPOJsonObjectCreatorSecondaryTree hj = new HPOJsonObjectCreatorSecondaryTree();
        String jsonChildren = "";

        HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);

        getAllChildrenNodes(parent, hj);

        jsonChildren = children.toString();

        return jsonChildren;
    }


    public void getAllChildrenNodes(HPOTerm parent, HPOJsonObjectCreatorSecondaryTree hj) {
        if (parent.getChildren() != null) {
            for (HPOTerm child : parent.getChildren()) {
                JSONObject childNode = new JSONObject(hj.createSubTree(child, parent.getId()));
                getAllChildrenNodes(child, hj);

                children.put(childNode);
            }

        }

    }

}