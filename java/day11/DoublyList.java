

class DoublyList {

    static class Node {
        private int data;
        private Node next;
		private Node prev;

        public Node() {
            data = 0;
            next = null;
			prev = null;
		}
    
        Node(int val) {
            data = val;
			next = null;
			prev = null;
        }
    }

    private Node head;

    public DoublyList() {
		head = null;
	}

	void display() {
		System.out.print("FWD LIST: ");
		Node trav = head;
		while (trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}
	void displayRev() {
		System.out.print("REV LIST: ");
		// traverse till last Node
		Node trav;
		if (head != null) {
			trav = head;
			while (trav.next != null)
				trav = trav.next;
			// visit each Node in reverse order (using prev pointer)
			while (trav != null) {
				System.out.print(trav.data + ", ");
				trav = trav.prev;
			}
		}
		System.out.println();
	}
	void addFirst(int val) {
		// alloc & init new Node
		Node newNode = new Node(val);
		// special 1: if list empty, newNode is first Node
		if (head == null)
			head = newNode;
		// add Node at the start & head to it.
		else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
	}
	void addLast(int val) {
		Node newNode,  trav;
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
			// newNode prev to last Node
			newNode.prev = trav;
		}
	}
	void addAtPos(int pos, int val) {
		Node newNode,  trav, temp;
		// special 1 & 2: if list is empty, new Node is first Node | user give pos = 1 or less than that, new Node should be first
		if (head == null || pos <= 1) {
			addFirst(val);
			return;
		}
		// alloc Node and init it
		newNode = new Node(val);
		// trav till pos-1
		trav = head;
		for (int i = 1; i < pos - 1; i++) {
			// special3: if pos is beyond size of list
			if (trav.next == null)
				break;
			trav = trav.next;
		}
		// take address of next pos into temp
		temp = trav.next;
		// newNode'next to trav's next
		newNode.next = temp;
		// newNode'prev to trav
		newNode.prev = trav;
		// trav 'next to newNode
		trav.next = newNode;
		// temp's prev to newNode
		if (temp != null) // special4: pos is immediately after last Node (temp will null).
			temp.prev = newNode;
	}
	void delFirst() {
		Node temp;
		// special1: if list is empty, do nothing.
		if (head != null) {
			// take addr of first Node into temp
			temp = head;
			// take head to next Node
			head = head.next;
			// temp Node will be garbage collected
			temp = null;
			// special2: list having only one Node, head will become null.
			if(head != null)
				// set new head's prev to null
				head.prev = null;
		}
	}

	void delAtPos(int pos) {
		Node trav;
		int i;
		//special 1 & 2: if list is empty or pos == 1, delete the first Node
		if (head == null || pos == 1)
			delFirst();
		else {
			//1. trav till pos
			trav = head;
			for (i = 1; i < pos; i++) {
				//special 3: if pos is beyond size of list
				if (trav == null)
					return;
				trav = trav.next;
			}
			//3. trav prev's next to trav nex
			trav.prev.next = trav.next;
			//special 4: delete the last pos Node (trav next will be null).
			if (trav.next != null)
				//4. trav next's prev to trav's prev
				trav.next.prev = trav.prev;
			//5. trav Node will be garbage collected
			trav = null;
		}
	}

	void delAll() {
		while (head != null)
			delFirst();
	}

    public static void main(String[] args) {
        DoublyList l1 = new DoublyList();
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
