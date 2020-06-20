package Unit_3_Labs_Stacks_Queues;

public class ResizingArrayQueue<T> {
    private T[] queue;
    private int size;
    private int first;
    private int last;

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public ResizingArrayQueue() {
        queue = (T[])new Object[1];
        size = 0;
        first = 0;
        last = 0;
    }
    
    public void enqueue(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (size == queue.length) resize(2*queue.length);
        queue[last++] = newItem;
        if (last == queue.length) last = 0;
        size++;
    }
    
    
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        T t = queue[first];
        queue[first++] = null;
        size--;
        if (first == queue.length) first = 0;
        if (size > 0 && size == queue.length/4) resize(queue.length/2); 
        return t;
    }

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    private void resize(int num)
    {
        T[] temp = (T[])new Object[num];
        for (int i = 0; i < this.size; i++)
        {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
        first = 0;
        last = size;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return queue[first];
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }
}