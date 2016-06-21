package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
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

    /**
     * @param requestedNodeChildren
     * @param collection
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public String getParentInformation(String requestedNodeChildren, final HashMap collection) throws IOException, JSONException {
        requestedNodeChildren = requestedNodeChildren.replaceAll("^\"|\"$", "");

        HPOTerm node = (HPOTerm) collection.get(requestedNodeChildren);

        JSONObject childNode = new JSONObject();
        childNode.put("id", node.getId());
        childNode.put("name", node.getName());

        Integer count = 0;
        for(Object item : node.getIsA()){
            if (count == 0){
                String nodeGetIsA = item.toString();
                nodeGetIsA.replaceAll("\\[", "").replaceAll("\\]","");
                childNode.put("parentid", nodeGetIsA);

                // call method recursively to get all the parent nodes and their id and name
                getParentInformation(nodeGetIsA, collection);

                count += 1;
            }
        }

        // Add the new node to the jschilds list
        children.put(childNode);
        String jschilds = children.toString();

        return jschilds;
    }
}

