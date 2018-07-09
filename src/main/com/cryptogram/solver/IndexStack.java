package main.com.cryptogram.solver;

import java.util.Stack;

class IndexStack extends Stack<Index> {

    private static IndexStack instance = new IndexStack();

    private IndexStack() {
        super();
    }

    static IndexStack getInstance() {
        return instance;
    }
}
