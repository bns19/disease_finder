/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon on 3/1/2016.
 */
package nl.bioinf.diseasefinderSpring.searchpackage;

import model.Findtrait;

/**
 * this class connects the Phedimap 2.0 application and the J-algorithm system.
 * @author jgboekhoudt and Bnsikkema
 */
public class SearchSystem {

    /**
     * A findtrait opject containing the search results.
     */
    private Findtrait results;

    /**
     * This function traits symptoms for diseases through the J-algorithm.
     * @param symptoms the short symptoms or complete symptoms
     * @param runtime the maximum runtime per symptom
     * @throws Exception an exception from the J-algorithm (thrown when a wrong input is given)
     */
     public SearchSystem(final String symptoms, final int runtime) throws Exception {
        Findtrait bb = new Findtrait();
         if (runtime > 0) {
             bb.setCutoffTime(runtime);
         }
         System.out.println("eentestofhijhierkomt");
         bb.setDiseases(symptoms);
         System.out.println("test2222");
         System.out.println(bb.getFinalres() + "results");
         this.results = bb;

    }

    /**
     * getResults.
     * @return results the findtrait object containing the results.
     */
    public Findtrait getResults() {
        return this.results;
    }

}
