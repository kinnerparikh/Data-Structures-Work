package Unit_1_Labs_LinkedList;

/**
    Implement the class SinglyLinkedList.

    While this will not be as thorough as implementing Java's entire List interface and will deviate from the List interface slightly, you will implement some of the key methods a linked list class must contain.
    Your SinglyLinkedList class will link Node objects, whose data will exclusively consist of storing a single integer. 
    This data structure must be implemented without the use of arrays, ArrayLists, or any additional libraries. In other words, you're building this class from scratch.

    Note that Node should be a static nested class of SinglyLinkedList. This will allow you to avoid the need for accessor and mutator methods of the Node class.
    
    Your SinglyLinkedList must adhere to the API stated here. 
    While you may choose to add additional instance variables or methods, you are not permitted to omit any part of the API, change method names or their parameters or return types.

    The Node class
        Field summary
            • data: int: Each Node object will store an integer value.
            • next: Node: Each Node stores a reference to the Node that follows this node. If there is no such node, next should be null.
        
        Constructor summary
            • Node(): This parameter-less constructor should assign data to 0 and next to null.
            • Node(int value): This single-parameter constructor should assign data to value and next to null.
            • Node(int value, Node nextNode): This two-parameter constructor should assign data to value and next to nextNode.
    
    The SinglyLinkedList class
        Field summary
            • head: Node: A reference to the Node that is the beginning of this SinglyLinkedList.

        Constructor summary
            • SinglyLinkedList(): This parameter-less constructor should assign head to null.
            • SinglyLinkedList(int value): This single-parameter constructor should assign head to a Node whose data is assigned to value.
        
        Method summary
            • isEmpty(): boolean: Returns true if this linked list is empty, i.e. there are no nodes in the list.
            • size(): int: Returns the number of nodes in this linked list.
            • toString(): String: Returns a String representation of this linked list. The output of the String should be in the format:
              node1.data --> node2.data --> node3.data --> ... --> null
              Each node's data should be followed by a space, two dashes, a greater-than symbol, and a space. The end of the String should include "null". In the case of an empty list, just return the String literal "null".
            • get(int index): int: Returns the value stored in the specified place in this linked list. If index is invalid, this method should thrown an IndexOutOfBoundsException. Read about how to throw an exception here.
            • contains(int value): boolean: Returns true if there is a Node in the linked list whose data is equal to value and returns false otherwise.
            • add(int value): void: Creates a new Node whose data is value, and appends this new Node to the end of this linked list.
            • add(int index, int value): void: Inserts a new Node whose data is value at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). If index is invalid, this method should throw an IndexOutOfBoundsException.
            • remove(): int: Retrieves and removes the head (first element) of this linked list. The Node that came after head should become the new head. If this list is empty when remove is called, throw a NoSuchElementException.
            • remove(int index): int: Removes the data of the Node at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the data of the Node that was removed from the list. If index is invalid, this method should throw an IndexOutOfBoundsException.
 */

import java.util.NoSuchElementException;

public class SinglyLinkedList {
    static class Node {
        int data;
        Node next;
        
        Node() {
            data = 0;
            next = null;
        }
        
        Node(int value) {
            data = value;
            next = null;
        }
        
        Node(int value, Node nextNode) {
            data = value;
            next = nextNode;
        }
    }
    
    private Node head;
    
    public SinglyLinkedList() {
        head = null;
    }
    
    public SinglyLinkedList(int value) {
        head = new Node(value);
    }
    
    public boolean isEmpty() {
        return (head == null);
    }
    
    public int size() {
        Node temp = head;
        int retVal = 0;
        while (temp != null){
            retVal++;
            temp = temp.next;
        }
        return retVal;
    }
    
    public String toString() {
        String retVal = "";
        Node n = head;
        while (n != null) {
            retVal += (n.data + " --> ");
            n = n.next;
        }
        retVal += "null";
        return retVal;
    }
    
    public int get(int index) {
        Node temp = head;
        int length = size();

        if (length-1 < index) throw new IndexOutOfBoundsException();
        else{
            for (int i = 0; i < index; i++){
                temp = temp.next;
            }
            return temp.data;
        }
    }
    
    public boolean contains(int value) {
        Node temp = head;
        while (temp != null){
            if (temp.data == value) return true;
            temp = temp.next;
        }
        return false;
    }
    
    public void add(int value) {
        if (head == null) {
            head = new Node(value);
        }
        else {
            Node temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(value);
        }
    }
    
    public void add(int index, int value) {
        if (index > size()) throw new IndexOutOfBoundsException();
        if (index == 0){
            Node temp = new Node(value);
            temp.next = head;
            head = temp;
            return;
        }
        
        Node curr = head;
        Node next = curr.next;
        for (int i = 0; i < index - 1; i++){
            curr = next;
            next = next.next;
        }
        
        curr.next = new Node(value);
        curr = curr.next;
        curr.next = next;
    }
    
    public int remove() {
        if (head == null){
            throw new NoSuchElementException();
        }
        int retVal = head.data;
        head = head.next;
        return retVal;
    }
    
    public int remove(int index) {
        if (index >= size()){
            throw new IndexOutOfBoundsException();
        }
        int retVal = 0;
        if (index == 0){
            retVal = head.data;
            head = head.next;
        }
        else{
            Node curr = head;
            Node prev = null;
            
            for (int i = 0 ; i < index; i++){
                prev = curr;
                curr = curr.next;
            }
            
            retVal = curr.data;
            prev.next = curr.next;
        }
        return retVal;
    }
}