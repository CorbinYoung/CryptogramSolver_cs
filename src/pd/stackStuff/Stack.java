package pd.stackStuff;

import java.util.List;

/**
 * This interface contains all of the methods I use for my stack
 *
 * @author Corbin Young
 * @param <E> type of stack
 */
public interface Stack<E> {

    /**
     * This method gets the element at the top of the stack
     *
     * @return element
     */
    E peek();

    /**
     * This method removes the element at the top of the stack
     *
     * @return element
     */
    E pop();

    /**
     * This method puts an item onto the top of the stack
     *
     * @param e item to be added to the stack
     */
    void push(E e);

    /**
     * This method removes all items from the stack
     */
    void clear();

    /**
     * This method gets the number of elements on the stack
     *
     * @return size
     */
    int size();

    /**
     * This method indicates whether or not the stack has any elements on it
     *
     * @return true if elements are on the stack, false if not
     */
    boolean isEmpty();

    /**
     * Gets all of the elements of the stack in a {@code List}
     *
     * @return list of elements
     */
    List<E> getStackAsList();
}
