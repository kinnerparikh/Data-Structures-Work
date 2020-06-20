package Unit_3_Labs_Stacks_Queues;

public class FixedSizeArrayQueue<T> {
    private T[] queue;
    private int num;
    private int first;
    private int last;
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public FixedSizeArrayQueue(int maxSize) {
        queue = (T[])new Object[maxSize];
        num = 0;
        first = 0;
        last = 0;
    }
    
    public void enqueue(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (num == queue.length) throw new IllegalStateException();
        queue[last++] = newItem;
        if (last == queue.length) last = 0;
        num++;
    }
    
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        T t = queue[first];
        queue[first++] = null;
        num--;
        if (first == queue.length) first = 0;
        return t;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return queue[first];
    }
    
    public boolean isEmpty() {
        return (num == 0);
    }
    
    public int size() {
        return num;
    }
}