package Unit_5_Labs_Tree_Heaps_PQs.BST_Methods;

public class BSTMin_Max<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public TKey min() {
        if (root == null) return null;
        return min(root);
    }

    private TKey min(Node node) {
        if (node.left == null) return node.key;
        return min(node.left);
    }

    public TKey max() {
        if (root == null) return null;
        return max(root);
    }

    private TKey max(Node node) {
        if (node.right == null) return node.key;
        return max(node.right);
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