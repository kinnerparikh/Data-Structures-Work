package LinkedList;
import java.util.*;

public class DoublyLinkedList<T> {
    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;
        
        Node() {
            this.data = null;
            this.next = null;
            this.previous = null;
        }
        
        Node(T value) {
            this.data = value;
            this.next = null;
            this.previous = null;
        }
        
        Node(T value, Node<T> prev, Node<T> next) {
            this.data = value;
            this.previous = prev;
            this.next = next;
        }
    }
    
    private Node<T> head;
    private int size = 0;
    
    public DoublyLinkedList() {
        head = null;
    }
    
    public DoublyLinkedList(T value) {
        head = new Node<T>(value);
        size++;
    }
    
    /*
     *isEmpty(): boolean: Returns true if this linked list is empty, i.e. there are no nodes in the list.
     */
    public boolean isEmpty() {
        return (head == null);
    }
    
    /*
     * size(): int: Returns the number of nodes in this linked list.
     */
    public int size() {
        return size;
    }
    
    /*
     * toString(): String: Returns a String representation of this linked list. 
     * The output of the String should be in the format:
     * "null <-- node1.data <--> node2.data <--> node3.data <--> ... --> null"
     * Each node's data should bepreceded by a two-dash arrow pointing to its previous node, 
     * and then the node is followed by a space, two dashes, a greater than symbol, and a space. 
     * The last node should only point to null. 
     * The start and end of the returned String should includereferences to null. 
     * In the case of an empty list,just return the String literal, "null".
     */
    public String toString() {
        String retVal = "null";
        Node temp = head;
        if (temp == null) {
            return retVal;
        }
        else {
            retVal += " <-- ";
            while (temp.next != null) {
                retVal += (temp.data + " <--> ");
                temp = temp.next;
            }
            
            retVal += temp.data + " --> null";
            return retVal;
        }
    }
    
    /*
     * get(int index): int: Returns the value stored in the specified place in this linked list. 
     * If index is invalid, this method should thrown an IndexOutOfBoundsException.
     */
    public T get(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            
            return temp.data;
        }
    }
    
    /*
     * contains(int value): boolean: Returns true if there is a Node in the linked list whose data is equal to value and returns false otherwise. 
     * Make sure to use value comparison in your equality check, not reference comparison!
     */
    public boolean contains(T value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(value)) {
                return true;
            }
            
            temp = temp.next;
        }
        return false;
    }
    
    /*
     * add(T value): void: Creates a new Node whose data is value, and appends this new Node to the end of this linked list.
     */
    public void add(T value) {
        
        if (isEmpty()) {
            head = new Node<T>(value);
        }
        else {
            Node temp = head;
            Node prev = null;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            
            temp.next = new Node<T>(value);
            temp.previous = prev;
        }
        size++;
    }
    
    /*
     * add(int index, T value): void: Inserts a new Node whose data is value at the specified position in this list. 
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). 
     * If index is invalid, this method should throw an IndexOutOfBoundsException.
     */
    public void add(int index, T value) {
        Node<T> newNode = new Node<T>(value);
        //check if out of bounds
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        // if its at 0 handle case
        if (index == 0) {
            head = new Node<T>(value, null, head);
        }
        // iterate through index -1
        else {
            Node<T> temp = head;
            for (int i = 0; i < (index - 1); i++) {
                temp = temp.next;
            }
            temp = new Node(value, temp, temp.next);
            temp.previous.next = temp;
            temp.next.previous = temp;
        }
        
        size++;
    }
    
    /*
     * remove(): T: Retrieves and removes the head (first element) of this linked list. 
     * The Node that came after head should become the new head. 
     * If this list is empty when remove is called, throw a NoSuchElementException.
     */
    public T remove() {
        Node<T> retVal = head;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            head = head.next;
            size--;
            if (size != 0){
                head.previous = null;
            }
        }
        return retVal.data;
    }
    
    /*
     * remove(int index): T: Removes the data of the Node at the specified position in this list. 
     * Shifts any subsequent elements to the left (subtracts one from their indices). \
     * Returns the data of the Node that was removed from the list. 
     * If index is invalid, this method should throw an IndexOutOfBoundsException.
     */
    public T remove(int index) {
        // Index out of bounds exception
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
            // Temporary list
            Node<T> temp = head;
            // Iterate to node at index
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            // Create the returnable var
            T retVal = temp.data;
            // prev and next both null case 
            if (temp.previous == null && temp.next == null) {
                head = null;
            }
            // only prev null case
            else if (temp.previous == null) {
                temp.next.previous = null;
            }
            // only next null case
            else if (temp.next == null) {
                temp.previous.next = null;
            }
            // prev and next non-null
            else {
                // reset pointers
                temp.previous.next = temp.next;
                temp.next.previous = temp.previous;
            }
            // decrement size
            size--;
            // return the return var
            return retVal;
        }
    }
}