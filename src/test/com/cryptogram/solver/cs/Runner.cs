using System;
using CryptoSolver.main.com.cryptogram.solver.cs;

namespace CryptoSolver.test.com.cryptogram.solver.cs {
    
    public class Runner {
        public static void Main(string[] args) {
            var mySolver = Solver.GetInstance();

            var result = mySolver.Solve("congrats.txt");

            Console.WriteLine("1st encrypted message:");
            Console.WriteLine(mySolver.GetEncryptedMsg());

            Console.WriteLine("1st decrypted message:");
            Console.WriteLine(result);

            var result2 = mySolver.Solve("preamble.txt");

            Console.WriteLine("\n2nd encrypted message:");
            Console.WriteLine(mySolver.GetEncryptedMsg());

            Console.WriteLine("2nd decrypted message:");
            Console.WriteLine(result2);
        }
    }
}