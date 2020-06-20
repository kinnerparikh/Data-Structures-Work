package Unit_3_Labs_Stacks_Queues;

public class OrderedArrayMaxPQ<T extends Comparable<T>> {
    // ADD YOUR CODE HERE.
    private int size;
    private T[] pq;

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public OrderedArrayMaxPQ() {
        pq = (T[])(new Comparable[1]);
        size = 0;
    }
    
    public void insert(T key) {
        if (key == null) throw new IllegalStateException();
        int temp = size - 1;
        while (temp >= 0 && (key.compareTo(pq[temp]) < 0)) {
            pq[temp + 1] = pq[temp--];
        }
        if (temp + 1 >= pq.length)
        {
            resize(true);
        }
        pq[temp + 1] = key;
        size++;
    }
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public void resize(boolean increaseSize)
    {
        T[] temp;
        if (increaseSize) 
        {
            temp = (T[])(new Comparable[pq.length * 2]);
            for (int i = 0; i < pq.length; i++)
            {
                temp[i] = pq[i]; 
            }
        }
        else
        {
            temp = (T[])(new Comparable[pq.length / 2]);
            for (int i = 0; i < size; i++)
            {
                temp[i] = pq[i];
            }
        }
        pq = temp;
    }
    
    public T delMax() {
        if (size == 0) throw new IllegalStateException();
        size--;
        if (size < pq.length / 4)
        {
            resize(false);
        }
        return pq[size];
    }
    
    public T max() {
        return pq[size - 1];
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }
}