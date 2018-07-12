namespace CryptoSolver.main.com.cryptogram.solver {
    
    /*
     * This class holds the indexes for a current pair
     *  one encrypted word = one decrypted word
     *
     * @author Corbin Young
     */
    internal sealed class Index {
        private readonly int _wI;     //index for the encrypted word
        private readonly int _mI;     //index for the matching decrypted word
        
        /*
         * Creates a new {@code Index} with the given indexes for the pair
         *
         * @param word index for the encrypted word
         * @param match index for the decrypted word
         */
        public Index(int word, int match) {
            _wI = word;
            _mI = match;
        }

        public int GetWi() {
            return _wI;
        }

        public int GetMi() {
            return _mI;
        }
    }
}