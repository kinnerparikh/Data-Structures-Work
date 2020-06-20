package Unit_3_Labs_Stacks_Queues;

public class LinkedListQueue<T> {
    
    private static class Node<T> {
        private T value;
        private Node<T> next;
    }
    
    // ADD YOUR CODE HERE.
    private int size;
    private Node<T> head;
    private Node<T> tail;
    
    public LinkedListQueue() {
        // ADD YOUR CODE HERE.
        head = null;
        tail = null;
        size = 0;
    }
    
    public void enqueue(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        Node<T> curr = tail;
        tail = new Node<T>();
        tail.next = null;
        tail.value = newItem;
        if (isEmpty()) head = tail;
        else curr.next = tail;
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        T t = (T)head.value;
        head = head.next;
        size--;
        if (isEmpty()) tail = null;
        return t;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return (T)head.value;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        return size;
    }
}