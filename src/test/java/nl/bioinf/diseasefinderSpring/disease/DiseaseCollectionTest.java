package nl.bioinf.diseasefinderSpring.disease;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Created by bnsikkema on 29-3-16.
 */
public class DiseaseCollectionTest {

    /**
     * test constructor with an empty String list
     * @throws Exception
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testDiseaseCollectonConstructor() throws Exception {
        String[] givenFeatures = new String[]{};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
    }

    /**
     * test constructor with a single feature that does not contain one letter.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDiseaseCollectonConstructorII() throws Exception {
        String[] givenFeatures = new String[]{"09346892367346234634---="};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
    }

    /**
     * test constructor with a single feature that is an empty string.
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void testDiseaseCollectonConstructorIII() throws Exception {
        String[] givenFeatures = new String[]{""};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
    }

    /**
     * test constructor with tree valid features.
     * @throws Exception
     */
    @Test
    public void testDiseaseCollectonConstructorIV() throws Exception {
        String[] givenFeatures = new String[]{"symptom1", "symptom2","symptom3"};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
    }

    /**
     * test constructor with tree valid features and an invalid feature
     * @throws Exception
     */
    @Test
    public void testDiseaseCollectonConstructorV() throws Exception {
        String[] givenFeatures = new String[]{"symptom1", "symptom2","symptom3", "0"};
        DiseaseCollection instance = new DiseaseCollection(givenFeatures);
    }

    @Test
    public void testGetDiseaseCollection() throws Exception {
        System.out.println("getDiseaseCollection");
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