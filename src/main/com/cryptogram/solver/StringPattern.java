package main.com.cryptogram.solver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class creates patterns for each word to be used to match pairs
 *
 * @author Corbin Young
 */
final class StringPattern {
    
    /**
     * Creates the pattern for a word and returns it as a string. This method allows for up to 16 different
     *  letters in one word. Each different letter will add a new index to the key, while letters that have
     *  already been added will be ignored.
     *
     * Examples:
     *  THAT = 0120
     *  PEOPLE = 012031
     *  MISSISSIPPI = 01221221331
     *  WATERMELON = 0123453678
     *
     * @param word word to get the pattern from
     * @return the pattern of the word
     */
    static String getPattern(final String word) {
        final StringBuilder pattern = new StringBuilder();
        final Map<Character, String> key = new HashMap<>();

        int index = 0;
        
        for (char letter : word.toCharArray()) {
            if(!key.containsKey(letter)) {
                key.put(letter, Integer.toHexString(index));
                index++;
            }
            
            pattern.append(key.get(letter));
        }
        
        return pattern.toString();
    }
}
