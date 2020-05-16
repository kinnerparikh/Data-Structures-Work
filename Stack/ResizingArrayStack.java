package Stack;
public class ResizingArrayStack<T> 
{
    private T[] arr;
    private int n;
    // ADD YOUR CODE HERE.
    
    public ResizingArrayStack() {
        arr = (T[])new Object[1];
        n = 0;
    }
    
    private void resize(int num)
    {
        T[] temp = (T[])new Object[num];
        for (int i = 0; i < n; i++)
        {
            temp[i] = arr[i];   
        }
        arr = temp;
    }
    
    public void push(T newItem) {
        if (newItem == null) throw new IllegalArgumentException();
        if (n == arr.length) resize(arr.length * 2);
        arr[n++] = newItem;
    }
    
    public T pop() {
        if (isEmpty()) throw new IllegalStateException();
        T temp = arr[n - 1];
        arr[--n] = null;
        
        if (n > 0 && n == arr.length/4) resize(arr.length/2);
        return temp;
    }
    
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        return arr[n - 1];
    }
    
    public boolean isEmpty() {
        return (n == 0);
    }
    
    public int size() {
        return n;
    }
}