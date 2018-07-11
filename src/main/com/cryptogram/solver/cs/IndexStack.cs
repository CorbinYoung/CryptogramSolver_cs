using System.Collections.Generic;

namespace CryptoSolver.main.com.cryptogram.solver.cs {
    
    sealed class IndexStack : Stack<Index> {
        private static readonly IndexStack Instance = new IndexStack();

        private IndexStack() {
        }

        public static IndexStack GetInstance() {
            return Instance;
        }
    }
}