
// adj matrix implementation of weighted graph

import java.util.Scanner;

public class PrimsMST {
	public static final int INF = 999;
	private int mat[][];
	private int vert_cnt, edge_cnt;
	public PrimsMST(int v_cnt) {
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

	public int get_min(int key[], boolean mst[], int vert_cnt) {
		int i, min_key = INF, min_vert = 0;
		for (i = 0; i < vert_cnt; i++) {
			if (mst[i] == false && key[i] <= min_key) {
				min_key = key[i];
				min_vert = i;
			}
		}
		return min_vert;
	}

	public void prim(int start) {
		// create mstset to keep track of vertices in mst. initially all are false
		boolean mst[] = new boolean[vert_cnt];
		// assign key to each vertex as infintiy. for start vertex it is 0.
		int cur, i, j, key[] = new int[vert_cnt];
		for (i = 0; i < vert_cnt; i++)
			key[i] = INF;
		key[start] = 0;
		// keep track of parent of each vertex. initially parent of all is -1.
		int parent[] = new int[vert_cnt];
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
			System.out.println(i + " -> " + parent[i]);
	 }
	 
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		PrimsMST g = new PrimsMST(9);
		g.accept(sc);
		g.display();
		g.prim(0);
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



