#include <iostream>
#include <stack>
#include <queue>
using namespace std;

// adj matrix implementation of non-weighted graph

#define MAX 10

class graph {
private:
	int mat[MAX][MAX];
	int vert_cnt;
public:
	graph(int v_cnt) {
		vert_cnt = v_cnt;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = 0;
			}
		}
	}
	void accept() {
		cout << "enter " << vert_cnt << " x " << vert_cnt << " graph adj matrix: " << endl;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				cin >> mat[i][j];
			}
		}
	}
	void display() {
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				cout << mat[i][j] << "\t";
			}
			cout << endl;
		}
	}

	void dfs_trav(int start) {
		stack<int> s;
		bool marked[MAX] = { false };
		// push start vertex on stack and mark it as marked
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack and print it
			int trav = s.top();	s.pop();
			cout << trav << ", ";
			// push all non marked neighbours of trav to the stack
			// and mark them as marked.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
	}

	bool is_connected() {
		int start = 0;
		stack<int> s;
		bool marked[MAX] = { false };
		// push start vertex on stack and mark it. also count num of vertices pushed on stack
		int cnt = 1;
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack
			int trav = s.top();	s.pop();
			// push all non marked neighbours of trav to the stack
			// and mark them and also count them
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
					cnt++;
					// when all vertices are visited, it is connected graph
					if (cnt == vert_cnt)
						return true;
				}
			}
		}
		return false; // all vertices not visited
	}

	void dfs_spanning_tree(int start) {
		stack<int> s;
		bool marked[MAX] = { false };
		// push start vertex on stack and mark it
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack
			int trav = s.top();	s.pop();
			// push all non marked neighbours of trav to the stack
			// and mark them as marked and also print the connecting edges
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
					cout << trav << " -> " << j << endl;
				}
			}
		}
	}

	void bfs_trav(int start) {
		queue<int> q;
		bool marked[MAX] = { false };
		// push start vertex on queue and mark it as marked
		q.push(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.empty()) {
			// pop a vertex from queue and print it
			int trav = q.front();	q.pop();
			cout << trav << ", ";
			// push all non marked neighbours of trav to the queue
			// and mark them as marked.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
	}

	void bfs_spannint_tree(int start) {
		queue<int> q;
		bool marked[MAX] = { false };
		// push start vertex on queue and mark it
		q.push(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.empty()) {
			// pop a vertex from queue
			int trav = q.front();	q.pop();
			// push all non marked neighbours of trav to the queue
			// and mark them and also print connecting edge
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.push(j);
					marked[j] = true;
					cout << trav << " -> " << j << endl;
				}
			}
		}
	}

	void bfs_path_length(int start) {
		queue<int> q;
		int dist[MAX] = { 0 };
		bool marked[MAX] = { false };
		// dist of starting vertex is always 0
		dist[start] = 0;
		// push start vertex on queue and mark it
		q.push(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.empty()) {
			// pop a vertex from queue
			int trav = q.front();	q.pop();
			// push all non marked neighbours of trav to the queue
			// and mark them and also calc dist of the neighbour
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.push(j);
					marked[j] = true;
					// dist of neighbour = trav vertex dist + 1 edge
					dist[j] = dist[trav] + 1;
				}
			}
		}
		// print distance array
		cout << "distance of all vertices from vertex " << start << endl;
		for (int i = 0; i < vert_cnt; i++)
			cout << "vertex " << i << " -> " << dist[i] << endl;
	}


	bool is_bipartite() {
		// 0=no color, 1=yellow, -1=green
		queue<int> q;
		int start = 0;
		// all vertices are initially not colored
		int color[MAX] = { 0 };
		// push start vertex on queue and mark it with a color
		q.push(start);
		color[start] = 1;
		// repeat until queue is empty
		while (!q.empty()) {
			// pop a vertex from queue
			int trav = q.front();	q.pop();
			// push all non colored neighbours of trav to the queue
			// and mark them with opposite color.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && color[j] == 0) {
					q.push(j);
					color[j] = -1 * color[trav];
				}
				else if (mat[trav][j] == 1 && color[j] == color[trav])
					return false;
			}
		}
		// all nodes are checked & are divided into two different colors - so it is bipartite
		return true;
	}
};

int main() {
	graph g(6);
	g.accept();
	g.display();
	g.dfs_trav(0);
	g.bfs_trav(0);
	cout << "connected graph: " << boolalpha << g.is_connected() << endl;
	cout << "dfs spanning tree: " << endl;
	g.dfs_spanning_tree(0);
	cout << "bfs spanning tree: " << endl;
	g.bfs_spannint_tree(0);
	g.bfs_path_length(0);
	cout << "bipartite graph: " << boolalpha << g.is_bipartite() << endl;
	return 0;
}

/*

0 1 1 1 0 0
1 0 1 0 1 0
1 1 0 0 0 0
1 0 0 0 1 1
0 1 0 1 0 0
0 0 0 1 0 0

*/



