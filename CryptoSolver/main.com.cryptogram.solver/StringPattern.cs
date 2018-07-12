using System.Collections.Generic;

namespace CryptoSolver.main.com.cryptogram.solver {
    
    /*
     * This class creates patterns for each word to be used to match pairs
     *
     * @author Corbin Young
     */
    internal sealed class StringPattern {

        /*
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
        public static string GetPattern(string word) {
            var pattern = "";
            var key = new Dictionary<char, string>();
            var index = 0;

            foreach (var letter in word) {
                if (!key.ContainsKey(letter)) {
                    key.Add(letter, index.ToString("X"));
                    index++;
                }

                pattern += key[letter];
            }

            return pattern;
        }
    }
}