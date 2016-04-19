package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOFileReader;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOTerm;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by bnsikkema on 18-4-16.
 */

@Controller
public class D3TreeController {


    @RequestMapping(value = "termsToTree")
    public void processRequest(@RequestParam String autoCompValues) throws IOException {

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
//        out.println(nodesToShow);
//        out.close();

    }

}
