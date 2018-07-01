The purpose of this project is to create a program that can solve a given cryptogram.

I used a file located on a UNIX system that contains hundreds of thousands of words as my
dictionary. A few things to note on this point:

1. The file does not contain the plural form of a word. So, for example, the file contains the word noun
but not the word nouns. If you use the plural form of a word, it is likely that you will either end up with
an incorrect decryption, or the program will be unable to solve your cryptogram.

2. The file does not contain any contractions. Any words in your cryptogram that contain a contraction
will not be used in solving the cryptogram. The program will solve the cryptogram without them and then
simply fill in the contraction with the appropriate letters.

3. The file does not contain any acronyms. For example, the file contains the words in real life,
but will not recognize the acronym irl.

4. I have included the file with this project. It is the words.txt file outside the src folder. If you
wish to add words to the file, please feel free to do so. However, keep in mind the notes above. The
program is designed to ignore contractions, so don't bother adding any to the dictionary. You may wish
to add the plural form of some words. I have actually already added a few of them myself, and they are
still in the file. **Do not move the file. If you move it, the Solver will not be able to find it, and
the program will not work.** 

As for using the project, I have included the main files involved with the Solver, along with some other
files that they use. The main files are: [Solver](src/main/com/cryptogram/solver/Solver.java), [Key](src/main/com/cryptogram/solver/Key.java),
[DataStorage](src/main/com/cryptogram/solver/DataStorage.java), and [Dictionary](src/main/com/cryptogram/solver/Dictionary.java).

Once you've pull the project down, all you have to do is create a main. Each of the above-listed classes
are Singletons. You will only every need to reference the Solver class. Simply get its instance in main,
and then call its solve() method. The Solver class will handle everything else. I have also provided a Main
under the src/test folder as an example, and I've included both the files it asks for with the project so you
can see where they need to go if you want to use them.

Solver has three public methods that are available:

1. solve() - This method takes in a file name passed as a String. The Solver will open the file and 
read in its contents and store them. Any file that you wish to read from must go in the root project
folder, the same one that the words.txt file is located in. Every time you call the solve() method,
the Solver will clear the Key and the DataStorage.

2. getEncryptedMsg() - This method will return the original message that the solver read in and tried
to solve. This method can be used to verify that it is actually trying to solve what you think it is.

3. displayKey() - This method will display the current state of the key. If you call this method before
you try and solve a cryptogram, it will show everything as null. Once you've called solve(), the key
will be the final form of the key for that specific cryptogram.