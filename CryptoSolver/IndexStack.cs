using System.Collections.Generic;

namespace CryptoSolver {

  internal sealed class IndexStack : Stack<Index> {
    private static readonly IndexStack Instance = new IndexStack();

    private IndexStack() {
    }

    public static IndexStack GetInstance() {
      return Instance;
    }
  }
}