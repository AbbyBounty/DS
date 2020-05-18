

class SinglyList {

    static class Node {
        private int data;
        private Node next;
    
        public Node() {
            data = 0;
            next = null;
        }
    
        Node(int val) {
            data = val;
            next = null;
        }
    }

    private Node head;

    public SinglyList() {
		head = null;
	}

    public void addFirst(int val) {
		Node newnode = new Node(val);
		newnode.next = head;
		head = newnode;
	}

    public void addLast(int val) {
		Node newnode, trav;
		// alloc new node and init it
		newnode = new Node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == null)
			head = newnode;
		else {
			// traverse till last node
			trav = head;
			while (trav.next != null)
				trav = trav.next;
			// add new node to the next of last node
			trav.next = newnode;
		}
	}

    public void addAtPos(int pos, int val) {
		Node newnode, trav;
		// special2: user give pos = 1 or less than that
		if (pos <= 1) {
			addFirst(val);
			return;
		}
		// alloc node and init it
		newnode = new Node(val);
		// special1: if list is empty, new node is first node
		if (head == null) {
			head = newnode;
			return;
		}
		// trav till pos-1
		trav = head;
		for (int i = 1; i < pos - 1; i++) {
			// special3: if pos is beyond size of list
			if (trav.next == null)
				break;
			trav = trav.next;
		}
		// newnode'next to trav's next
		newnode.next = trav.next;
		// previous (trav) 'next to newnode
		trav.next = newnode;
	}

    public void display() {
        System.out.print("LIST: ");
        Node trav = head;
		while (trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}

    public static void main(String[] args) {
        SinglyList l1 = new SinglyList();
        l1.addLast(10);
        l1.addLast(20);
        l1.addLast(30);
        l1.addFirst(40);
        l1.addAtPos(3, 50);
        l1.addAtPos(1, 60);
        l1.addAtPos(8, 70);
        l1.display();
    }
}
