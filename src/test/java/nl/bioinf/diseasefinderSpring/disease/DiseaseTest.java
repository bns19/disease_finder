package nl.bioinf.diseasefinderSpring.disease;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Created by bnsikkema on 30-3-16.
 */
public class DiseaseTest {

    /**
     * test with null arguments
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDiseaseConstructorI() {
        System.out.println();
        TreeMap tm = new TreeMap<>();
        Disease newDisease = new Disease(null,null, null);
    }

    /**
     * Test constructor with a omimnumber of wrong length
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDiseaseConstructorII() {

        TreeMap tm = new TreeMap<>();
        tm.put("pain", "yes");
        Disease newDisease = new Disease("testNumber","title", tm);

    }

    /**
     * Test constructor with an empty treeMap
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDiseaseConstructorIII() {
        TreeMap tm = new TreeMap<>();
        Disease newDisease = new Disease("012345","title", tm);

    }

    /**
     * Test constructor with an empty title
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDiseaseConstructorIV() {
        TreeMap tm = new TreeMap<>();
        tm.put("pain", "yes");
        Disease newDisease = new Disease("012345","", tm);

    }


    /**
     * Test constructor with valid arguments
     */
    @Test
    public void testDiseaseConstructorV() {
        TreeMap tm = new TreeMap<>();
        tm.put("pain", "yes");
        Disease newDisease = new Disease("012345","title", tm);

    }

}