package Unit_3_Labs_Stacks_Queues;

public class LinkedListDeque<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
        
        public Node(T input)
        {
            data = input;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public LinkedListDeque() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void pushRight(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        Node<T> temp = new Node<T>(newItem);
        if (size == 0)
        {
            tail = temp;
        }
        else
        {
            head.prev = temp;
        }
        
        temp.next = head;
        head = temp;
        size++;
    }
    
    public void pushLeft(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        Node<T> temp = new Node<T>(newItem);
        if (size == 0)
        {
            head = temp;
        }
        else
        {
            tail.next = temp;
            temp.prev = tail;
        }
        tail = temp;
        size++;
    }
    
    public T popRight() {
        if (head == null) throw new IllegalStateException();
        T retVal;
        if (head.next == null)
        {
            retVal = head.data;
            tail = null;
        }
        else
        {
            retVal = head.data;
            head.next.prev = null;
        }
        head = head.next;
        size--;
        return (T)retVal;
    }
    
    public T popLeft() {
        if (tail == null) throw new IllegalStateException();
        T retVal;
        if (head.next == null)
        {
            retVal = head.data;
            head = null;
        }
        else
        {
            retVal = tail.data;
            tail.prev.next = null;
        }
        tail = tail.prev;
        size--;
        return (T)retVal;
    }
    
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
}