/*
 * This class is implemented, works fine, but it is not used yet
 * it could be interesting in future work with the application
 */
package synonyms;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class gets synonyms of words by the wordnet plugin
 * @author aroeters
 */
public class SynonymRetriever {

    /**
     * searches in the WordNetDatabase for synonyms of the given word.
     *
     * @param word the word to get synonyms for
     * @return an array with synonyms
     */
    public final ArrayList getSynonyms(final String word) {
        System.setProperty("wordnet.database.dir", "/commons/student/2014-2015/"
                + "Thema11/Mkslofstra/WordNet-3.0/dict");
        ArrayList synonyms = new ArrayList<String>();
        if (word != null) {
            //  Concatenate the command-line arguments
            StringBuilder buffer = new StringBuilder();
            buffer.append(word);
            String wordForm = buffer.toString();
            //  Get the synsets containing the word form
            WordNetDatabase database = WordNetDatabase.getFileInstance();
            Synset[] synsets = database.getSynsets(wordForm);
            //  Display the word forms and definitions for synsets retrieved
            if (synsets.length > 0) {
                for (Synset synset : synsets) {
                    String[] wordForms = synset.getWordForms();
                    if (synset.getTagCount(wordForm) > 3) {
                        synonyms.addAll(Arrays.asList(wordForms));
                    }
                }
            } else {
                System.err.println("No synset exists that resembles "
                        + "the word '" + wordForm + "'");
            }
        } else {
            System.err.println("You must specify "
                    + "a word where the synsets are needed from.");
        }
        return synonyms;
    }
}
