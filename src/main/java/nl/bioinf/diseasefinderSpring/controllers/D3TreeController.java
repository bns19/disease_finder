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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        List<String> items = new LinkedList<String>(Arrays.asList(id.split("\\s*,\\s*")));
        items.add(0, "#");

        ArrayList<String> jsonChildrenList = new ArrayList<String>();
        ArrayList<String> jsonChildrenListForAdding = new ArrayList<String>();
        String jsonChildren = "";

        for (String item : items) {;

            HashMap collection = HPOFileLoader.LoadHPOFile();
            String requestedNodeChildren = item;
            SecondaryTreeBuilder secTreeBuilder = new SecondaryTreeBuilder();
            jsonChildren = secTreeBuilder.buildsecondaryTree(requestedNodeChildren, collection);
            jsonChildrenListForAdding.add(jsonChildren);
            jsonChildrenList.add(jsonChildren);
        }

        for (String jsonchild : jsonChildrenList){

            Pattern pattern = Pattern.compile("([0-9]{7})");
            Matcher matcher = pattern.matcher(jsonchild);
            if (matcher.find())
            {
                System.out.println(matcher.group(1));
            }

            System.out.println(jsonchild);




        }

        return jsonChildrenList;
    }
}


