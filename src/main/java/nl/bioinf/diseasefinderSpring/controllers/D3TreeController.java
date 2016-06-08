package nl.bioinf.diseasefinderSpring.controllers;


import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOFileLoader;
import nl.bioinf.diseasefinderSpring.treehandler.ParentInformation;
import nl.bioinf.diseasefinderSpring.treehandler.PrimaryTreeBuilder;
import nl.bioinf.diseasefinderSpring.treehandler.SecondaryTreeBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by bnsikkema on 18-4-16.
 */

@Controller
public class D3TreeController {


    @RequestMapping(value = "termsToTree", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray processRequest(String autoCompleteResult) throws IOException {
        HashMap collection = HPOFileLoader.LoadHPOFile();
        String autoComp = autoCompleteResult.toLowerCase();
        TermsToPrimaryTreeProcessor tTPTP = new TermsToPrimaryTreeProcessor(collection, autoComp);
        return tTPTP.getNodes();

    }

    @RequestMapping(value = "treeBuilder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String buildBaseTree(String id, String icon) throws IOException {
        HashMap collection = HPOFileLoader.LoadHPOFile();
        String requestedNodeChildren = id;
        PrimaryTreeBuilder primaryTreeBuilder = new PrimaryTreeBuilder();
        String jsonChildren = primaryTreeBuilder.buildPrimaryTree(requestedNodeChildren, icon, collection);
        return jsonChildren;

    }

    @RequestMapping(value = "secondTreeBuilder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String buildSecondTree(String id) throws IOException, JSONException {

        ArrayList<String> jsonChildrenList = new ArrayList<String>();
        String jsonChildren = "";

        HashMap collection = HPOFileLoader.LoadHPOFile();
        SecondaryTreeBuilder secTreeBuilder = new SecondaryTreeBuilder();
        jsonChildren = secTreeBuilder.buildsecondaryTree(id, collection);
        jsonChildrenList.add(jsonChildren);

        return jsonChildrenList.toString();
    }

    @RequestMapping(value = "parentTreeBuilder", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getParentInformation(String id) throws IOException, JSONException {

        List<String> ParentIditems = Arrays.asList(id.split("\\s*,\\s*"));

        List<String> items = new LinkedList<String>(Arrays.asList(id.split("\\s*,\\s*")));
        ArrayList<String> jsonChildrenList = new ArrayList<String>();
        String jsonChildren = "";

        for (String item : items) {

            if (item.equals("#")){
                items.remove(item);
            }
            else{

            HashMap collection = HPOFileLoader.LoadHPOFile();
            ParentInformation parentInf = new ParentInformation();

            jsonChildren = parentInf.getParentInformation(item, collection);
            jsonChildrenList.add(jsonChildren);
            }
        }

        return jsonChildrenList.toString();
    }

}


