/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author aroeters
 */
public class HPOJsonObjectCreatorSecondaryTree {

    /**
     * Contains the hpoTerms.
     */
    private final HashMap<String, HPOTerm> hpoCollection;


    /**
     * The constructor of the class.
     *
     * @throws IOException when the file is not found
     */
    public HPOJsonObjectCreatorSecondaryTree() throws IOException {

        String path = HPOJsonObjectCreatorSecondaryTree.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        this.hpoCollection = hr.readFile().getHPOHashMap();

    }

    /**
     * Creates the child branch of the requested parent.
     *
     * @param hpoTerm the HPoTerm object of the parent
     * @param parent the id of the parent
     * @return a HashMap with all the information for jsTree.js
     */
    public final HashMap createSubTree(final HPOTerm hpoTerm,
            final String parent) {
        HashMap tree = new HashMap();
        HashMap<String, Boolean> state = new HashMap<String, Boolean>();
        state.put("opened", false);
        state.put("selected", false);
        tree.put("id", hpoTerm.getId());
        tree.put("name", hpoTerm.getName());
        tree.put("parent", parent);
        tree.put("state", state);
        return tree;
    }
}
