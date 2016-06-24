/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon on 7-6-16.
 */
package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;

/**
 * Returns the information of all the parent nodes of the selected node in the tree.
 */
public class ParentInformation {
    JSONArray children = new JSONArray();

    /**
     * finds all the parent information of the selected node by searching through the HPO file.
     * @param requestedNodeChildren
     * @param collection
     * @return List of all the parent and child nodes to create the secondary tree.
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

