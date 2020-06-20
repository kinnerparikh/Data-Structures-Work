package Unit_5_Labs_Tree_Heaps_PQs.BST_Methods;

public class Size_Rank<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null){
            return 0;
        }else{
            return size(node.left) + size(node.right) + 1;
        }
    }

    public int rank(TKey key) {
        return rank(root, key);
    }

    private int rank(Node node, TKey key) {
        if(node == null){
            return 0;
        }
        if(node.key.compareTo(key) >= 0){
            return rank(node.left, key) + rank(node.right, key);
        }else{
            return rank(node.left, key) + rank(node.right, key) + 1;
        }
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