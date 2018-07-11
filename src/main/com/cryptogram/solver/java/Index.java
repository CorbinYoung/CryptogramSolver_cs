package main.com.cryptogram.solver;

/**
 * This class holds the indexes for a current pair
 *  one encrypted word = one decrypted word
 *
 * @author Corbin Young
 */
final class Index {
    private int wI;     //index for the encrypted word
    private int mI;     //index for the matching decrypted word
    
    /**
     * Creates a new {@code Index} with the given indexes for the pair
     *
     * @param word index for the encrypted word
     * @param match index for the decrypted word
     */
    Index(final int word, final int match) {
        wI = word;
        mI = match;
    }

    final int getWI() {
        return wI;
    }

    final int getMI() {
        return mI;
    }
}
