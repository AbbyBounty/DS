

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
		Node newNode = new Node(val);
		newNode.next = head;
		head = newNode;
	}

    public void addLast(int val) {
		Node newNode, trav;
		// alloc new Node and init it
		newNode = new Node(val);
		// special case: if list is empty, newNode itself is first Node
		if (head == null)
			head = newNode;
		else {
			// traverse till last Node
			trav = head;
			while (trav.next != null)
				trav = trav.next;
			// add new Node to the next of last Node
			trav.next = newNode;
		}
	}

    public void addAtPos(int pos, int val) {
		Node newNode, trav;
		// special2: user give pos = 1 or less than that
		if (pos <= 1) {
			addFirst(val);
			return;
		}
		// alloc Node and init it
		newNode = new Node(val);
		// special1: if list is empty, new Node is first Node
		if (head == null) {
			head = newNode;
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
		// newNode'next to trav's next
		newNode.next = trav.next;
		// previous (trav) 'next to newNode
		trav.next = newNode;
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

	void delFirst() {
		if (head != null)
			head = head.next;
		// unreferned old head Node will be garbage collected.
	}
	void delAll() {
		while (head != null)
			delFirst();
	}
	void delAtPos(int pos) {
		Node temp, trav;
		int i;
		//special 1 & 2: if list is empty or pos == 1, delete the first Node
		if (head == null || pos == 1)
			delFirst();
		else {
			//1. trav till pos-1
			trav = head;
			for (i = 1; i < pos - 1; i++) {
				//special 3: if pos is beyond size of list
				if (trav.next == null)
					return;
				trav = trav.next;
			}
			//2. get pointer temp to the next Node (to be deleted)
			temp = trav.next;
			//3. trav's next to temp's next
			trav.next = temp.next;
			//4. delete temp Node
			temp = null; // Node will be garbage collected.
		}
	}

	void reverse() {
		Node oldhead,  temp;
		// consider cur list as old list & make cur list empty
		oldhead = head;
		head = null;
		while (oldhead != null) {
			// delete first Node from old list
			temp = oldhead;
			oldhead = oldhead.next;
			// add it to start of new list
			temp.next = head;
			head = temp;
		}
	}

	void rev_display(Node trav) {
		if (trav == null)
			return;
		rev_display(trav.next);
		System.out.print(trav.data + ", ");
	}
	void rev_display() {
		System.out.print("REV : ");
		rev_display(head);
		System.out.println();
	}
	void find_mid() {
		Node slow = head,  fast = head;
		if (head != null) {
			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			System.out.println("Mid: " + slow.data);
		}
	}
	Node revrec(Node trav) {
		if (trav.next == null) {
			head = trav;
			return trav;
		}
		Node travnext = revrec(trav.next);
		travnext.next = trav;
		return trav;
	}
	void revrec() {
		if (head != null) {
			Node trav = revrec(head);
			trav.next = null;
		}
	}

	void find_common(SinglyList other) {
		System.out.print("Common : ");
		Node trav1 = this.head,  trav2 = other.head;
		while (trav1 != null && trav2 != null) {
			if (trav1.data == trav2.data) {
				System.out.print(trav1.data + ", ");
				trav1 = trav1.next;
				trav2 = trav2.next;
			}
			else {
				if (trav1.data < trav2.data)
					trav1 = trav1.next;
				else
					trav2 = trav2.next;
			}
		}
		System.out.println();
	}

	Node del(Node prev, Node trav) {
		Node travnext = trav.next;
		if (prev == null)
			head = travnext;
		else 
			prev.next = travnext;
		return travnext;
	}

	void del_common(SinglyList other) {
		System.out.print("Common : ");
		Node trav1 = this.head,  trav2 = other.head;
		Node prev = null;
		while (trav1 != null && trav2 != null) {
			if (trav1.data == trav2.data) {
				System.out.print(trav1.data + ", ");
				trav1 = del(prev, trav1);
				trav2 = trav2.next;
			}
			else {
				if (trav1.data < trav2.data) {
					prev = trav1;
					trav1 = trav1.next;
				} 
				else
					trav2 = trav2.next;
			}
		}
		System.out.println();
	}

	void del_duplicates() {
		Node trav1,  trav2,  prev2,  next2;
		for (trav1 = head; trav1 != null; trav1 = trav1.next) {
			prev2 = trav1;
			for (trav2 = trav1.next; trav2 != null; trav2 = next2) {
				next2 = trav2.next;
				if (trav1.data == trav2.data)
					del(prev2, trav2);
				else
					prev2 = trav2;
			}
		}
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
		l1.delFirst();
		l1.display();
		l1.delFirst();
		l1.display();
		l1.delAtPos(3);
		l1.display();
		l1.delAtPos(4);
		l1.display();
		l1.delAtPos(5);
		l1.display();
		l1.delAll();
		l1.display();
	}
}
