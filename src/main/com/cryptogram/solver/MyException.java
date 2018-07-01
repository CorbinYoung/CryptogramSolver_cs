package main.com.cryptogram.solver;

/**
 * This is just my personal exception that I'm throwing if the cryptogram can't be solved
 *
 * @author Corbin Young
 */
public final class MyException extends Exception {
    
    public MyException(final String msg) {
        super(msg);
    }
}
