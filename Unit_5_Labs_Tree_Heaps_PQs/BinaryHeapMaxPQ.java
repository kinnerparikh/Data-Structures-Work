package Unit_5_Labs_Tree_Heaps_PQs;

public class BinaryHeapMaxPQ<Key extends Comparable<Key>> {
    private int size;
    private Key[] heap;

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public BinaryHeapMaxPQ() {
        heap = (Key[]) new Comparable[2];
        size = 0;
    }

    public void insert(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (size == heap.length - 1) resize(heap.length * 2);
        heap[++size] = key;
        swim(size);
    }

    public Key delMax() {
        Key retVal = max();
        heap[1] = null;
        exch(1, size--);
        sink(1);
        heap[size + 1] = null;
        if ((size > 0) && (size == (heap.length - 1) / 4)) resize(heap.length / 2);
        return retVal;
    }

    public Key max() {
        if (heap == null || heap[1] == null) throw new IllegalStateException();
        return (Key)heap[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    private void sink(int k) {
        while (k * 2 <= size) {
            k *= 2;
            if (k < size && heap[k + 1].compareTo(heap[k]) > 0) k++;
            if (!(heap[k].compareTo(heap[k/2]) > 0)) break;
            exch(k/2, k);
        }
    }

    private void swim(int k) {
        while (k > 1 && heap[k/2].compareTo(heap[k]) < 0) {
          exch(k, k/2);
          k /= 2;
        }
    }
    
    private void exch(int i, int j) {
        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}