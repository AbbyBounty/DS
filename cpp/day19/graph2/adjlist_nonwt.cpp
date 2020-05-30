#include <iostream>
#include <vector>
#include <list>
#include <stack>
using namespace std;

#define MAX 10

class graph {
private:
	vector< list<int> > edges;
public:
	graph(int v_cnt) {
		list<int> emp_list;
		for (int i = 1; i <= v_cnt; i++)
			edges.push_back(emp_list);
	}
	void accept() {
		int edge_cnt, from, to;
		cout << "enter number of edges: ";
		cin >> edge_cnt;
		cout << "enter edges from -> to: " << endl;
		for (int i = 0; i < edge_cnt; i++) {
			cin >> from >> to;
			edges[from].push_back(to);
			edges[to].push_back(from); // graph is undirected (all edges are like bi-direction).
		}
	}
	void display() {
		for (unsigned i = 0; i < edges.size(); i++) {
			cout << i << " = ";
			list<int>::iterator itr = edges[i].begin();
			while (itr != edges[i].end()) {
				cout << *itr << " -> ";
				itr++;
			}
			cout << endl;
		}
	}

	void dfs_trav(int start) {
		stack<int> s;
		bool marked[MAX] = { false };

		s.push(start);
		marked[start] = true;
		while (!s.empty()) {
			int trav = s.top();	s.pop();
			cout << trav << ", ";
			list<int>::iterator itr = edges[trav].begin();
			while (itr != edges[trav].end()) {
				if (marked[*itr] == false) {
					s.push(*itr);
					marked[*itr] = true;
				}
				itr++;
			}
		}
		cout << endl;
	}
};

int main() {
	graph g(6);
	g.accept();
	g.display();
	g.dfs_trav(0);
	return 0;
}

/*
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5
*/








