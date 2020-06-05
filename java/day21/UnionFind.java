
import java.util.Scanner;

// adj matrix implementation of non-weighted graph

public class UnionFind {
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

	public UnionFind(int v_cnt) {
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

    public boolean containsCycle() {
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
	 
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		UnionFind g = new UnionFind(9);
		g.accept(sc);
        g.display();
        boolean cycle = g.containsCycle();
        System.out.println("Cycle present: " + cycle);
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


