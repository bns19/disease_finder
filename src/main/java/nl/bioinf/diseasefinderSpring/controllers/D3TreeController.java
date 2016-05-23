package nl.bioinf.diseasefinderSpring.controllers;


import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOFileLoader;
import nl.bioinf.diseasefinderSpring.treehandler.PrimaryTreeBuilder;
import nl.bioinf.diseasefinderSpring.treehandler.SecondaryTreeBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public List buildSecondTree(String id) throws IOException, JSONException {

        ArrayList<String> jsonChildrenList = new ArrayList<String>();


            String jsonChildren = "";

            HashMap collection = HPOFileLoader.LoadHPOFile();
            String requestedNodeChildren = id;
            SecondaryTreeBuilder secTreeBuilder = new SecondaryTreeBuilder();
            jsonChildren = secTreeBuilder.buildsecondaryTree(requestedNodeChildren, collection);

            System.out.println("JSONCHILD: " + jsonChildren);

            jsonChildrenList.add(jsonChildren);



        return jsonChildrenList;
    }
}


