namespace CryptoSolver.main.com.cryptogram.solver.cs {
    
    /*
     * This class holds the indexes for a current pair
     *  one encrypted word = one decrypted word
     *
     * @author Corbin Young
     */
    sealed class Index {
        private int Wi { get; }     //index for the encrypted word
        private int Mi { get; }     //index for the matching decrypted word
        
        /*
         * Creates a new {@code Index} with the given indexes for the pair
         *
         * @param word index for the encrypted word
         * @param match index for the decrypted word
         */
        Index(int word, int match) {
            Wi = word;
            Mi = match;
        }
    }
}