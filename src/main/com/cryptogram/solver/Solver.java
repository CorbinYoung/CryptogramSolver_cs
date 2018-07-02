package main.com.cryptogram.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class is in charge of the algorithm for solving the cryptogram.
 *
 * @author Corbin Young
 */
public final class Solver {

    private static final Solver instance = new Solver();

    private final Key key = Key.getInstance();
    private final Dictionary dictionary = Dictionary.getInstance();
    private final DataStorage storage = DataStorage.getInstance();
    private final Stack<Index> stack = new Stack<>();

    /**
     * Creates the instance of the {@code Solver}
     */
    private Solver() {
        DataReader.readWords();
    }

    /**
     * This method resets the {@code Key}, {@code DataStorage}, and the {@code Stack}.
     */
    private void reset() {
        stack.clear();
        storage.clear();
        key.clear();
    }

    /**
     * This method checks pairs of an encrypted word with its set of decryptions against the {@code Key}
     *
     * @param word encrypted word
     * @param index starting index in the list of decrypted matches
     * @return the index of a pair that was successfully inserted into the key
     */
    private int pickMatch(final String word, int index) {
        Set<String> mySet = dictionary.getWordSubset(word.length(), StringPattern.getPattern(word));
        
        if(mySet == null)
            throw new RuntimeException("No matches found for: " + word);
            
        List<String> myList = new ArrayList<>(mySet);
        
        for(; index < myList.size(); index++) {
            if(key.addToKey(word, myList.get(index)))
                return index;
        }
        
        return -1;
    }
    
    /**
     * General algorithm to solve the cryptogram
     *
     *  1. Pick a match with the first word starting at index 0
     *  2. Push the indexes of the word and its decryption onto the stack
     *  3. Increment the index for the decrypted words and pick another match to add to the stack
     *  4. While no match is found that could be added to the key and we haven't removed everything from the stack
     *      4a. Pop a match off the stack and reset the key
     *      4b. Go back to the previous encrypted word and find a different match
     *  5. If the stack is now empty and we still haven't found a valid match, the cryptogram is unsolvable
     *  6. Otherwise, push our new match on the stack and repeat 2-6
     */
    private void createKey() {
        int wI = 0, mI;
        
        Index index;
        
        final List<String> words = storage.getData();
        
        mI = pickMatch(words.get(wI), 0);
        
        stack.push(new Index(wI, mI));
        
        while(stack.size() < words.size() && !stack.isEmpty()) {
            index = stack.peek();
            
            wI = index.getWI() + 1;
            mI = pickMatch(words.get(wI), 0);
            
            while(mI < 0 && !stack.isEmpty()) {
                index = key.updateKey(stack, words);
                wI = index.getWI();
                mI = pickMatch(words.get(wI), index.getMI() + 1);
            }
                
            if(mI < 0)
                throw new RuntimeException("Cryptogram is unsolvable");
    
            stack.push(new Index(wI, mI));
        }
    }
    
    /**
     * This method translates the original encrypted message into the final decrypted form based on
     *  the {@code Key}.
     *
     * @param encryptedMsg original encrypted message
     * @return the final decrypted message
     */
    private String translate(final String encryptedMsg) {
        StringBuilder decryptedMsg = new StringBuilder();
        
        for (char letter : encryptedMsg.toCharArray()) {
            switch(letter) {
                case ' ': case '\'':
                case '\"': case ',':
                case '-': case '!':
                case ';': case ':':
                case '.': case '?':
                case '\n':
                    decryptedMsg.append(letter);
                    break;
                default:
                    decryptedMsg.append(key.getKey().get(letter));
            }
        }
        
        return decryptedMsg.toString();
    }

    /**
     * Returns the instance of the {@code Solver}
     *
     * @return instance
     */
    public static Solver getInstance() {
        return instance;
    }

    /**
     * This method reads all of the data into storage and the dictionary and then
     *  solves the cryptogram and returns the final decrypted message.
     *
     * @param file contains the name of the file that holds the encrypted message
     * @return final decrypted message
     */
    public final String solve(final String file) {
        reset();

        DataReader.readData(file);
        
        createKey();
        
        return translate(storage.getMsg());
    }
    
    /**
     * This method gets the original encrypted message from {@code DataStorage}
     *
     * @return original encrypted message
     */
    public final String getEncryptedMsg() {
        return storage.getMsg();
    }
    
    /**
     * This method displays the {@code Key}
     *
     * @return final working version of the key
     */
    public final String displayKey() {
        StringBuilder msg = new StringBuilder("Here is the key:\n");
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for(char letter : alphabet.toCharArray()) {
            msg.append(letter);
            msg.append(" => ");
            msg.append(key.getKey().get(letter));
            msg.append("\n");
        }
        
        return msg.toString();
    }
}
