
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BSTree3 {
    static class Node {
        private int data;
        private Node left;
        private Node right;
        private boolean visited;

        public Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
            this.visited = false;
        }

        public Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
            this.visited = false;
        }
    }

    private Node root;

    public BSTree3() {
        root = null;
    }

    void add(int val) {
        // alloc & init new Node
        Node newNode = new Node(val);
        // if tree is isEmpty (root is null), newNode is root Node.
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

    Node[] binsearch(int key) {
        // start searching from root
        Node trav = root, parent = null;
        // repeat until null is reached
        while (trav != null) {
            // check if key is found
            if (key == trav.data)
                return new Node[] { trav, parent };
            parent = trav;
            // check if key is smaller than current
            if (key < trav.data)
                trav = trav.left;
            else // check if key is greater/equal than current
                trav = trav.right;
        }
        return new Node[] { null, null };
    }

    void preorder() {
        Stack<Node> s = new Stack<>();
        // 1. start traversing from root
        Node trav = root;
        System.out.print("PRE : ");
        while (trav != null || !s.isEmpty()) {
            while (trav != null) {
                // 2. visit trav
                System.out.print(trav.data + ", ");
                // 3. if trav has right, push trav.right on stack
                if (trav.right != null)
                    s.push(trav.right);
                // 4. go to left of trav
                trav = trav.left;
            } // 5. repeat 2-5 until trav is null
              // 6. pop Node from stack into trav
            if (!s.isEmpty())
                trav = s.pop();
        } // 7. repeat 2-6, until trav is null or stack is isEmpty
        System.out.println();
    }

    void inorder() {
        Stack<Node> s = new Stack<>();
        Node trav = root;
        System.out.print("IN  : ");
        while (trav != null || !s.isEmpty()) {
            while (trav != null) {
                s.push(trav);
                trav = trav.left;
            }
            if (!s.isEmpty()) {
                trav = s.pop();
                System.out.print(trav.data + ", ");
                trav = trav.right;
            }
        }
        System.out.println();
    }

    void postorder() {
        Stack<Node> s = new Stack<>();
        // start trav from root
        Node trav = root;
        System.out.print("POST: ");
        // while trav is not null or stack is not isEmpty
        while (trav != null || !s.isEmpty()) {
            // until null is reached
            while (trav != null) {
                // push trav on stack
                s.push(trav);
                // go to trav's left
                trav = trav.left;
            }
            // if stack is not isEmpty
            if (!s.isEmpty()) {
                // pop Node from stack into trav
                trav = s.pop();
                // if trav's right is present & visited
                if (trav.right == null || trav.right.visited == true) {
                    // visit trav & mark it as visited
                    System.out.print(trav.data + ", ");
                    trav.visited = true;
                    // make trav null (so that next Node will be popped from stack)
                    trav = null;
                }
                // otherwise
                else {
                    // push Node on stack
                    s.push(trav);
                    // go to its right
                    trav = trav.right;
                }
            }
        }
        System.out.println();
    }

    Node dfs(int key) {
        Stack<Node> s = new Stack<>();
        Node trav;
        s.push(root);
        while (!s.isEmpty()) {
            trav = s.pop();
            if (key == trav.data)
                return trav;
            if (trav.right != null)
                s.push(trav.right);
            if (trav.left != null)
                s.push(trav.left);
        }
        return null;
    }

    Node bfs(int key) {
        Queue<Node> q = new LinkedList<>();
        Node trav;
        q.offer(root);
        while (!q.isEmpty()) {
            trav = q.poll();
            if (key == trav.data)
                return trav;
            if (trav.left != null)
                q.offer(trav.left);
            if (trav.right != null)
                q.offer(trav.right);
        }
        return null;
    }

    void del(int val) {
        Node temp, parent, pred;
        // find the Node with its parent
        Node[] result = binsearch(val);
        temp = result[0];
        parent = result[1];
        // if Node is not found, return.
        if (temp == null)
            return;
        // if Node has both child
        if (temp.left != null && temp.right != null) {
            // find its pred with pred's parent
            parent = temp;
            pred = temp.left;
            while (pred.right != null) {
                parent = pred;
                pred = pred.right;
            }
            // replace temp's data with pred's data
            temp.data = pred.data;
            // consider pred Node to be deleted
            temp = pred;
        }
        // if Node do not have right child
        if (temp.right == null) {
            if (temp == root)
                root = temp.left;
            else if (temp == parent.left)
                parent.left = temp.left;
            else
                parent.right = temp.left;
            temp = null;
        }
        // if Node do not have left child
        else if (temp.left == null) {
            if (temp == root)
                root = temp.right;
            else if (temp == parent.left)
                parent.left = temp.right;
            else
                parent.right = temp.right;
            temp = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTree3 t = new BSTree3();
        t.add(50);
        t.add(30);
        t.add(90);
        t.add(10);
        t.add(40);
        t.add(70);
        t.add(100);
        t.add(20);
        t.add(80);
        t.add(60);
        t.preorder();
        t.inorder();
        t.postorder();

        int val;
        Node temp, parent;
        System.out.print("Enter value to find: ");
        val = sc.nextInt();
        temp = t.dfs(val);
        if (temp == null)
            System.out.println("Node not found");
        else
            System.out.println("Node found: " + temp.data);

        temp = t.bfs(val);
        if (temp == null)
            System.out.println("Node not found");
        else
            System.out.println("Node found: " + temp.data);

        Node[] result = t.binsearch(val);
        temp = result[0];
        parent = result[1];
        if (temp == null)
            System.out.println("Node not found");
        else {
            System.out.println("Node found: " + temp.data);
            if (parent != null)
                System.out.println("Node parent: " + parent.data);
        }

        t.del(val);
        t.inorder();
        sc.close();
    }
}
