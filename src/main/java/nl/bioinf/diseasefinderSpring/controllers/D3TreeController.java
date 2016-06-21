/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bnsikkema en Henri du Pon on 18-4-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOFileLoader;
import nl.bioinf.diseasefinderSpring.treehandler.ParentInformation;
import nl.bioinf.diseasefinderSpring.treehandler.PrimaryTreeBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.*;

@Controller
public class D3TreeController {

    /**
     * @param autoCompleteResult
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "termsToTree", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray processRequest(String autoCompleteResult) throws IOException {

        HashMap collection = HPOFileLoader.LoadHPOFile();
        String autoComp = autoCompleteResult.toLowerCase();
        TermsToPrimaryTreeProcessor tTPTP = new TermsToPrimaryTreeProcessor(collection, autoComp);

        return tTPTP.getNodes();
    }

    /**
     * @param id of the selected node in the primary tree.
     * @param icon of the selected node in the primary tree.
     * @return list of children of the node.
     * @throws IOException
     */
    @RequestMapping(value = "treeBuilder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String buildBaseTree(String id, String icon) throws IOException {

        HashMap collection = HPOFileLoader.LoadHPOFile();
        String requestedNodeChildren = id;
        PrimaryTreeBuilder primaryTreeBuilder = new PrimaryTreeBuilder();
        String jsonChildren = primaryTreeBuilder.buildPrimaryTree(requestedNodeChildren, icon, collection);

        return jsonChildren;
    }

    /**
     * @param ids is a list to string of all the parent nodes of the selected node in the primary tree.
     * @return a list to Json-string of all the parents with id, parent, children, text/name.
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "parentTreeBuilder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getParentInformation(String ids) throws IOException, JSONException {

        List<String> items = new LinkedList<String>(Arrays.asList(ids.split("\\s*,\\s*")));
        ArrayList<String> jsonChildrenList = new ArrayList<String>();
        String jsonChildren = "";

        for (String item : items) {

            // remove the root (#)
            if (item.equals("#")) {
                items.remove(item);
            } else {
                HashMap collection = HPOFileLoader.LoadHPOFile();
                ParentInformation parentInf = new ParentInformation();

                jsonChildren = parentInf.getParentInformation(item, collection);
                jsonChildrenList.add(jsonChildren);
            }
        }
        return jsonChildrenList.toString();
    }
}


