package Unit_5_Labs_Tree_Heaps_PQs;

public class BSTHeight<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public int height() {
        return root == null ? 0 : height(root);
    }

    private int height(Node current) {
        if (current == null) return -1;
        int leftHeight = height(current.left);
        int rightHeight = height(current.right);
        return Math.max(leftHeight, rightHeight) + 1;
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