/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon on 20-05-16.
 */
package nl.bioinf.diseasefinderSpring.treehandler;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreatorSecondaryTree;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;


/**
 * Controls the information of the selected node and the parent nodes.
 */
public class SecondaryTreeBuilder {
    JSONArray children = new JSONArray();

    /**
     * @param requestedNodeChildren
     * @param collection
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public String buildsecondaryTree(String requestedNodeChildren, final HashMap collection) throws IOException, JSONException {
        HPOJsonObjectCreatorSecondaryTree hj = new HPOJsonObjectCreatorSecondaryTree();
        String jsonChildren = "";

        HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);
        getAllChildrenNodes(parent, hj);
        jsonChildren = children.toString();

        return jsonChildren;
    }


    /**
     * Controls the information to get all the child nodes from the (selected) parent node.
     * @param parent node (selected node).
     * @param hj HPO file information.
     */
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