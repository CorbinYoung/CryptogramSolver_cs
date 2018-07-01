package main.com.cryptogram.solver.stackStuff;

import java.util.List;

public final class ADTStack<E> implements Stack<E> {
    
    private final SLL<E> list = new SLL<>();
    
    public ADTStack() {
    }
    
    @Override
    public final E peek() {
        return list.getFirst();
    }
    
    @Override
    public final void push(final E e) {
        list.addFirst(e);
    }
    
    @Override
    public final E pop() {
        return list.removeFirst();
    }
    
    @Override
    public final int size() {
        return list.size();
    }
    
    @Override
    public final boolean isEmpty() {
        return list.isEmpty();
    }
    
    @Override
    public final List<E> getStackAsList() {
        return list.getAsList();
    }

    @Override
    public void clear() {
        list.removeAll();
    }
}
