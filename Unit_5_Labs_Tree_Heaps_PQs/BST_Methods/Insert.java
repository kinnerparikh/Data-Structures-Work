package Unit_5_Labs_Tree_Heaps_PQs.BST_Methods;

public class Insert<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
        
        public Node(TKey key, TValue value)
        {
            this.key = key;
            this.value = value;
        }
    }

    Node root;

    public TValue insert(TKey key, TValue value) {
        if (key.equals(null)) return null;
        if (root == null){ root = new Node(key, value); return null;}
        return insert(root, new Node(key, value));
    }

    private TValue insert(Node current, Node newNode) {
        if (current == null)
        {
            current = newNode;
            return null;
        }
        else if (newNode.key.compareTo(current.key) < 0)
        {
            if (current.left != null)
            {
                return insert(current.left, newNode);
            }
            else
            {
                current.left = newNode;
                return null;
            }
        }
        else if (newNode.key.compareTo(current.key) > 0)
        {
            if (current.right != null)
            {
                return insert(current.right, newNode);
            }
            else
            {
                current.right = newNode;
                return null;
            }
        }
        TValue temp = current.value;
        current.value = newNode.value;
        return temp;
        
    }
}