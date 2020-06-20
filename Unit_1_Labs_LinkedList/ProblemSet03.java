package Unit_1_Labs_LinkedList;

/*
Problem 0 – Find Minnie
Implement the method getMin, which takes a single Node reference, head, that points to the beginning of a linked list. getMin should return the Node whose data is the minimum of the list. If head is null, then there is no minimum and the method should return null.

Problem 1 – Merging Two Linked Lists
Implement the method, mergeTwoLists, which merges two sorted linked lists and returns it as a single sorted list. The new list should be made by splicing together the nodes of the first two lists. Return the head of the merged linked list.

Problem 2 – tsiL dekniL a esreveR
Write the method, reverseList, which reverses a linked list. The original head should be the last element of the new list, the second node should become the second to last node, etc. This method returns the head of the newly reversed list.

Problem 3 – Was it a rat I saw?
A palindrome is read the same forwards as it is backwards. Implement the method, isPalindrome, which determines if a linked list is a palindrome.
*/

class Node {
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }
}

public class ProblemSet03 {
    public static Node getMin(Node head) {
        int minInt = Integer.MAX_VALUE;
        Node minNode = null;
        while (head != null){
            if (head.data < minInt){
                minInt = head.data;
                minNode = head;
            }
            
            head = head.next;
        }
        
        return minNode;
    }

    public static Node mergeTwoLists(Node head1, Node head2) {
        Node temp = new Node(0); 
        Node retNode = temp; 
        while(head1 != null && head2 != null)  
        {
            if(head1.data < head2.data) 
            { 
                retNode.next = head1; 
                head1 = head1.next; 
            }  
            else
            { 
                retNode.next = head2; 
                head2 = head2.next; 
            } 
            retNode = retNode.next; 
        }
        if (head1 == null) retNode.next = head2;
        if (head2 == null) retNode.next = head1;
        return temp.next; 
    }

    public static Node reverseList(Node head) {
        if (head == null) return head;
        Node prev = null; 
        Node current = head; 
        Node next = null; 
        while (current != null) { 
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next; 
        } 
        head = prev; 
        return head; 
    }

    public static boolean isPalindrome(Node head) {
        int size = 0;
        Node temp = head;
        while (temp != null)
        {
            size++;
            temp = temp.next;
        }
        
        for (int i = 0; i < size / 2; i++)
        {
            temp = head;
            for (int j = 0; j < i; j++)
            {
                temp = temp.next;
            }
            int currData = temp.data;
            
            temp = head;
            for (int j = 0; j < size--; j++)
            {
                temp = temp.next;
            }
            if (temp.data != currData) return false;
        }
        
        return true;
    }
}