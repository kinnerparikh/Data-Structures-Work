package LinkedList;
import java.util.*;

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