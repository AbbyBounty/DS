#include <iostream>
using namespace std;

#define INF 999
#define MAX 10

class graph {
private:
	int mat[MAX][MAX];
	int vert_cnt, edge_cnt;
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
			mat[src][dest] = weight;
			mat[dest][src] = weight;
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
	}
	/*
	int find_min(int arr[], int n) {
		int i, min = INF;
		for (i = 0; i < n; i++) {
			if (arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	*/
	int get_min(int key[], bool mst[], int vert_cnt) {
		int i, min_key = INF, min_vert = 0;
		for (i = 0; i < vert_cnt; i++) {
			if (mst[i] == false && key[i] <= min_key) {
				min_key = key[i];
				min_vert = i;
			}
		}
		return min_vert;
	}
	void prim(int start) {
		// create mstset to keep track of vertices in mst. initially all are false
		bool mst[MAX] = { false };
		// assign key to each vertex as infintiy. for start vertex it is 0.
		int cur, i, j, key[MAX];
		for (i = 0; i < vert_cnt; i++)
			key[i] = INF;
		key[start] = 0;
		// keep track of parent of each vertex. initially parent of all is -1.
		int parent[MAX];
		for (i = 0; i < vert_cnt; i++)
			parent[i] = -1;

		// repeat until all vertices are added into mst
		for (i = 0; i < vert_cnt; i++) {
			// find min key vertex, which is not added in mst
			cur = get_min(key, mst, vert_cnt);
			// add that vertex into mst
			mst[cur] = true;
			// update key & parent of all neighbours (which are not yet in mst & their key is more than the weight of connecting edge)
			for (j = 0; j < vert_cnt; j++) {
				if (mat[cur][j] != INF && mst[j] == false && key[j] > mat[cur][j]) {
					key[j] = mat[cur][j];
					parent[j] = cur;
				}
			}
		}

		// print min spanning tree (parent's array)
		for (i = 0; i < vert_cnt; i++)
			cout << i << " -> " << parent[i] << endl;
 	}
};

int main() {
	graph g(9);
	g.accept();
	g.display();
	g.prim(0);
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
