package Stack;

public class LinkedListStack<T> {
    private int n;
    private Node head;
    
    private static class Node<T> {
        private T node;
        private Node next;
    }
    
    public LinkedListStack() {
        head = null;
        n = 0;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        Node old = head;
        head = new Node();
        head.node = newItem;
        head.next = old;
        n++;
    }
    
    public T pop() {
        if (isEmpty()) throw new IllegalStateException();
        T newItem = (T)head.node;
        head = head.next;
        n--;
        return newItem;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return (T)head.node;
    }
    
    public boolean isEmpty() {
        return (head == null);
    }
    
    public int size() {
        return n;
    }
}