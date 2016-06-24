/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 7-5-16.
 */
package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.util.HashMap;

/**
 *
 */
public class HPOFileLoader {
    private HPOFileLoader() {};

    /**
     * @return
     * @throws java.io.IOException
     */
    public static HashMap LoadHPOFile() throws java.io.IOException {
        //HPOJsonObjectCreator hj = new HPOJsonObjectCreator();

        String path = HPOJsonObjectCreator.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        HashMap collection = hr.readFile().getHPOHashMap();
        return collection;

    }

}
