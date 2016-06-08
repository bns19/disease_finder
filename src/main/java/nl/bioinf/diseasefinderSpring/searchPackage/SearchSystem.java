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

import java.util.List;

/**
 *
 * @author jgboekhoudt
 */
//public class SearchSystem {
////
////    /**
////     * @param args the command line arguments
////     */
//    public static void main(String[] args) throws Exception {
//        // TODO code application logic here
//        Findtrait bb = new Findtrait("large ears,fat,autism,bad breath,no head control,small toes,black nail,albinism,cancer,blood cells");
//        bb.setDiseases("large ears,fat,autism,bad breath,no head control,small toes,black nail,albinism,cancer,blood cells");
//        System.out.println(bb.getFinalres().size());
//        int count = 0;
//        System.out.println(bb.getFinalres());
//        for (List<String> i : bb.getFinalres().keySet()) {
//            count++;
//
//            System.out.println(count);
//            System.out.println("Disorder    " + i.get(1));
//            System.out.println("id    " + i.get(0));
//            System.out.println("match    " + bb.getFinalres().get(i) + "\n");
//
//        }
//    }
//
//}
// /
//import model.Findtrait;
//
//import java.util.List;

/**
 *
 * @author jgboekhoudt
 */
//public class NewMain {
public class SearchSystem{

    private Findtrait results;

 //   public static void main(String[] args) throws Exception {
        // TODO code application logic here
     public SearchSystem(String symptoms) throws Exception {
//        Findtrait bb = new Findtrait("large ears,fat,autism,bad breath,no head control,small toes,black nail,albinism,cancer,blood cells");
        Findtrait bb = new Findtrait();
         bb.setCutoffTime(30000);
         bb.setDiseases("large ears,fat,autism,bad breath,no head control,small toes,black nail,albinism,cancer,blood cells");
       // System.out.println(bb.getFinalres().size());
       // this.results = new Findtrait("large ears,fat,autism,bad breath,no head control,small toes,black nail,albinism,cancer,blood cells");
        //this.results = new Findtrait(symptoms);
        this.results = bb;
        //int count = 0;
      //  System.out.println(bb.getFinalres());
//        for (List<String> i : bb.getFinalres().keySet()) {
//            count++;
//
//            System.out.println(count);
//            System.out.println("Disorder    " + i.get(1));
//            System.out.println("id    " + i.get(0));
//            System.out.println("match    " + bb.getFinalres().get(i) + "\n");

        //}
    }

    public Findtrait getResults() {
        return this.results;
    }
//
}
