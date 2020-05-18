

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
		// unreferned old head node will be garbage collected.
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
			temp = null; // node will be garbage collected.
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
