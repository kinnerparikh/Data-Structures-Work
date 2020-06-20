/**
 * A first-in-first-out (FIFO) queue of generic items.
 *
 * @param <T> the type of item to store in the queue
 */
public class Queue<T>
{
    private int size;
    private Node head;
    private Node tail;

    private static class Node<T> 
    {
        private T value;
        private Node next;
    }
    /**
     * Initializes an empty queue.
     */
    public Queue()
    {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an item to the queue.
     *
     * @param newItem the item to add
     */
    public void enqueue(T newItem)
    {
        if (newItem == null) throw new IllegalArgumentException();
        Node curr = tail;
        tail = new Node();
        tail.next = null;
        tail.value = newItem;
        if (isEmpty()) head = tail;
        else curr.next = tail;
        size++;
    }

    /**
     * Removes and returns the item on the queue that was least recently added.
     *
     * @return the item on the queue that was least recently added
     */
    public T dequeue()
    {
        if (isEmpty()) throw new IllegalStateException();
        T currValue = (T)head.value;
        head = head.next;
        size--;
        if (isEmpty()) tail = null;
        return currValue;
    }

    /**
     * Returns the item least recently added to the queue.
     *+
     * @return the item least recently added to the queue
     */
    public T peek()
    {
        if (isEmpty()) throw new IllegalStateException();
        return (T)head.value;
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return whether the queue is empty
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    public int size()
    {
        return size;
    }
}
