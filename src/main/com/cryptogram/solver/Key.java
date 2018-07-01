package main.com.cryptogram.solver;

import main.com.cryptogram.solver.stackStuff.ADTStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is in charge of managing the key for the cryptogram
 *
 * @author Corbin Young
 */
final class Key {

    private static final Key instance = new Key();

    private final Map<Character, Character> key = new HashMap<>();
    
    /**
     * Creates the instance of {@code Key}
     */
    private Key() {
    }
    
    /**
     * This method removes all of the letters of a word from the key
     *
     * @param word word that needs to have its letters removed from the key
     */
    private void removeFromKey(final String word) {
        for(char letter : word.toCharArray())
            key.remove(letter);
    }

    /**
     * Returns the instance of {@code Key}
     *
     * @return instance
     */
    static Key getInstance() {
        return instance;
    }

    /**
     * Checks to make sure that a pair (one encrypted and one decrypted word) are compatible
     *  with the current version of the key. If they are, then that pair is added to the key.
     *
     * Instances where a match is not valid:
     *  1. No letter can decrypt to itself
     *  2. If the encrypted letter already exists but matches to a different decrypted letter
     *  3. If the decrypted letter already exists but matches to a different encrypted letter
     *
     * @param word encrypted word to be added to key
     * @param eWord decrypted word to be added to key
     * @return true if pair is compatible, false if it's not
     */
    final boolean addToKey(final String word, final String eWord) {
        char[] letters = word.toCharArray();
        char[] eLetters = eWord.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            if(letters[i] == eLetters[i]) {
                return false;
            } else if(key.containsKey(letters[i])) {
                if(key.get(letters[i]) != eLetters[i])
                    return false;
            } else if(key.containsValue(eLetters[i])) {
                for(Character entry : key.keySet())
                    if(key.get(entry) == eLetters[i] && entry != letters[i])
                        return false;
            }
        }
    
        for(int i = 0; i < letters.length; i++) {
            key.put(letters[i], eLetters[i]);
        }
        
        return true;
    }
    
    /**
     * This method updates the {@code Key} when a word gets removed from the {@code Stack}
     *
     * Algorithm:
     *  1. Pop one {@code Index} off the {@code Stack}
     *  2. Re-input all other pairs still on the {@code Stack} to ensure that no older word had the same key/value pair
     *
     * @param stack the current {@code Stack} of words that have been solved
     * @param words {@code List} of all the encrypted words
     * @return the {@code Index} that got popped off the {@code Stack}
     */
    final Index updateKey(final ADTStack<Index> stack, final List<String> words) {
        Index removed = stack.pop();
        removeFromKey(words.get(removed.getWI()));
        
        String word;
        String eWord;
        
        for(Index index : stack.getStackAsList()) {
            word = words.get(index.getWI());
            eWord = (String) Dictionary.getInstance().getWordSubset(word.length(), StringPattern.getPattern(word)).toArray()[index.getMI()];
            addToKey(word, eWord);
        }
        
        return removed;
    }

    final Map<Character, Character> getKey() {
        return key;
    }

    /**
     * This method clears all items in the {@code Key}
     */
    final void clear() {
        key.clear();
    }
}
