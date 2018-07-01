package main.com.cryptogram.solver;

/**
 * This class manages punctuation, which is separated into two categories.
 *
 *  Good punctuation: Punctuation that is used in words, like ' for contractions
 *  Bad punctuation: Punctuation that is not actually part of the word, like " or , or ?
 *
 * The methods check to see if something is good or bad punctuation.
 *  Bad punctuation will be removed, but good punctuation will not.
 *
 * @author Corbin Young
 */
final class Punctuation {
    
    private static final char[] badPunctuation = {',', '!', '.', '?', '\"', ':', ';'};
    
    private static final char[] goodPunctuation = {'\'', '-'};

    /**
     * This method determines if a character is in the badPunctuation array
     *
     * @param letter character to be checked
     * @return true if character exists, false if it does not
     */
    private static boolean isBadPunctuation(final char letter) {

        for(char character : badPunctuation) {
            if(letter == character) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * This method determines if a word contains any "bad punctuation"
     *
     * @param word word to be checked
     * @return true if word contains "bad punctuation," false if it does not
     */
    private static boolean hasBadPunctuation(final String word) {
        
        for(char letter : word.toCharArray()) {
            if(isBadPunctuation(letter)){
                return true;
            }
        }
        
        return false;
    }

    /**
     * This method determines if a character exists in the goodPunctuation array
     *
     * @param letter character to be checked
     * @return true if it exists, false if it does not
     */
    private static boolean isGoodPunctuation(final char letter) {
        for(char character : goodPunctuation) {
            if(letter == character) {
                return true;
            }
        }
    
        return false;
    }

    /**
     * This method determines if a word contains any "good punctuation"
     *
     * @param word word to be checked
     * @return true if word has "good punctuation," false if it does not
     */
    static boolean hasGoodPunctuation(final String word) {
        for(char letter : word.toCharArray()) {
            if(isGoodPunctuation(letter)){
                return true;
            }
        }
    
        return false;
    }

    /**
     * This method removes any "bad punctuation" from a word
     *
     * @param word word to be checked
     * @return the input word without any "bad punctuation"
     */
    static String removeBadPunctuation(final String word) {
        
        if(!hasBadPunctuation(word))
            return word;
        
        StringBuilder newString = new StringBuilder();
        
        for(char letter : word.toCharArray()) {
            if(!isBadPunctuation(letter))
                newString.append(letter);
        }
        
        return newString.toString();
    }
}
