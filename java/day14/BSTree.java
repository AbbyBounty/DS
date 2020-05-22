
public class BSTree {
    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }

        public Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BSTree() {
        root = null;
    }

    void add(int val) {
        // alloc & init new Node
        Node newNode = new Node(val);
        // if tree is empty (root is null), newNode is root Node.
        if (root == null) {
            root = newNode;
            return;
        }
        // start traversing from the root
        Node trav = root;
        while (true) {
            // if val is less than trav data
            if (val < trav.data) {
                // if no Node in trav left, add newNode there
                if (trav.left == null) {
                    trav.left = newNode;
                    break;
                }
                // go to left
                trav = trav.left;
            } else { // if val is greater than/equal trav data
                     // if no Node in trav right, add newNode there
                if (trav.right == null) {
                    trav.right = newNode;
                    break;
                }
                // go to right
                trav = trav.right;
            }
        }
    }

    public void preorder(Node trav) {
        if (trav == null)
            return;
        System.out.print(trav.data + ", ");
        preorder(trav.left);
        preorder(trav.right);
    }

    public void preorder() {
        System.out.print("PRE: ");
        preorder(root);
        System.out.println();
    }

    public static void main(String[] args) {
        BSTree t = new BSTree();
        t.add(50);
        t.add(20);
        t.add(10);
        t.add(5);
        t.add(25);
        t.add(30);
        t.add(70);
        t.add(90);
        t.add(60);
        t.preorder();
    }
}