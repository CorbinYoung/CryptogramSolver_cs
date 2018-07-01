package main.com.cryptogram.solver.stackStuff;

import java.util.ArrayList;
import java.util.List;

public final class SLL<E> {
    
    private static final class Node<E> {
        private final E element;
        private Node<E> next;
        
        private Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        
        private E getElement() {
            return element;
        }
        
        private Node<E> getNext() {
            return next;
        }
    }
    
    private Node<E> head = null;
    
    private int size = 0;
    
    SLL() {
    }
    
    public final boolean isEmpty() {
        return size == 0;
    }
    
    public final int size() {
        return size;
    }
    
    public final E getFirst() {
        if(isEmpty())
            return null;
        
        return head.getElement();
    }
    
    final void addFirst(final E e) {
        head = new Node<>(e, head);
        
        size++;
    }
    
    final E removeFirst() {
        if(isEmpty())
            return null;
        
        final E e = head.getElement();
        
        head = head.getNext();
    
        size--;
        
        return e;
    }
    
    final List<E> getAsList() {
        List<E> list = new ArrayList<>();
        
        Node<E> ptr = head;
        
        while(ptr != null) {
            list.add(ptr.getElement());
            ptr = ptr.getNext();
        }
        
        return list;
    }

    final void removeAll() {
        head = null;
        size = 0;
    }
}
