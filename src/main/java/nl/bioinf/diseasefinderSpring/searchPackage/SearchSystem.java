/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 3/1/2016.
 */
package nl.bioinf.diseasefinderSpring.searchpackage;

import model.Findtrait;

/**
 *
 * @author jgboekhoudt and Bnsikkema
 */
public class SearchSystem{

    private Findtrait results;

 //   public static void main(String[] args) throws Exception {
        // TODO code application logic here
     public SearchSystem(String symptoms, int runtime) throws Exception {
        Findtrait bb = new Findtrait();
         if (runtime > 0) {
             bb.setCutoffTime(runtime);
         }
         System.out.println("eentestofhijhierkomt");
         bb.setDiseases(symptoms);
//       bb.setDiseases("Pain insensitivity,Impaired pain sensation,Abnormality of pain sensation,Sensory impairment,Peripheral neuropathy,Abnormal peripheral nervous system morphology,Abnormality of nervous system morphology,Abnormality of the nervous system,Phenotypic abnormality,Impaired pain sensation");

         System.out.println("test2222");
         System.out.println(bb.getFinalres()+ "results");
         this.results = bb;

    }

    public Findtrait getResults() {
        return this.results;
    }

}
