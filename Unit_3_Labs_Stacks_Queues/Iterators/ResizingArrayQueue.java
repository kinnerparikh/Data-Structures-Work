package Unit_3_Labs_Stacks_Queues.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<T> implements Iterable<T> {
    private T[] queue;
    private int size;
    private int first;
    private int last; 
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public ResizingArrayQueue() {
        queue = (T[])(new Object[1]);
        size = 0;
    }
    
    public void enqueue(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (size == queue.length) resize(2*queue.length);   
        queue[last++] = newItem;                        
        if (last == queue.length) last = 0;          
        size++;
    }
    
    public T dequeue() {
        if (size == 0) throw new IllegalStateException();
        T t = queue[first];
        queue[first] = null;
        size--;
        first++;
        if (first == queue.length) first = 0;
        if (size > 0 && size == queue.length / 4) resize(queue.length / 2);
        return t;
    }
    
    public T peek() {
        if (size == 0) throw new IllegalStateException();
        return queue[first];
    }
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public void resize(int nextSize)
    {
        T[] temp = (T[]) new Object[nextSize];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
        first = 0;
        last  = size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<T> 
    {
        private int index;

        public ArrayIterator()
        {
            index = first;
        }

        public boolean hasNext() 
        {
            return index < last;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            T item = queue[index];
            index++;
            return item;
        }
    }
}