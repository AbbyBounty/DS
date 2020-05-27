
import java.util.Scanner;
import java.util.Stack;

public class BSTree1 {
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

    public BSTree1() {
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

	Node binsearch(int key) {
		// start searching from root
		Node trav = root;
		// repeat until null is reached
		while (trav != null) {
			// check if key is found
			if (key == trav.data)
				return trav;
			// check if key is smaller than current
			if (key < trav.data)
				trav = trav.left;
			else // check if key is greater/equal than current
				trav = trav.right;
		}		
		return null;
	}

	void preorder() {
		Stack<Node> s = new Stack<>();
		//1. start traversing from root
		Node trav = root;
		System.out.print("PRE : ");
		while (trav != null || !s.empty()) {
			while (trav != null) {
				//2. visit trav
				System.out.print(trav.data + ", ");
				//3. if trav has right, push trav.right on stack
				if (trav.right != null)
					s.push(trav.right);
				//4. go to left of trav
				trav = trav.left;
			} //5. repeat 2-5 until trav is null
			//6. pop node from stack into trav
			if (!s.empty())
				trav = s.pop();
		} //7. repeat 2-6, until trav is null or stack is empty
		System.out.println();
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTree1 t = new BSTree1();
        t.add(50);
        t.add(20);
        t.add(10);
        t.add(5);
        t.add(25);
        t.add(30);
        t.add(70);
        t.add(90);
        t.add(60);
        
        System.out.print("Enter element to find: ");
        int key = sc.nextInt();
        Node trav = t.binsearch(key);
        if(trav == null)
            System.out.println("Key not found.");
        else
            System.out.println("Key found: " + trav.data);
        t.preorder();
        sc.close();
    }
}