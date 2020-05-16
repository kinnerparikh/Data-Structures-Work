package Stack;

public class FixedSizeArrayStack<T> {
    // ADD YOUR CODE HERE.
    private int size;
    private T[] stack;
    
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