#include <iostream>
using namespace std;

class singly_list; // forward declaration class

class node {
private:
	int data;
	node* next;
public:
	node() {
		data = 0;
		next = NULL;
	}
	node(int val) {
		data = val;
		next = NULL;
	}
	friend class singly_list;
};

class singly_list {
private:
	node* head;
public:
	singly_list() {
		head = NULL;
	}
	~singly_list() {
		del_all();
	}
	void add_first(int val) {
		node* newnode = new node(val);
		newnode->next = head;
		head = newnode;
	}
	void add_last(int val) {
		node* newnode, * trav;
		// alloc new node and init it
		newnode = new node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == NULL)
			head = newnode;
		else {
			// traverse till last node
			trav = head;
			while (trav->next != NULL)
				trav = trav->next;
			// add new node to the next of last node
			trav->next = newnode;
		}
	}
	void display() {
		cout << "LIST: ";
		node* trav = head;
		while (trav != NULL) {
			cout << trav->data << ", ";
			trav = trav->next;
		}
		cout << endl;
	}
	void del_first() {
		node* temp;
		if (head != NULL) {
			temp = head;
			head = head->next;
			delete temp;
		}
	}
	void del_all() {
		while (head != NULL)
			del_first();
	}
};

int main() {
	singly_list l1;
	l1.add_last(20);
	l1.add_last(10);
	l1.add_last(40);
	l1.add_last(60);
	l1.add_last(30);
	l1.add_last(70);
	l1.add_last(50);
	l1.display();
	return 0;
}






