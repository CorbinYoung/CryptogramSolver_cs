package test.com.cryptogram.solver;

import main.com.cryptogram.solver.MyException;
import main.com.cryptogram.solver.Solver;

/**
 * Main class that gets the solver, gives it a message to decrypt, and then displays the final decryption
 *
 * @author Corbin Young
 */
public final class Main {
    
    public static void main(String[] args) {
        Solver mySolver = Solver.getInstance();
        
        try {
            String result = mySolver.solve("congrats.txt");
    
            System.out.println("1st encrypted message:");
            System.out.println(mySolver.getEncryptedMsg());
    
            System.out.println("1st decrypted message:");
            System.out.println(result);

            String result2 = mySolver.solve("preamble.txt");

            System.out.println("\n2nd encrypted message:");
            System.out.println(mySolver.getEncryptedMsg());

            System.out.println("2nd decrypted message:");
            System.out.println(result2);
        }
        catch (MyException me) {
            System.out.println(me.getMessage());
        }
    }
}
