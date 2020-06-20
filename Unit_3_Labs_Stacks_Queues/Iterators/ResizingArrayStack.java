package Unit_3_Labs_Stacks_Queues.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] stack;
    private int size;
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public ResizingArrayStack() {
        stack = (T[])(new Object[1]);
        size = 0;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (size == stack.length) resize(2 * stack.length);
        stack[size++] = newItem;
    }
    
    public T pop() {
        if (isEmpty()) throw new IllegalStateException();
        T t = stack[size - 1];
        stack[--size] = null;
        if (size > 0 && size == stack.length / 4) resize(stack.length / 2);
        return t;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return stack[size - 1];
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    private void resize(int endSize)
    {
        T[] temp = (T[])(new Object[endSize]);
        for (int i = 0; i < stack.length; i++)
        {
            temp[i] = stack[i];
        }
        stack = temp;
    }
    
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<T> 
    {
        private int index;

        public ArrayIterator()
        {
            index = size-1;
        }

        public boolean hasNext() 
        {
            return index >= 0;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            return stack[index--];
        }
    }
}