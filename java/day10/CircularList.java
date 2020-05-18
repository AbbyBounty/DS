

class CircularList {

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

    public CircularList() {
		head = null;
	}

	void display() {
		System.out.print("LIST: ");
		if (head != null) {
			Node trav = head;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.next;
			} while (trav != head);
		}
		System.out.println();
	}
	void addLast(int val) {
		Node newNode,  trav;
		// alloc new Node and init it
		newNode = new Node(val);
		// special case: if list is empty, newNode itself is first Node
		if (head == null) {
			head = newNode;
			newNode.next = head;
		} else {
			// traverse till last Node
			trav = head;
			while (trav.next != head)
				trav = trav.next;
			// newNode's next to head
			newNode.next = head;
			// add new Node to the next of last Node
			trav.next = newNode;
		}
	}
	void addFirst(int val) {
		Node newNode,  trav;
		// alloc new Node and init it
		newNode = new Node(val);
		// special case: if list is empty, newNode itself is first Node
		if (head == null) {
			head = newNode;
			newNode.next = head;
		}
		else {
			// traverse till last Node
			trav = head;
			while (trav.next != head)
				trav = trav.next;
			// newNode's next to head
			newNode.next = head;
			// add new Node to the next of last Node
			trav.next = newNode;
		}
		// head point to newNode
		head = newNode;
	}
	void addAtPos(int pos, int val) {
		Node newNode,  trav;
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
			if (trav.next == head)
				break;
			trav = trav.next;
		}
		// newNode'next to trav's next
		newNode.next = trav.next; // head
		// previous (trav) 'next to newNode
		trav.next = newNode;
	}
	void delFirst() {
		Node trav,  temp;
		// special1: if list empty, return
		if (head == null)
			return;
		// special2: if single Node, delete it.
		if (head.next == head) {
			head = null; // first node will be garbage collected
			return;
		}
		// traverse till last Node (trav)
		trav = head;
		while (trav.next != head)
			trav = trav.next;
		// take first Node addr in temp
		temp = head;
		// take head to next Node
		head = head.next;
		// last Node (trav) point to new head
		trav.next = head;
		// temp node will be garbage collected
		temp = null;
	}
	void delAtPos(int pos) {
		Node temp,  trav;
		int i;
		//special 1 & 2: if list is empty or pos == 1, delete the first Node
		if (head == null || pos == 1)
			delFirst();
		else {
			//1. trav till pos-1
			trav = head;
			for (i = 1; i < pos - 1; i++) {
				//special 3: if pos is beyond size of list
				if (trav.next == head)
					return;
				trav = trav.next;
			}
			//2. get pointer temp to the next Node (to be deleted)
			temp = trav.next;
			//3. trav's next to temp's next
			trav.next = temp.next;
			//4. delete temp Node
			temp=null; // temp will be garbage collected
		}
	}
	void del_all() {
		// convert circular list to linear list
		if (head != null) {
			Node temp = head;
			head = head.next;
			temp.next = null;
		}
		// delete singly linear list
		while (head != null) {
			head = head.next;
			// old head will be garbage collected
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
	//	l1.delAll();
	//	l1.display();
	}
}
