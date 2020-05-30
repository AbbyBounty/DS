
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// adj matrix implementation of non-weighted graph

public class AdjMatrixNonWeightedGraph {
	private int mat[][];
	private int vert_cnt;
	public AdjMatrixNonWeightedGraph(int v_cnt) {
		vert_cnt = v_cnt;
		mat = new int[vert_cnt][];
		for (int i = 0; i < vert_cnt; i++) {
			mat[i] = new int[vert_cnt];
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = 0;
			}
		}
	}
	void accept(Scanner sc) {
		System.out.println("enter " +vert_cnt +" x " +vert_cnt +" graph adj matrix: ");
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
	}
	void display() {
		for (int i = 0; i < vert_cnt; i++) {
			for (int j = 0; j < vert_cnt; j++) {
				System.out.print(mat[i][j] +"\t");
			}
			System.out.println();
		}
	}
	void dfs_trav(int start) {
		Stack<Integer> s = new Stack<>();
		boolean marked[] = new boolean[vert_cnt];
		// push start vertex on stack and mark it
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack and print it
			int trav = s.peek();	s.pop();
			System.out.print(trav +", ");
			// push all non marked neighbours of trav to the stack
			// and mark them.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
				}
			}
		}
		System.out.println();
	}

	public boolean is_connected() {
		int start = 0;
		Stack<Integer> s = new Stack<>();
		boolean marked[] = new boolean[vert_cnt];
		// push start vertex on stack and mark it. also count num of vertices pushed on stack
		int cnt = 1;
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.isEmpty()) {
			// pop a vertex from stack
			int trav = s.pop();
			// push all non marked neighbours of trav to the stack
			// and mark them and also count them
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
					cnt++;
					// when all vertices are visited, it is connected graph
					if (cnt == vert_cnt)
						return true;
				}
			}
		}
		return false; // all vertices not visited
	}

	void dfs_spanning_tree(int start) {
		Stack<Integer> s = new Stack<>();
		boolean marked[] = new boolean[vert_cnt];
		// push start vertex on stack and mark it
		s.push(start);
		marked[start] = true;
		// repeat until stack is empty
		while (!s.isEmpty()) {
			// pop a vertex from stack
			int trav = s.pop();
			// push all non marked neighbours of trav to the stack
			// and mark them as marked and also print the connecting edges
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					s.push(j);
					marked[j] = true;
					System.out.println(trav + " -> " + j);
				}
			}
		}
	}

	void bfs_trav(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean marked[] = new boolean[vert_cnt];
		// push start vertex on queue and mark it
		q.offer(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.isEmpty()) {
			// pop a vertex from queue and print it
			int trav = q.poll();
			System.out.print(trav +", ");
			// push all non marked neighbours of trav to the queue
			// and mark them.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.offer(j);
					marked[j] = true;
				}
			}
		}
		System.out.println();
	}

	public void bfs_spanning_tree(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean marked[] = new boolean[vert_cnt];
		// push start vertex on queue and mark it
		q.offer(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.isEmpty()) {
			// pop a vertex from queue
			int trav = q.poll();
			// push all non marked neighbours of trav to the queue
			// and mark them and also print connecting edge
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.offer(j);
					marked[j] = true;
					System.out.println(trav + " -> " + j);
				}
			}
		}
	}

	public void bfs_path_length(int start) {
		Queue<Integer> q = new LinkedList<>();
		int dist[] = new int[vert_cnt];
		boolean marked[] = new boolean[vert_cnt];
		// dist of starting vertex is always 0
		dist[start] = 0;
		// push start vertex on queue and mark it
		q.offer(start);
		marked[start] = true;
		// repeat until queue is empty
		while (!q.isEmpty()) {
			// pop a vertex from queue
			int trav = q.poll();
			// push all non marked neighbours of trav to the queue
			// and mark them and also calc dist of the neighbour
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && marked[j] == false) {
					q.offer(j);
					marked[j] = true;
					// dist of neighbour = trav vertex dist + 1 edge
					dist[j] = dist[trav] + 1;
				}
			}
		}
		// print distance array
		System.out.println("distance of all vertices from vertex " + start);
		for (int i = 0; i < vert_cnt; i++)
			System.out.println("vertex " + i + " -> " + dist[i]);
	}


	public boolean is_bipartite() {
		// 0=no color, 1=yellow, -1=green
		Queue<Integer> q = new LinkedList<>();
		int start = 0;
		// all vertices are initially not colored
		int color[] = new int[vert_cnt];
		// push start vertex on queue and mark it with a color
		q.offer(start);
		color[start] = 1;
		// repeat until queue is empty
		while (!q.isEmpty()) {
			// pop a vertex from queue
			int trav = q.poll();
			// push all non colored neighbours of trav to the queue
			// and mark them with opposite color.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && color[j] == 0) {
					q.offer(j);
					color[j] = -1 * color[trav];
				}
				else if (mat[trav][j] == 1 && color[j] == color[trav])
					return false;
			}
		}
		// all nodes are checked & are divided into two different colors - so it is bipartite
		return true;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		AdjMatrixNonWeightedGraph g = new AdjMatrixNonWeightedGraph(6);
		g.accept(sc);
		g.display();
		g.dfs_trav(0);
		g.bfs_trav(0);			
		System.out.println("connected graph: " + g.is_connected());
		System.out.println("dfs spanning tree: ");
		g.dfs_spanning_tree(0);
		System.out.println("bfs spanning tree: ");
		g.bfs_spanning_tree(0);
		g.bfs_path_length(0);
		System.out.println("bipartite graph: " + g.is_bipartite());
	
		sc.close();
	}
}

/*

0 1 1 1 0 0
1 0 1 0 1 0
1 1 0 0 0 0
1 0 0 0 1 1
0 1 0 1 0 0
0 0 0 1 0 0

*/



