///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package nl.bioinf.diseasefinderSpring.searchPackage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import model.Findtrait;

import model.Findtrait;

/**
 *
 * @author jgboekhoudt and Bnsikkema
 */
//public class NewMain {
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
