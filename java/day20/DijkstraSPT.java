
import java.util.Scanner;

// adj matrix implementation of non-weighted graph

public class DijkstraSPT {
	public static final int INF = 999;
	private int mat[][];
	private int vert_cnt, edge_cnt;
	public DijkstraSPT(int v_cnt) {
		vert_cnt = v_cnt;
		mat = new int[vert_cnt][];
		for (int i = 0; i < vert_cnt; i++) {
			mat[i] = new int[vert_cnt];
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = INF;
			}
		}
	}
	void accept(Scanner sc) {
		System.out.print("Enter number of edges: ");
		edge_cnt = sc.nextInt();
		int src, dest, weight;
		System.out.println("enter src dest weight for " + edge_cnt + " edges");
		for (int i = 0; i < edge_cnt; i++) {
			src = sc.nextInt();
			dest = sc.nextInt();
			weight = sc.nextInt();
			mat[src][dest] = weight;
			mat[dest][src] = weight;
		}
	}
	void display() {
		char inf = '#'; //236;
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				if(mat[i][j]==INF)
					System.out.print(inf +"\t");
				else
					System.out.print(mat[i][j] +"\t");
			}
			System.out.println();
		}
	}

	int get_min(int dist[], boolean spt[], int vert_cnt) {
		int i, min_dist = INF, min_vert = 0;
		for (i = 0; i < vert_cnt; i++) {
			if (spt[i] == false && dist[i] < min_dist) {
				min_dist = dist[i];
				min_vert = i;
			}
		}
		return min_vert;
	}
	
	void dikstra(int start) {
		// create sptset to keep track of vertices in spt. initially all are false
		boolean spt[] = new boolean[vert_cnt];
		// assign dist to each vertex as infintiy. for start vertex it is 0.
		int cur, i, j, dist[] = new int[vert_cnt];
		for (i = 0; i < vert_cnt; i++)
			dist[i] = INF;
		dist[start] = 0;
		// keep track of parent of each vertex. initially parent of all is -1.
		int parent[] = new int[vert_cnt];
		for (i = 0; i < vert_cnt; i++)
			parent[i] = -1;

		// repeat until all vertices are added into spt
		for (i = 0; i < vert_cnt; i++) {
			// find min dist vertex, which is not added in spt
			cur = get_min(dist, spt, vert_cnt);
			// add that vertex into spt
			spt[cur] = true;
			// update dist & parent of all neighbours (which are not yet in spt & their dist is more than the sum of cur vert dist & weight of connecting edge)
			for (j = 0; j < vert_cnt; j++) {
				if (mat[cur][j] != INF && spt[j] == false && dist[j] > (dist[cur] + mat[cur][j])) {
					dist[j] = dist[cur] + mat[cur][j];
					parent[j] = cur;
				}
			}
		}

		// print shortest path tree (parent's array)
		System.out.println("spt with vertex " + start);
		for (i = 0; i < vert_cnt; i++)
			System.out.println(i + " -> " + parent[i]);
 		
		// print single src dist.
		System.out.println("\ndist to all vertices from vertex " + start);
		for (i = 0; i < vert_cnt; i++)
			System.out.println(i + " -> " + dist[i]);
	}
	 
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		DijkstraSPT g = new DijkstraSPT(9);
		g.accept(sc);
		g.display();
		g.dikstra(0);
		sc.close();
	}
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



