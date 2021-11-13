using System;
using System.IO;

namespace CryptoSolver {

  public class Program {

    public static void Main(string[] args) {
      var mySolver = Solver.GetInstance();

      var result = mySolver.Solve($"{Directory.GetCurrentDirectory()}\\..\\..\\..\\congrats.txt");

      Console.WriteLine("1st encrypted message:");
      Console.WriteLine(mySolver.GetEncryptedMsg());

      Console.WriteLine("1st decrypted message:");
      Console.WriteLine(result);

      Console.WriteLine("\nKey:");
      Console.WriteLine(mySolver.DisplayKey());

      var result2 = mySolver.Solve($"{Directory.GetCurrentDirectory()}\\..\\..\\..\\preamble.txt");

      Console.WriteLine("\n2nd encrypted message:");
      Console.WriteLine(mySolver.GetEncryptedMsg());

      Console.WriteLine("2nd decrypted message:");
      Console.WriteLine(result2);

      Console.WriteLine("\nKey:");
      Console.WriteLine(mySolver.DisplayKey());
    }
  }
}
