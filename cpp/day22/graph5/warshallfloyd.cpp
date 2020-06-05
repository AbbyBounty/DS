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
			// put weight into adj matrix for directed graph
			mat[src][dest] = weight;
		//	mat[dest][src] = weight;
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

	void warshall_floyd() {
		// create dist matrix & init with adj matrix.
		int i, s, d, dist[MAX][MAX];
		for (s = 0; s < vert_cnt; s++) {
			for (d = 0; d < vert_cnt; d++)
				dist[s][d] = mat[s][d];
			// dist of each vertex from itself is 0.
			dist[s][s] = 0;
		}

		// consider each vertex i as intermediate between s (src) & d (dest).
		for (i = 0; i < vert_cnt; i++) {
			for (s = 0; s < vert_cnt; s++) {
				for (d = 0; d < vert_cnt; d++) {
					if(dist[s][i] != INF && dist[i][d] != INF && dist[s][i] + dist[i][d] < dist[s][d])
						dist[s][d] = dist[s][i] + dist[i][d];
				}
			}
		}

		// print dist matrix
		cout << "\n distance matrix: " << endl;
		unsigned char inf = 236;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				if (dist[i][j] != INF)
					cout << dist[i][j] << "\t";
				else
					cout << inf << "\t";
			}
			cout << endl;
		}
	}
};

int main() {
	graph g(5);
	g.accept();
	g.display();
	g.warshall_floyd();
	return 0;
}

/*
8
1 4 2
3 1 1
1 3 2
0 1 -1
0 2 4
3 2 5
1 2 3
4 3 -3
*/
