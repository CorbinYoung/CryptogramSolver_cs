using System;
using CryptoSolver.main.com.cryptogram.solver;

namespace CryptoSolver.test.com.cryptogram.solver {
    
    public sealed class Runner {
        
        public static void Main(string[] args) {
            var mySolver = Solver.GetInstance();

            var result = mySolver.Solve("congrats.txt");

            Console.WriteLine("1st encrypted message:");
            Console.WriteLine(mySolver.GetEncryptedMsg());

            Console.WriteLine("1st decrypted message:");
            Console.WriteLine(result);

            Console.WriteLine("\nKey:");
            Console.WriteLine(mySolver.DisplayKey());

            var result2 = mySolver.Solve("preamble.txt");

            Console.WriteLine("\n2nd encrypted message:");
            Console.WriteLine(mySolver.GetEncryptedMsg());

            Console.WriteLine("2nd decrypted message:");
            Console.WriteLine(result2);
            
            Console.WriteLine("\nKey:");
            Console.WriteLine(mySolver.DisplayKey());
        }
    }
}