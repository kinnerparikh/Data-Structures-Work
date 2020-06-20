package Unit_3_Labs_Stacks_Queues;

/*
Create a class called FixedSizeArrayStack, which uses a fixed-size array to store items.
Your stack must store any type.

Specification
    • Constructor: takes in maximum size.
    • push method: accepts any type and adds it to the top of the stack (returns void).
        ○ Must throw an IllegalArgumentException if a null item is given.
        ○ Must throw IllegalStateException if the stack is full.
    • pop method: removes and returns the item at the top of the stack (accepts nothing).
        ○ If the stack is empty then this method must throw an IllegalStateException.
    • peek method: returns, but does not remove, the top item from the stack (accepts nothing).
        ○ If the stack is empty then this method must throw an IllegalStateException.
    • isEmpty method: tests to see if the stack is empty.
    • size method: returns the number of items in the stack.
    • Your implementation must avoid loitering. Loitering is holding a reference to an object when it is no longer needed. The Java garbage collector can reclaim memory for an object only if there are no outstanding references to it. So eliminating these references (by setting them to null) saves memory.
*/

public class FixedSizeArrayStack<T> {
    private int size;
    private T[] stack;

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public FixedSizeArrayStack(int maxSize) {
        stack = (T[])new Object[maxSize];
        size = 0;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (size == stack.length) throw new IllegalStateException();
        
        stack[size] = newItem;
        size++;
    }
    
    public T pop() {
        if (isEmpty()) throw new IllegalStateException();
        T temp = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return temp;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return stack[size - 1];
    }
    
    public boolean isEmpty() {
        return (size <= 0);
    }
    
    public int size()
    {
        return size;
    }
}