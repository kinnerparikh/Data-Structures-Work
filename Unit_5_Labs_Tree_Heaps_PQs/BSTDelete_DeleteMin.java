package Unit_5_Labs_Tree_Heaps_PQs;

public class BSTDelete_DeleteMin<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    /**
     * Deletes the minimum node in the binary search tree.
     */
    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }

    /**
     * Deletes the minimum node in the subtree with root {@param node}.
     * 
     * Returns the root of the new subtree at {@param node}'s initial position.
     */
    private Node deleteMin(Node node) {
        return delete(root, min(root).key);
    }

    /**
     * Deletes the node with key equal to {@param key} in the binary search tree.
     */
    public void delete(TKey key) {
        if (key == null) return;
        root = delete(root, key);
    }

    /**
     * Deletes the node with key equal to {@param key} in the subtree with root {@param node}.
     * 
     * Returns the root of the new subtree at {@param node}'s initial position.
     */
    private Node delete(Node node, TKey key) {
        if (node == null) return node;
        if (key.compareTo(node.key) < 0) node.left = delete(node.left, key);
        else if (key.compareTo(node.key) > 0) node.right = delete(node.right, key);
        else
        {
            if (node.left == null)
            {
                return node.right;
            }
            if (node.right == null)
            {
                return node.left;
            }
            node.key = min(node.right).key;
            node.right = delete(node.right, node.key);
        }
        return node;
    }

    /**
     * Finds and returns the minimum node starting at the subtree with root {@param node}.
     * 
     * Hint: Use this to implement Case 3 (2 children) in Hibbard deletion.
     */
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
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