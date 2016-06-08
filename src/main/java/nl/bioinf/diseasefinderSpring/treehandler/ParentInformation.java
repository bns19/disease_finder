package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreatorSecondaryTree;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOgetParentInformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hjdupon on 7-6-16.
 */
public class ParentInformation {
    JSONArray children = new JSONArray();

    public String getParentInformation(String requestedNodeChildren, final HashMap collection) throws IOException, JSONException {
        requestedNodeChildren = requestedNodeChildren.replaceAll("^\"|\"$", "");

        HPOgetParentInformation hpi = new HPOgetParentInformation();
        String jsonChildren = "";

        HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);

        getAllChildrenNodes(parent, hpi);

        jsonChildren = children.toString();

        return jsonChildren;
    }


    public void getAllChildrenNodes(HPOTerm parent, HPOgetParentInformation hpi) {
        if (parent.getChildren() != null) {
            for (HPOTerm child : parent.getChildren()) {
                JSONObject childNode = new JSONObject(hpi.getParentInfo(child, parent.getId()));
                getAllChildrenNodes(child, hpi);

                children.put(childNode);
                System.out.println(childNode);
            }

        }

    }

}
