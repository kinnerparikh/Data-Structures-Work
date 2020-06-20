package Unit_5_Labs_Tree_Heaps_PQs;

public class BSTFind<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public TValue find(TKey key) {
        if (key.equals(null)) return null;
        return find(root, key);
    }

    private TValue find(Node current, TKey key) {
        if (current == null) return null;
        if (key.compareTo(current.key) < 0) {
            return find(current.left, key);
        }
        if (key.compareTo(current.key) > 0) {
            return find(current.right, key);
        }
        return current.value;
        
    }

    // This is used by our test code. Do not change.
    Node newNode(TKey key, TValue value, Node left, Node right) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        node.left = left;
        node.right = right;
        return node;
    }
}