package Unit_5_Labs_Tree_Heaps_PQs;

// This is a horrible way of implementing this
// The comparable objects should explicitly be 
// of a certain type rather than being of no 
// single type. This is how it is in the lab
// but if you ever use the Comparable operator 
// by itself, you will be banished to the shadow
// realm. 
// Ex. Comparable<T>[] data
// This also confuses the editor a crap ton so
// just dont do it. The compiler can handle it, 
// but there are red lines under the commented
// text so just dont be bad at things. Thank 
// you for attending my TED talk. 

public class Heapsort {
    public static void sort(Comparable[] data) {
        int size = data.length;
        for (int i = size/2; i >= 1; i--)
        {
            sink(data, size, i);
        }
        while (size > 1) {
            exch(data, size, 1);
            sink(data, --size, 1);
        }
    }

    static void sink(Comparable[] data, int size, int k) {
        while (k * 2 <= size) {
            k *= 2;
            if (k < size && data[k].compareTo(data[k-1]) > 0) k++;
            if (!(data[k - 1].compareTo(data[k/2 - 1]) > 0)) break;
            exch(data, k/2, k);
        }
    }

    static void exch(Comparable[] data, int i, int j) {
        Comparable swap = data[i-1];
        data[i-1] = data[j-1];
        data[j-1] = swap;
    }
}