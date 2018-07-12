using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CryptoSolver.main.com.cryptogram.solver {
    
    /*
     * This class manages all of the words in the encrypted message
     *
     * @author Corbin Young
     */
    internal sealed class DataStorage {

        private static readonly DataStorage Instance = new DataStorage();

        private List<string> _data = new List<string>(); //list of all the encrypted words
        private readonly StringBuilder _eMsg = new StringBuilder(); //the original message

        /*
         * Creates the instance of {@code DataStorage}
         */
        private DataStorage() {
        }

        /*
         * Returns the instance of {@code DataStorage}
         *
         * @return instance
         */
        public static DataStorage GetInstance() {
            return Instance;
        }

        /*
         * This method adds a line of data read in from the file
         *
         * @param newData line of data to be added to storage
         */
        public void AddData(string newData) {
            _eMsg.Append(newData + "\n");

            var pieces = newData.Split(" ");

            foreach (var piece in pieces) {
                var noPunctuation = Punctuation.RemoveBadPunctuation(piece);

                //Part of this check here is to make sure that the data doesn't have any punctuation, good or bad
                //  The reason for this is that my dictionary doesn't contain any words that have punctuation, so
                //  those words would never have a match and the cryptogram would always be unsolvable.
                //Technically, the structure I'm using here is a set, but I couldn't use an actual {@code Set} because
                //  they can't be sorted, and I wanted to sort all of the words by length
                if (!Punctuation.HasGoodPunctuation(noPunctuation) && !_data.Contains(noPunctuation))
                    _data.Add(noPunctuation);
            }

            //This sorts the words in the list from longest to shortest
            _data = _data.OrderBy(s => s.Length).Reverse().ToList();
        }

        /*
         * This method gets the original encrypted message
         *
         * @return original encrypted message
         */
        public string GetMsg() {
            return _eMsg.ToString();
        }

        public List<string> GetData() {
            return _data.ToList();
        }

        /*
         * This method clears all of the data
         */
        public void Clear() {
            _data.Clear();
            _eMsg.Clear();
        }
    }
}