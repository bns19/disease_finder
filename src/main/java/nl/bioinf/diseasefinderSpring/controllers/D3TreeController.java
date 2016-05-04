package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.Application;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOFileReader;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOTerm;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by bnsikkema on 18-4-16.
 */

@Controller
public class D3TreeController {


    @RequestMapping(value = "termsToTree", method = RequestMethod.GET)
    public JSONArray processRequest(@RequestParam("term") String autoCompValues) throws IOException {

        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();
        String path = Application.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        HashMap collection = hr.readFile().getHPOHashMap();

        //PrintWriter out = response.getWriter();
        String autoComp = autoCompValues.toLowerCase();
//        String autoComp = request.getParameter("autoCompleteResult")
//                .toLowerCase();

        JSONArray nodesToShow = new JSONArray();
        String id = "";
        for (Object key: collection.keySet()) {
            HPOTerm node = (HPOTerm) collection.get(key.toString());
            if (node.getName().equalsIgnoreCase(autoComp)) {
                id = node.getId();
            }
        }
        while (!id.equals("HP:0000001")) {
            HPOTerm term = (HPOTerm) collection.get(id);
            nodesToShow.put(term.getId());
            id = term.getIsA().get(0).toString();
        }
        return nodesToShow;
//        out.println(nodesToShow);
//        out.close();

    }
    @ResponseBody
    @RequestMapping(value = "treeBuilder")
    public String buildBaseTree(String id, String icon) throws IOException {
      //  response.setContentType("application/json");

        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();
        String path = Application.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        HashMap collection = hr.readFile().getHPOHashMap();
        //String requestedNodeChildren = request.getParameter("id")
        String requestedNodeChildren = id;
        HashMap<String, String> icons = new HashMap<String, String>();
        icons.put("HP:0000005", "img/dna.png");
        icons.put("HP:0000118", "img/human.png");
        icons.put("HP:0040006", "img/skull.png");
        icons.put("HP:0012823", "img/clock.png");
        String jsonChildren = "";
        if (requestedNodeChildren.equals("#")) {
            /*based on request attribute "id", fetch the children for that ID.*/
            jsonChildren = "["
                    + "{\"children\":true,\"icon\":\"glyphicon glyphicon-user\""
                    + ",\"id\":\"HP:0000001\", \"text\": \"All\", "
                    + "\"state\": {\"opened\": true,"
                    + " \"selected\": false}}"
                    + "]";
        } else {
            HPOTerm parent = (HPOTerm) collection.get(requestedNodeChildren);
            JSONArray children = new JSONArray();
            for (HPOTerm child : parent.getChildren()) {
                JSONObject childNode = new JSONObject(hj.createSubTree(
                        child, parent.getId()));
                if (icons.containsKey(child.getId())) {
                    childNode.put("icon", icons.get(child.getId()));
                } else {
                    //childNode.put("icon", request.getParameter("icon"));
                    childNode.put("icon", icon);
                }
                children.put(childNode);
            }
            jsonChildren = children.toString();
        }
//        PrintWriter out = response.getWriter();
//        out.write(jsonChildren);
//        out.close();
        System.out.println("tst");
        return jsonChildren;
    }
}


