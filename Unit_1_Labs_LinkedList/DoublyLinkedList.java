package Unit_1_Labs_LinkedList;

/*
Implement the class DoublyLinkedList.

You will implement a fully functioning and working Doubly Linked List class that meets the criteria of a specific API. 
Your DoublyLinkedList class will link Node objects, whose data will be of a generic type. 
This data structure must be implemented without the use of arrays, ArrayLists, or any additional libraries. 
In other words, you're building this class from scratch.

You may (and should), however, reference your SinglyLinkedList class to remind you of how to implement some of the basic methods.

The Node class
    Field summary
        • data: T: Each Node object will store data of generic type.
        • next: Node: Each Node stores a reference to the Node that follows this node. If there is no such node, next should be null.
        • previous: Node: Each Node stores a reference to the Node that precedes this node. If there is no such node, previous should be null.
    
    Constructor summary
        • Node(): This parameter-less constructor should assign data, next, and previous to null.
        • Node(T value): This single-parameter constructor should assign data to value and next and previous to null.

The DoublyLinkedList class

    Field summary
        • head: Node: A reference to the Node that is the beginning of this DoublyLinkedList.

    Constructor summary
        • DoublyLinkedList(): This parameter-less constructor should assign head to null.
        • DoublyLinkedList(T value): This single-parameter constructor should assign head to a Node whose data is assigned to value.
    
    Method summary
        • isEmpty(): boolean: Returns true if this linked list is empty, i.e. there are no nodes in the list.
        • size(): int: Returns the number of nodes in this linked list.
        • toString(): String: Returns a String representation of this linked list. The output of the String should be in the format:
                      null <-- node1.data <--> node2.data <--> node3.data <--> ... --> null
                      Each node's data should bepreceded by a two-dash arrow pointing to its previous node, and then the node is followed by a space, two dashes, a greater than symbol, and a space. 
                      The last node should only point to null. The start and end of the returned String should includereferences to null. In the case of an empty list,just return the String literal, "null".
    
        • get(int index): int: Returns the value stored in the specified place in this linked list. If index is invalid, this method should thrown an IndexOutOfBoundsException. Read about how to throw an exception here. <https://www.webucator.com/how-to/how-throw-an-exception-java.cfm>
        • contains(int value): boolean: Returns true if there is a Node in the linked list whose data is equal to value and returns false otherwise. Make sure to use value comparison in your equality check, not reference comparison!
        • add(T value): void: Creates a new Node whose data is value, and appends this new Node to the end of this linked list.
        • add(int index, T value): void: Inserts a new Node whose data is value at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). If index is invalid, this method should throw an IndexOutOfBoundsException.
        • remove(): T: Retrieves and removes the head (first element) of this linked list. The Node that came after head should become the new head. If this list is empty when remove is called, throw a NoSuchElementException.
        • remove(int index): T: Removes the data of the Node at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the data of the Node that was removed from the list. If index is invalid, this method should throw an IndexOutOfBoundsException.
*/

import java.util.NoSuchElementException;

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
        Node<T> temp = head;
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
        Node<T> temp = head;
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
            Node<T> temp = head;
            Node<T> prev = null;
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
            temp = new Node<T>(value, temp, temp.next);
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