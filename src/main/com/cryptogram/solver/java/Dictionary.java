package main.com.cryptogram.solver;

import java.util.*;

/**
 * This class manages all of the english words read in from my file.
 *
 * I will explain the data structure I used below here:
 *
 *  The top level is a {@code Map}, where the key is the length of a given word
 *  The next level is another {@code Map}, where the key is the pattern of a word
 *  The final level is a {@code Set}, which contains unique words that are all of the same length and pattern
 *
 * @author Corbin Young
 */
final class Dictionary {

    private static final Dictionary instance = new Dictionary();

    private final Map<Integer, Map<String, Set<String>>> words = new HashMap<>();
    
    /**
     * Creates the instance of {@code Dictionary}
     */
    private Dictionary() {
    }

    /**
     * Returns the instance of {@code Dictionary}
     *
     * @return instance
     */
    static Dictionary getInstance() {
        return instance;
    }

    /**
     * This method adds a word to the dictionary
     *
     * @param word word to be added to the dictionary
     */
    final void addWord(final String word) {
        final String pattern = StringPattern.getPattern(word);
        final int key = word.length();
        
        words.computeIfAbsent(key, t -> new HashMap<>());
        words.get(key).computeIfAbsent(pattern, t -> new HashSet<>());
        words.get(key).get(pattern).add(word);
    }
    
    /**
     * This method returns a {@code Set} of words that all have the same length and pattern
     *
     * @param key specified length
     * @param pattern specified pattern
     * @return {@code Set} of words
     */
    final Set<String> getWordSubset(final int key, final String pattern) {
        return words.get(key).get(pattern);
    }
}
