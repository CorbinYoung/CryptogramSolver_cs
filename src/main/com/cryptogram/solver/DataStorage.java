package main.com.cryptogram.solver;

import java.util.*;

/**
 * This class manages all of the words in the encrypted message
 *
 * @author Corbin Young
 */
public final class DataStorage {

    private static final DataStorage instance = new DataStorage();

    private final List<String> data = new ArrayList<>();            //list of all the encrypted words
    private final StringBuilder eMsg = new StringBuilder();         //the original message
    
    /**
     * Creates the instance of {@code DataStorage}
     */
    private DataStorage() {
    }

    /**
     * Returns the instance of {@code DataStorage}
     *
     * @return instance
     */
    public static DataStorage getInstance() {
        return instance;
    }

    /**
     * This method adds a line of data read in from the file
     *
     * @param newData line of data to be added to storage
     */
    public final void addData(final String newData) {
        eMsg.append(newData);
        eMsg.append("\n");
        
        String[] pieces = newData.split("\\s");
        String noPunc;
        
        for(String piece : pieces) {
            noPunc = Punctuation.removeBadPunctuation(piece);
            
            //Part of this check here is to make sure that the data doesn't have any punctuation, good or bad
            //  The reason for this is that my dictionary doesn't contain any words that have punctuation, so
            //  those words would never have a match and the cryptogram would always be unsolvable.
            //Technically, the structure I'm using here is a set, but I couldn't use an actual {@code Set} because
            //  they can't be sorted, and I wanted to sort all of the words by length
            if(!Punctuation.hasGoodPunctuation(noPunc) && !data.contains(noPunc))
                data.add(noPunc);
        }
        
        //This sorts the words in the list from longest to shortest
        data.sort(Comparator.comparingInt(String::length).reversed());
    }

    /**
     * This method gets the original enrypted message
     *
     * @return original encrypted message
     */
    public final String getMsg() {
        return eMsg.toString();
    }

    public final List<String> getData() {
        return data;
    }

    /**
     * This method clears all of the data
     */
    public final void clear() {
        data.clear();
        eMsg.delete(0, eMsg.length());
    }
}
