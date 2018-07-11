using System.Linq;

namespace CryptoSolver.main.com.cryptogram.solver.cs {
    
    /*
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
    sealed class Punctuation {
        private static readonly char[] BadPunctuation = {',', '!', '.', '?', '\"', ':', ';'};
    
        private static readonly char[] GoodPunctuation = {'\'', '-'};
    
        /*
         * This method determines if a character is in the badPunctuation array
         *
         * @param letter character to be checked
         * @return true if character exists, false if it does not
         */
        private static bool IsBadPunctuation(char letter) {
            return BadPunctuation.Any(character => letter == character);
        }
    
        /*
         * This method determines if a word contains any "bad punctuation"
         *
         * @param word word to be checked
         * @return true if word contains "bad punctuation," false if it does not
         */
        private static bool HasBadPunctuation(string word) {
            return word.Any(IsBadPunctuation);
        }
    
        /*
         * This method determines if a character exists in the goodPunctuation array
         *
         * @param letter character to be checked
         * @return true if it exists, false if it does not
         */
        private static bool IsGoodPunctuation(char letter) {
            return GoodPunctuation.Any(character => letter == character);
        }
    
        /*
         * This method determines if a word contains any "good punctuation"
         *
         * @param word word to be checked
         * @return true if word has "good punctuation," false if it does not
         */
        public static bool HasGoodPunctuation(string word) {
            return word.Any(IsGoodPunctuation);
        }
    
        /*
         * This method removes any "bad punctuation" from a word
         *
         * @param word word to be checked
         * @return the input word without any "bad punctuation"
         */
        public static string RemoveBadPunctuation(string word) {
            
            if(!HasBadPunctuation(word))
                return word;
            
            var newString = "";
            
            foreach (var letter in word) {
                if(!IsBadPunctuation(letter))
                    newString += letter;
            }
            
            return newString;
        }
    }
}