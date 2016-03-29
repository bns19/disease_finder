package nl.bioinf.diseasefinderSpring.disease;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Created by bnsikkema on 29-3-16.
 */
public class DiseaseCollectionTest {



    @Test
    public void testGetDiseaseCollection() throws Exception {
        System.out.println("getDiseaseCollection");
        String mimNumber = "666666";
        TreeMap features = new TreeMap();
        features.put("Horns", "fgsg");
        features.put("Evil", "yes");
        Disease devilsDisease = new Disease("666666", "devils disease",
                features);
        String[] givenFeatures = new String[]{"dizziness", "blurry vision",
                "ptosis"};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
        instance.addToDiseaseCollection(devilsDisease, mimNumber);
        HashMap<String, Disease> result = instance.getDiseaseCollection();
        assertTrue(result.containsKey("666666") && result.get("666666")
                != null);
        System.out.println(devilsDisease.getFeatures());


    }

    @Test
    public void testAddToDiseaseCollection() throws Exception {
        System.out.println("addToDiseaseCollection");
        String mimNumber = "666666";
        TreeMap features = new TreeMap();
        features.put("Horns", "yes");
        features.put("Evil", "yes");
        Disease devilsDisease = new Disease("666666", "devils disease",
                features);
        String[] givenFeatures = new String[]{"dizziness", "blurry vision",
                "ptosis"};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
        instance.addToDiseaseCollection(devilsDisease, mimNumber);
    }
}