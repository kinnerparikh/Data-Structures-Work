package Unit_3_Labs_Stacks_Queues;

/*
Create a class called LinkedListStack, which uses a linked list to store items.

Your stack must store any type.

Specification

    • You should have a private inner class Node for your nodes.
    • You should have a private field: head
    • push method: accepts any type and adds it to front of the list (returns void).
        ○ Must throw an IllegalArgumentException if a null item is given.
    • pop method: removes and returns the item at the front of the list (accepts nothing).
        ○ If the queue is empty then this method must throw an IllegalStateException.
    • peek method: returns, but does not remove, the item at the front of the list (accepts nothing).
        ○ If the queue is empty then this method must throw an IllegalStateException.
    • isEmpty method: tests to see if the stack is empty.
    • size method: returns the number of items in the stack.
*/

public class LinkedListStack<T> {
    private int n;
    private Node<T> head;
    
    private static class Node<T> {
        private T node;
        private Node<T> next;
    }
    
    public LinkedListStack() {
        head = null;
        n = 0;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        Node<T> old = head;
        head = new Node<T>();
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