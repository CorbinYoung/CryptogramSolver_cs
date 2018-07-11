using System;
using System.Collections.Generic;

namespace CryptoSolver.main.com.cryptogram.solver.cs {
    
    /*
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
    sealed class MyDictionary {

        private static readonly MyDictionary Instance = new MyDictionary();

        private readonly Dictionary<int, Dictionary<string, HashSet<string>>> Words;

        /*
         * Creates the instance of {@code Dictionary}
         */
        private MyDictionary() {
            Words = new Dictionary<int, Dictionary<string, HashSet<string>>>();
        }

        /*
         * Returns the instance of {@code Dictionary}
         *
         * @return instance
         */
        static MyDictionary GetInstance() {
            return Instance;
        }

        /*
         * This method adds a word to the dictionary
         *
         * @param word word to be added to the dictionary
         */
        void AddWord(string word) {
            String pattern = StringPattern.GetPattern(word);
            int key = word.Length;

            if (!Words.ContainsKey(key)) {
                Words.Add(key, new Dictionary<string, HashSet<string>>());
            }

            if (!Words[key].ContainsKey(pattern)) {
                Words[key].Add(pattern, new HashSet<string>());
            }
            
            Words[key][pattern].Add(word);
        }

        /*
         * This method returns a {@code Set} of words that all have the same length and pattern
         *
         * @param key specified length
         * @param pattern specified pattern
         * @return {@code Set} of words
         */
        HashSet<string> GetWordSubset(int key, string pattern) {
            return Words[key][pattern];
        }
    }
}