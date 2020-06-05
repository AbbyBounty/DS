
import java.util.Arrays;
import java.util.Scanner;

// adj matrix implementation of non-weighted graph


public class KruskalMST {
    class Edge {
        int src, dest, weight;
        public Edge() {
            this(0, 0, 0);
        }
        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public static final int INF = 999;
	private int mat[][];
    private int vert_cnt, edge_cnt;
    private Edge[] edges;

	public KruskalMST(int v_cnt) {
		vert_cnt = v_cnt;
		mat = new int[vert_cnt][];
		for (int i = 0; i < vert_cnt; i++) {
			mat[i] = new int[vert_cnt];
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = INF;
			}
        }
        edges = new Edge[0];
    }
    
	void accept(Scanner sc) {
		System.out.print("Enter number of edges: ");
        edge_cnt = sc.nextInt();
        edges = new Edge[edge_cnt];
		int src, dest, weight;
		System.out.println("enter src dest weight for " + edge_cnt + " edges");
		for (int i = 0; i < edge_cnt; i++) {
			src = sc.nextInt();
			dest = sc.nextInt();
			weight = sc.nextInt();
			mat[src][dest] = weight;
            mat[dest][src] = weight;
            edges[i] = new Edge(src, dest, weight);
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

    public int findParent(int v, int parent[]) {
        while(parent[v] != -1)
            v = parent[v];
        return v;
    }

    public void unionSets(int root1, int root2, int parent[]) {
        parent[root1] = root2;
    }

    public boolean containsCycle(Edge edges[], int edge_cnt) {
        int parent[] = new int[vert_cnt];
        for(int i=0; i<vert_cnt; i++)
            parent[i] = -1;
        for (int i=0; i < edge_cnt; i++) {
            Edge e = edges[i];
            int src_root = findParent(e.src, parent);
            int dest_root = findParent(e.dest, parent);
            if(src_root == dest_root)
                return true;
            unionSets(src_root, dest_root, parent);
        }
        return false;
    }

    public void kruskal() {
        // sort edges in asc order by weight
        Arrays.sort(edges, (e1,e2) -> e1.weight - e2.weight);
        
        Edge mst[] = new Edge[vert_cnt-1];
        int mst_edge_cnt = 0, i = 0;
        // add each edge into mst, until V-1 edges are done
        while(mst_edge_cnt < vert_cnt-1) {
            mst[mst_edge_cnt] = edges[i++];
            mst_edge_cnt++;
            // if forming cycle, discard the edge from mst    
            if(containsCycle(mst, mst_edge_cnt))
                mst_edge_cnt--;
        }

        // print mst
        int mst_wt = 0;
        for (Edge e : mst) {
            System.out.println(e.src + " -> " + e.dest + " (" + e.weight + ")");
            mst_wt += e.weight;
        }
        System.out.println("MST weight: " + mst_wt);
    }

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		KruskalMST g = new KruskalMST(9);
		g.accept(sc);
        g.display();
        g.kruskal();
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


