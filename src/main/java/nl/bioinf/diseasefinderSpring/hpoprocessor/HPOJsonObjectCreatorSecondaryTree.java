/**
 * Project: Disease Finder
 * Theme 11/12
 * Created at 15-6-2016
 */
package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that is responsible for the information about the parents that are selected in the primary tree.
 */
public class HPOJsonObjectCreatorSecondaryTree {

    /**
     * Contains the hpoTerms.
     */
    private final HashMap<String, HPOTerm> hpoCollection;


    /**
     * The constructor of the class.
     * @throws IOException when the file is not found
     */
    public HPOJsonObjectCreatorSecondaryTree() throws IOException {

        String path = HPOJsonObjectCreatorSecondaryTree.class.getClassLoader().getResource("config/hp.obo").toString();
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
    public final HashMap createSubTree(final HPOTerm hpoTerm, final String parent) {

        HashMap tree = new HashMap();
        tree.put("id", hpoTerm.getId());
        tree.put("name", hpoTerm.getName());
        tree.put("parent", parent);

        return tree;
    }
}
