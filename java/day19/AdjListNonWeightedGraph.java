import java.util.*;

public class AdjListNonWeightedGraph {
	private LinkedList<Integer> []edges;
	private int vert_cnt;
	@SuppressWarnings("unchecked")
	public AdjListNonWeightedGraph(int v_cnt) {
		vert_cnt = v_cnt;
		edges = new LinkedList[v_cnt];
		for (int i = 0; i < v_cnt; i++)
			edges[i] = new LinkedList<>();
	}
	public void accept(Scanner sc) {
		int edge_cnt, from, to;
		System.out.print("enter number of edges: ");
		edge_cnt = sc.nextInt();
		System.out.print("enter edges from -> to: ");
		for (int i = 0; i < edge_cnt; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			edges[from].add(to);
			edges[to].add(from); // graph is undirected (all edges are like bi-direction).
		}
	}

	public void display() {
		for (int i = 0; i < edges.length; i++) {
			System.out.print(i +" = ");
			for (int v : edges[i])
				System.out.print(v +" -> ");
			System.out.println();
		}
	}

	public void dfs_trav(int start) {
		Stack<Integer> s = new Stack<>();
		boolean marked[] = new boolean[vert_cnt];

		s.push(start);
		marked[start] = true;
		while (!s.empty()) {
			int trav = s.pop();
			System.out.print(trav +", ");
			for(int v: edges[trav]) {
				if(!marked[v]) {
					s.push(v);
					marked[v] = true;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AdjListNonWeightedGraph g = new AdjListNonWeightedGraph(6);
		g.accept(sc);
		g.display();
		g.dfs_trav(0);
		sc.close();			
	}
}


/*
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5
*/








