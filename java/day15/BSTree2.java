import java.util.Scanner;

public class BSTree2 {
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

    public BSTree2() {
        root = null;
    }

	void add(int val) {
		// if tree is empty, root is new Node
		if (root == null)
			root = new Node(val);
		// else add Node in tree (root)
		else
			add(root, val);
	}
	void add(Node trav, int val) {
		// if val < trav.data, 
		if (val < trav.data) {
			// if no left sub tree present, add new Node to left
			if (trav.left == null) {
				trav.left = new Node(val);
				return;
			}
			// add to left subtree
			add(trav.left, val);
		}
		// if val >= trav.data
		else {
			// if no left sub tree present, add new Node to right
			if (trav.right == null) {
				trav.right = new Node(val);
				return;
			}
			// add to right subtree
			add(trav.right, val);
		}
	}

	Node binsearch(Node trav, int key) {
		// if sub-tree is empty, return
		if (trav == null)
			return null;
		// compare key with current root (trav). if equal, return address of Node
		if (key == trav.data)
			return trav;
		// if key is smaller than current root, search in left sub-tree
		if (key < trav.data)
			return binsearch(trav.left, key);
		// else search in right sub-tree
		else
			return binsearch(trav.right, key);
		// using ternary operator
		// return binsearch(key < trav.data ? trav.left : trav.right, key);
	}

	Node binsearch(int val) {
		return binsearch(root, val);
	}

	void preorder(Node trav) {
		if (trav == null)
			return;
		System.out.print(trav.data + ", ");
		preorder(trav.left);
		preorder(trav.right);
	}
	void preorder() {
		System.out.print("PRE: ");
		preorder(root);
		System.out.println();
	}

	void inorder(Node trav) {
		if (trav == null)
			return;
		inorder(trav.left);
		System.out.print(trav.data + ", ");
		inorder(trav.right);
	}
	void inorder() {
		System.out.print("IN : ");
		inorder(root);
		System.out.println();
	}

	void postorder(Node trav) {
		if (trav == null)
			return;
		postorder(trav.left);
		postorder(trav.right);
		System.out.print(trav.data + ", ");
	}
	void postorder() {
		System.out.print("POST: ");
		postorder(root);
		System.out.println();
	}

	void delall(Node trav) {
		if (trav == null)
			return;
		delall(trav.left);
		delall(trav.right);
		trav = null;
	}
	void delall() {
		delall(root);
		root = null;
	}

	int height(Node trav) {
		// if null tree, height = 0
		if (trav == null)
			return 0;
		// find height of left subtree
		int hl = height(trav.left);
		// find height of right subtree
		int hr = height(trav.right);
		// height of tree = MAX (height of left, height of right) + 1
		int max = hl > hr ? hl : hr;
		return max + 1;
	}

	int height() {
		return height(root);
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTree2 t = new BSTree2();
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
        t.inorder();
        t.postorder();
        System.out.println("Height: " + t.height());
        System.out.print("Enter element to find: ");
        int key = sc.nextInt();
        Node trav = t.binsearch(key);
        if(trav == null)
            System.out.println("Key not found.");
        else
            System.out.println("Key found: " + trav.data);
        sc.close();
    }
}