package Unit_5_Labs_Tree_Heaps_PQs.BST_Methods;

public class PrintKeys<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public void printKeys(){
        printKeys(root);
    }

    private void printKeys(Node node) {
        if (node != null)
        {
            printKeys(node.left);
            System.out.println(node.key);
            printKeys(node.right);
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