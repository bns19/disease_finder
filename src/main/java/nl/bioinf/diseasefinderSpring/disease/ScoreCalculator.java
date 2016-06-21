/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.disease;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class will check if hits are true positive and score them.
 *
 * @author mkslofstra
 */
public class ScoreCalculator {

    /**
     * matchCount is the HashMap with the occurrence of all matches in the
     * search.
     */
    private final HashMap<String, Double> matchCount = new HashMap();
    /**
     * The diseasecollection which is getting a score.
     */
    private final HashMap diseases;
    /**
     * diseaseScore is the score given the matches and their score.
     */
    private final HashMap<String, Double> diseaseScore = new HashMap();

    /**
     * ScoreCalculator calculates the score of a disease hit. This is the
     * constructor.
     *
     * @param collection the diseasecollection to calculate the score on.
     */
    public ScoreCalculator(final DiseaseCollection collection) {
        diseases = collection.getDiseaseCollection();
        this.processMatches();
        this.processDiseases();
        collection.sortDiseaseCollection();
    }

    /**
     * countMatches fills the HashMap matchCount which contains all the matches
     * and their number of occurrence through the search.
     *
     * @param matches the list of matches of a certain disease.
     */
    private void countMatches(final List matches) {
        String matchWord;
        for (Object match : matches) {
            if (match.toString().startsWith(" ")) {
                matchWord = match.toString().substring(1);
            } else {
                matchWord = match.toString();
            }
            if (matchWord.endsWith(" ")) {
                matchWord = matchWord.substring(0, matchWord.length() - 1);
            }
            if (!matchCount.containsKey(matchWord)) {
                matchCount.put(matchWord, 1.0);
            } else {
                Double total = matchCount.get(matchWord) + 1;
                matchCount.put(matchWord, total);
            }
        }
    }

    /**
     * This void processes the matches and counts them.
     */
    private void processMatches() {
        HashMap<String, Disease> diseasesCopy =
                (HashMap<String, Disease>) diseases.clone();
        Iterator it = diseasesCopy.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String omimNumber = (String) pair.getKey();
            Disease disease = (Disease) pair.getValue();
            List matches = disease.getMatches();
            this.countMatches(matches);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * This function processes diseases and calculates their
     * score by calling methords.
     */
    private void processDiseases() {
        HashMap<String, Disease> diseasesCopy =
                (HashMap<String, Disease>) diseases.clone();
        Iterator it = diseasesCopy.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String omimNumber = (String) pair.getKey();
            Disease disease = (Disease) pair.getValue();
            List matches = disease.getMatches();
            Double score = this.calculateDiseaseScore(omimNumber, matches);
            score = new BigDecimal(score).setScale(3, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
            disease.setScore(score);
            disease.setHits(matches.size());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * This void fills diseaseScore given the matches a disease has and their
     * importance.
     *
     * @param omimNumber the omimNumber of the disease
     * @param matches the total number of matches
     * @return the score for the disease
     */
    private Double calculateDiseaseScore(final String omimNumber,
            final List matches) {
        //score per match = 1/totalOfMatch,
        //which will give a max score of 1 per match.
        Double score = 0.0;
        String matchWord;
        for (Object match : matches) {
            if (match.toString().startsWith(" ")) {
                matchWord = match.toString().substring(1);
            } else {
                matchWord = match.toString();
            }
            if (matchWord.endsWith(" ")) {
                matchWord = matchWord.substring(0, matchWord.length() - 1);
            }
            score += 1 / matchCount.get(matchWord);
        }
        return score;
    }
}
