using System;
using System.Linq;
using System.Text;

namespace CryptoSolver.main.com.cryptogram.solver {
    
    /*
     * This class is in charge of the algorithm for solving the cryptogram.
     *
     * @author Corbin Young
     */
    public sealed class Solver {
    
        private static readonly Solver Instance = new Solver();
    
        private readonly Key _key = Key.GetInstance();
        private readonly MyDictionary _dictionary = MyDictionary.GetInstance();
        private readonly DataStorage _storage = DataStorage.GetInstance();
        private readonly IndexStack _stack = IndexStack.GetInstance();
    
        /*
         * Creates the instance of the {@code Solver}
         */
        private Solver() {
            DataReader.ReadWords();
        }
    
        /*
         * This method resets the {@code Key}, {@code DataStorage}, and the {@code Stack}.
         */
        private void Reset() {
            _stack.Clear();
            _storage.Clear();
            _key.Clear();
        }
    
        /*
         * This method checks pairs of an encrypted word with its set of decryptions against the {@code Key}
         *
         * @param word encrypted word
         * @param index starting index in the list of decrypted matches
         * @return the index of a pair that was successfully inserted into the key
         */
        private int PickMatch(string word, int index) {
            var mySet = _dictionary.GetWordSubset(word.Length, StringPattern.GetPattern(word));
            
            if(!mySet.Any())
                throw new SystemException("No matches found for: " + word);
            
            for(var i = index; i < mySet.Count; i++) {
                if(_key.AddToKey(word, mySet.ElementAt(i)))
                    return i;
            }
            
            return -1;
        }
        
        /*
         * General algorithm to solve the cryptogram
         *
         *  1. Pick a match with the first word starting at index 0
         *  2. Push the indexes of the word and its decryption onto the stack
         *  3. Increment the index for the decrypted words and pick another match to add to the stack
         *  4. While no match is found that could be added to the key and we haven't removed everything from the stack
         *      4a. Pop a match off the stack and reset the key
         *      4b. Go back to the previous encrypted word and find a different match
         *  5. If the stack is now empty and we still haven't found a valid match, the cryptogram is unsolvable
         *  6. Otherwise, push our new match on the stack and repeat 2-6
         */
        private void CreateKey() {
            var wI = 0;
            
            var words = _storage.GetData();
            
            var mI = PickMatch(words[wI], 0);
            
            _stack.Push(new Index(wI, mI));
            
            while(_stack.Count < words.Count && _stack.Any()) {
                var index = _stack.Peek();
                
                wI = index.GetWi() + 1;
                mI = PickMatch(words[wI], 0);
                
                while(mI < 0 && _stack.Any()) {
                    index = _key.UpdateKey(_stack, words);
                    wI = index.GetWi();
                    mI = PickMatch(words[wI], index.GetMi() + 1);
                }
    
                if(mI < 0)
                    throw new SystemException("Cryptogram is unsolvable");
        
                _stack.Push(new Index(wI, mI));
            }
        }
        
        /*
         * This method translates the original encrypted message into the final decrypted form based on
         *  the {@code Key}.
         *
         * @param encryptedMsg original encrypted message
         * @return the final decrypted message
         */
        private string Translate(string encryptedMsg) {
            var decryptedMsg = new StringBuilder();
            
            foreach (var letter in encryptedMsg) {
                switch(letter) {
                    case ' ': case '\'':
                    case '\"': case ',':
                    case '-': case '!':
                    case ';': case ':':
                    case '.': case '?':
                    case '\n':
                        decryptedMsg.Append(letter);
                        break;
                    default:
                        decryptedMsg.Append(_key.GetKey()[letter]);
                        break;
                }
            }
            
            return decryptedMsg.ToString();
        }
    
        /*
         * Returns the instance of the {@code Solver}
         *
         * @return instance
         */
        public static Solver GetInstance() {
            return Instance;
        }
    
        /*
         * This method reads all of the data into storage and the dictionary and then
         *  solves the cryptogram and returns the final decrypted message.
         *
         * @param file contains the name of the file that holds the encrypted message
         * @return final decrypted message
         */
        public string Solve(string file) {
            Reset();
    
            DataReader.ReadData(file);
            
            CreateKey();
            
            return Translate(_storage.GetMsg());
        }
        
        /*
         * This method gets the original encrypted message from {@code DataStorage}
         *
         * @return original encrypted message
         */
        public string GetEncryptedMsg() {
            return _storage.GetMsg();
        }
        
        /*
         * This method displays the {@code Key}
         *
         * @return final working version of the key
         */
        public string DisplayKey() {
            var msg = new StringBuilder("Here is the key:\n");
            
            foreach(var letter in "ABCDEFGHIJKLMNOPQRSTUVWXYZ") {
                if (!_key.GetKey().ContainsKey(letter)) continue;
                msg.Append(letter);
                msg.Append(" => ");
                msg.Append(_key.GetKey()[letter]);
                msg.Append("\n");
            }
            
            return msg.ToString();
        }
    }
}