/**
 * A last-in-first-out (LIFO) stack of generic items.
 *
 * @param <T> the type of item to store in the stack
 */
public class Stack<T>
{
    private Node head;
    private int size;

    private static class Node<T> 
    {
        private T data;
        private Node next;
    }

    /**
     * Initializes an empty stack.
     */
    public Stack()
    {
        head = null;
        size = 0;
    }

    /**
     * Adds an item to the stack.
     *
     * @param newItem the item to add
     */
    public void push(T newItem)
    {
        if (newItem == null) throw new IllegalArgumentException();
        Node old = head;
        head = new Node();
        head.data = newItem;
        head.next = old;
        size++;
    }

    /**
     * Removes and returns the item on the stack that was most recently added.
     *
     * @return the item on the stack that was most recently added
     */
    public T pop()
    {
        if (isEmpty()) throw new IllegalStateException();
        T popItem = (T)head.data;
        head = head.next;
        size--;
        return popItem;
    }

    /**
     * Returns the item most recently added to the stack.
     *
     * @return the item most recently added to the stack
     */
    public T peek()
    {
        if (isEmpty()) throw new IllegalStateException();
        return (T)head.data;
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
        return size;
    }
}
