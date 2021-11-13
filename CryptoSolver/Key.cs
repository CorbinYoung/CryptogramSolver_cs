using System.Collections.Generic;
using System.Linq;

namespace CryptoSolver {

  /*
   * This class is in charge of managing the key for the cryptogram
   *
   * @author Corbin Young
   */
  internal sealed class Key {
    private static readonly Key Instance = new Key();

    private readonly Dictionary<char, char> _key = new Dictionary<char, char>();

    /*
     * Creates the instance of {@code Key}
     */
    private Key() {
    }

    /**
     * This method removes all of the letters of a word from the key
     *
     * @param word word that needs to have its letters removed from the key
     */
    private void RemoveFromKey(string word) {
      foreach (var letter in word) {
        _key.Remove(letter);
      }
    }

    /*
     * Returns the instance of {@code Key}
     *
     * @return instance
     */
    public static Key GetInstance() {
      return Instance;
    }

    /**
     * Checks to make sure that a pair (one encrypted and one decrypted word) are compatible
     *  with the current version of the key. If they are, then that pair is added to the key.
     *
     * Instances where a match is not valid:
     *  1. No letter can decrypt to itself
     *  2. If the encrypted letter already exists but matches to a different decrypted letter
     *  3. If the decrypted letter already exists but matches to a different encrypted letter
     *
     * @param word encrypted word to be added to key
     * @param eWord decrypted word to be added to key
     * @return true if pair is compatible, false if it's not
     */
    public bool AddToKey(string word, string eWord) {

      for (var i = 0; i < word.Length; i++) {
        if (word[i] == eWord[i]) {
          return false;
        }

        if (_key.ContainsKey(word[i])) {
          if (_key[word[i]] != eWord[i])
            return false;
        } else if (_key.ContainsValue(eWord[i])) {
          foreach (var entry in _key.Keys) {
            if (_key[entry] == eWord[i] && entry != word[i])
              return false;
          }
        }
      }

      for (var i = 0; i < word.Length; i++) {
        if (!_key.ContainsKey(word[i])) {
          _key.Add(word[i], eWord[i]);
        }
      }

      return true;
    }

    /*
     * This method updates the {@code Key} when a word gets removed from the {@code Stack}
     *
     * Algorithm:
     *  1. Pop one {@code Index} off the {@code Stack}
     *  2. Re-input all other pairs still on the {@code Stack} to ensure that no older word had the same key/value pair
     *
     * @param stack the current {@code Stack} of words that have been solved
     * @param words {@code List} of all the encrypted words
     * @return the {@code Index} that got popped off the {@code Stack}
     */
    public Index UpdateKey(IndexStack stack, List<string> words) {
      var removed = stack.Pop();
      RemoveFromKey(words[removed.GetWi()]);

      foreach (var index in stack) {
        var word = words[index.GetWi()];
        var eWord = MyDictionary.GetInstance().GetWordSubset(word.Length, StringPattern.GetPattern(word)).ElementAt(index.GetMi());
        AddToKey(word, eWord);
      }

      return removed;
    }

    public Dictionary<char, char> GetKey() {
      return _key;
    }

    /*
     * This method clears all items in the {@code Key}
     */
    public void Clear() {
      _key.Clear();
    }
  }
}