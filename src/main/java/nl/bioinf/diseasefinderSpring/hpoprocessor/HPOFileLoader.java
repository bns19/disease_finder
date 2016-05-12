package nl.bioinf.diseasefinderSpring.hpoprocessor;

import java.util.HashMap;

/**
 * Created by bas on 7-5-16.
 */
public class HPOFileLoader {
    private HPOFileLoader() {};

    public static HashMap LoadHPOFile() throws java.io.IOException {
        //HPOJsonObjectCreator hj = new HPOJsonObjectCreator();

        String path = HPOJsonObjectCreator.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        HashMap collection = hr.readFile().getHPOHashMap();
        return collection;

    }

}
