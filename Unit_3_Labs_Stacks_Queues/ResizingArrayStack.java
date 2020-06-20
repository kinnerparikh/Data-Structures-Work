package Unit_3_Labs_Stacks_Queues;

/*
Create a class called ResizingArrayStack, which uses a resizing-array to store items.
Your stack must store any type.

Specification
    • Constructor: initializes array at size 1.
    • push method: accepts any type and adds it to the top of the stack (returns void).
        ○ When a push would cause the array to overflow, double the size of the array.
        ○ Must throw an IllegalArgumentException if a null item is given.
    • pop method: removes and returns the item at the top of the stack (accepts nothing).
        ○ When a pop would cause the array to reduce to one fourth its current capacity, halve the size of the array.
        ○ If the stack is empty then this method must throw an IllegalStateException.
    • peek method: returns, but does not remove, the top item from the stack (accepts nothing).
        ○ If the stack is empty then this method must throw an IllegalStateException.
    • isEmpty method: tests to see if the stack is empty.
    • size method: returns the number of items in the stack.
    • Your implementation must avoid loitering. Loitering is holding a reference to an object when it is no longer needed. 
      The Java garbage collector can reclaim memory for an object only if there are no outstanding references to it. 
      So eliminating these references (by setting them to null) saves memory.
*/

public class ResizingArrayStack<T> 
{
    private T[] arr;
    private int n;
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public ResizingArrayStack() {
        arr = (T[])new Object[1];
        n = 0;
    }
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    private void resize(int num)
    {
        T[] temp = (T[])new Object[num];
        for (int i = 0; i < n; i++)
        {
            temp[i] = arr[i];   
        }
        arr = temp;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (n == arr.length) resize(arr.length * 2);
        arr[n++] = newItem;
    }
    
    public T pop() {
        if (isEmpty()) throw new IllegalStateException();
        T temp = arr[n - 1];
        arr[--n] = null;
        
        if (n > 0 && n == arr.length/4) resize(arr.length/2);
        return temp;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return arr[n - 1];
    }
    
    public boolean isEmpty() {
        return (n == 0);
    }
    
    public int size() {
        return n;
    }
}