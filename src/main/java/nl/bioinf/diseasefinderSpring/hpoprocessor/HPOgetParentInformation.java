package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hjdupon on 7-6-16.
 */
public class HPOgetParentInformation {

    /**
     * Contains the hpoTerms.
     */
    private final HashMap<String, HPOTerm> hpoCollection;


    /**
     * The constructor of the class.
     *
     * @throws IOException when the file is not found
     */
    public HPOgetParentInformation() throws IOException {

        String path = HPOJsonObjectCreatorSecondaryTree.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        this.hpoCollection = hr.readFile().getHPOHashMap();
    }

    /**
     * Creates the child branch of the requested parent.
     *
     * @param hpoTerm the HPoTerm object of the parent
     * @param parent  the id of the parent
     * @return a HashMap with all the information for jsTree.js
     */
    public final HashMap getParentInfo(final HPOTerm hpoTerm, final String parent) {
        HashMap tree = new HashMap();
        tree.put("id", hpoTerm.getId());
        tree.put("name", hpoTerm.getName());
        return tree;
    }
}