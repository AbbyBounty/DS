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
			// put weight into adj matrix for directed graph
			mat[src][dest] = weight;
		//	mat[dest][src] = weight;
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

	bool bellman_ford(int start) {
		// create dist array & init dist of all vertices to INF
		int i, j, dist[MAX];
		for (i = 0; i < vert_cnt; i++)
			dist[i] = INF;
		// and dist of start vertex as 0.
		dist[start] = 0;

		// update dist of dest vertex in each edge for V-1 times
		for (i = 1; i < vert_cnt; i++) {
			for (j = 0; j < edge_cnt; j++) {
				edge e = edges[j];
				// dist[dest] = dist[src] + weight(src,dest)
				if(dist[e.src] != INF && dist[e.dest] > dist[e.src] + e.weight)
					dist[e.dest] = dist[e.src] + e.weight;
			}
		}

		// check -ve weight cycle
		for (j = 0; j < edge_cnt; j++) {
			edge e = edges[j];
			// check if dist[dest] > dist[src] + weight(src,dest)
			if (dist[e.src] != INF && dist[e.dest] > dist[e.src] + e.weight)
				return true; // -ve weight cycle present
		}

		// print dist
		for (i = 0; i < vert_cnt; i++)
			cout << "distance of " << i << " from " << start << " = " << dist[i] << endl;

		return false;
	}
};

int main() {
	graph g(5);
	g.accept();
	g.display();
	bool result = g.bellman_ford(0);
	if (result == true) // -ve edge cycle
		cout << "graph contains -ve weight cycle" << endl;
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
