

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

    public void display() {
        System.out.print("LIST: ");
        Node trav = head;
		while (trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}

	public void selection_sort() {
		Node i, j;
		for (i = head; i != null; i = i.next) {
			for (j = i.next; j != null; j = j.next) {
				if (i.data > j.data) {
					int temp = i.data;
					i.data = j.data;
					j.data = temp;
				}			
			}
		}
	}

    public static void main(String[] args) {
        SinglyList l1 = new SinglyList();
        l1.addLast(30);
        l1.addLast(20);
        l1.addLast(50);
        l1.addLast(10);
        l1.addLast(60);
        l1.addLast(40);
		l1.display();
		l1.selection_sort();
		l1.display();		
    }
}
