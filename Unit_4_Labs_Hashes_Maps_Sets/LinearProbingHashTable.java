package Unit_4_Labs_Hashes_Maps_Sets;

public class LinearProbingHashTable<TKey, TValue> {
    private int size;
    
    private TKey[] keys;
    
    private TValue[] values;
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public LinearProbingHashTable(int initialCapacity) {
        size = 0;
        keys = (TKey[]) new Object[initialCapacity];
        values = (TValue[]) new Object[initialCapacity];
    }
    
    public void put(TKey key, TValue value) {
        if(key == null){
            return;
        }
        int index = Math.abs(key.hashCode()) % keys.length;
        while(keys[index] != null){
            index = (index + 1)%keys.length;
        }
        keys[index] = key;
        values[index] = value;
        size++;
        if(size>=keys.length/2){
            resize(keys.length*2);
        }
    }
    
    public TValue get(TKey key) {
        int index = Math.abs(key.hashCode()) % keys.length;
        while(keys[index] != null){
            if(keys[index].equals(key)){
                return values[index];
            }
            index = (index + 1)%keys.length;
        }
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    private void resize(int newCapacity) {
        TKey[] tempKeys = keys;
        TValue[] tempValues = values;
        keys = (TKey[]) new Object[newCapacity];
        values = (TValue[]) new Object[newCapacity];
        size = 0;
        for (int i = 0; i < tempKeys.length; i++){
            put(tempKeys[i], tempValues[i]);
        }
    }
}