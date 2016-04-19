/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.HPOProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author aroeters
 */
public class HPOFileReader {

    /**
     * Contains the name of the file including the path to the file.
     */
    private final String file;
  //  private final File file;
    /**
     * The constructor of the class.
     *
     * @param inFile the file that contains the HPO terms
     * @throws IOException when file not found
     */
    public HPOFileReader(final String inFile) throws IOException {
       // File hpFile = new File(inFile);
       // file = hpFile;
        file = inFile;
        if (inFile == null) {
            throw new NullPointerException();
        }
    }
    /**
     * Reads the file line by line and processes it.
     *
     * @return HPOTermCollection
     * @throws IOException when file not found
     */
    public final HPOTermCollection readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        HPOTermCollection hc = new HPOTermCollection();
        String line;
        HPOTerm hp = new HPOTerm();
        while ((line = br.readLine()) != null) {
            if (line.startsWith("id:")) {
                hp.setId(line.substring(4));
            } else if (line.startsWith("name:")) {
                hp.setName(line.substring(6));
            } else if (line.startsWith("def:")) {
                hp.setDef(line.substring(5));
            } else if (line.startsWith("is_a:")) {
                hp.addIsA(line.substring(6, 16));
            } else if (line.contains("Term")) {
                if (hp.getId() != null) {
                    hc.addToHPOHashMap(hp.getId(), hp);
                    hp = new HPOTerm();
                }
            }
        }
        // To add the last HPOTerm
        hc.addToHPOHashMap(hp.getId(), hp);
        HashMap allTerms = hc.getHPOHashMap();
        for (Object key : allTerms.keySet()) {
            HPOTerm child = (HPOTerm) allTerms.get(key);
            List parents = child.getIsA();
            if (!parents.isEmpty()) {
                for (Object parent : parents) {
                    HPOTerm childReceiver = (HPOTerm) allTerms.get(parent);
                    childReceiver.addChild(child);
                }
            }
        }
        return hc;
    }

}
