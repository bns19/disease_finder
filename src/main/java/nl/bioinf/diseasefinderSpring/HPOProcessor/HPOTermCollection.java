/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.util.HashMap;

/**
 *
 * @author aroeters
 */
public class HPOTermCollection {
        /**
         * Contains the HPOTerm objects.
         */
    private final HashMap<String, HPOTerm> hpoHashMap =
            new HashMap<String, HPOTerm>();
    /**
     * Puts a hpo term into the hpoHashMap.
     * @param id the id of the hpoTerm
     * @param hpoTerm Object of a HPOTerm
     */
    public final void addToHPOHashMap(final String id, final HPOTerm hpoTerm) {
        if (hpoTerm == null) {
            throw new NullPointerException("hpoTerm is empty");
        }
        hpoHashMap.put(id, hpoTerm);
    }
    /**
     * The getter of the hpoList.
     * @return list containing the HPOTerm objects
     */
    public final HashMap getHPOHashMap() {
        return hpoHashMap;
    }
}
