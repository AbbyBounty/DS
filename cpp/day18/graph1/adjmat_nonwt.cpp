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
		bool visited[MAX] = { false };
		// push start vertex on stack and mark it as visited
		s.push(start);
		visited[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack and print it
			int trav = s.top();	s.pop();
			cout << trav << ", ";
			// push all non visited neighbours of trav to the stack
			// and mark them as visited.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && visited[j] == false) {
					s.push(j);
					visited[j] = true;
				}
			}
		}
		cout << endl;
	}
	void bfs_trav(int start) {
		queue<int> q;
		bool visited[MAX] = { false };
		// push start vertex on queue and mark it as visited
		q.push(start);
		visited[start] = true;
		// repeat until queue is empty
		while (!q.empty()) {
			// pop a vertex from queue and print it
			int trav = q.front();	q.pop();
			cout << trav << ", ";
			// push all non visited neighbours of trav to the queue
			// and mark them as visited.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && visited[j] == false) {
					q.push(j);
					visited[j] = true;
				}
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
	g.bfs_trav(0);
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



