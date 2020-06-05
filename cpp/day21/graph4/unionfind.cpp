#include <iostream>
using namespace std;

#define INF 999
#define MAX 10
#define MAX_EDGES	100

class edge {
public:
	int src, dest, weight;
public:
	edge() {
		src = 0;
		dest = 0;
		weight = 0;
	}
	edge(int s, int d, int w) {
		src = s;
		dest = d;
		weight = w;
	}
	void display() {
		cout << src << " -> " << dest << " (" << weight << ")" << endl;
	}
};

class graph {
private:
	int mat[MAX][MAX];
	int vert_cnt, edge_cnt;
	edge edges[MAX_EDGES];
public:
	graph(int v_cnt) {
		vert_cnt = v_cnt;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = INF;
			}
		}
	}
	void accept() {
		int src, dest, weight;
		cout << "enter number of edges: ";
		cin >> edge_cnt;
		cout << "enter src dest weight for " << edge_cnt << " edges" << endl;
		for (int i = 0; i < edge_cnt; i++) {
			cin >> src >> dest >> weight;
			// put weight into adj matrix
			mat[src][dest] = weight;
			mat[dest][src] = weight;
			// also add edge info into array of edges.
			edge temp(src, dest, weight);
			edges[i] = temp;
		}
	}
	void display() {
		unsigned char inf = 236;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[i][j] != INF)
					cout << mat[i][j] << "\t";
				else
					cout << inf << "\t";
			}
			cout << endl;
		}

		for (int i = 0; i < edge_cnt; i++)
			edges[i].display();
	}

	int find_root(int v, int parent[]) {
		// traverse to the parent, until reach to root
		while (parent[v] != -1)
			v = parent[v];
		return v;
	}

	void union_sets(int src_root, int dest_root, int parent[]) {
		parent[src_root] = dest_root;
	}

	bool contains_cycle() {
		int i, parent[MAX];
		// consider each vertex as disjoint set
		for (i = 0; i < vert_cnt; i++)
			parent[i] = -1;
		// go through each edge
		for (i = 0; i < edge_cnt; i++) {
			edge e = edges[i];
			// find roots/sets of src & dest vertex
			int src_root = find_root(e.src, parent);
			int dest_root = find_root(e.dest, parent);
			// if both are in same set, cycle is detected
			if (src_root == dest_root)
				return true;
			// otherwise merge/union both the sets
			union_sets(src_root, dest_root, parent);
		}
		// if all edges are checked, but no cycle
		return false;
	}

};

int main() {
	graph g(9);
	g.accept();
	g.display();
	bool result = g.contains_cycle();
	cout << "contain cycle: " << boolalpha << result << endl;
	return 0;
}

/*
14
7 6 1
8 2 2
6 5 2
0 1 4
2 5 4
8 6 6
2 3 7
7 8 7
0 7 8
1 2 8
3 4 9
5 4 10
1 7 11
3 5 14
*/
