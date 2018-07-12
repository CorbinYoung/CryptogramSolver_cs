using System.IO;

namespace CryptoSolver.main.com.cryptogram.solver {
    
    /*
     * This class is in charge of reading in data from different files
     *
     * @author Corbin Young
     */
    internal sealed class DataReader {
        
        /*
         * This method reads an encrypted message from a file
         *
         * @param dataFile name of the file containing encrypted message
         */
        public static void ReadData(string dataFile) {
            foreach(var line in File.ReadLines(dataFile)) {
                DataStorage.GetInstance().AddData(line.ToUpper());
            }
        }
        
        /*
         * This method read in all of the english words from a file
         */
        public static void ReadWords() {
            foreach(var line in File.ReadLines("words.txt")) {
                MyDictionary.GetInstance().AddWord(Punctuation.RemoveBadPunctuation(line.ToUpper()));
            }
            
        }
    }
}