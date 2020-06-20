package Unit_5_Labs_Tree_Heaps_PQs.BST_Methods;

public class Floor<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public TKey floor(TKey key) {
        return floor(root, key);
    }

    private TKey floor(Node node, TKey key) {
        if (node == null) return null;
        if (node.key == key) return key;
        if (node.key.compareTo(key) > 0) return floor(node.left, key);
        TKey f = floor(node.right, key);
        
        //return (f != null && f.compareTo(key) <= 0) ? f : node.key;
        return (f == null) ? node.key : f;
        
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