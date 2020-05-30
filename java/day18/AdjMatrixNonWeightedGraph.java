
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
		boolean visited[] = new boolean[vert_cnt];
		// push start vertex on stack and mark it as visited
		s.push(start);
		visited[start] = true;
		// repeat until stack is empty
		while (!s.empty()) {
			// pop a vertex from stack and print it
			int trav = s.peek();	s.pop();
			System.out.print(trav +", ");
			// push all non visited neighbours of trav to the stack
			// and mark them as visited.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && visited[j] == false) {
					s.push(j);
					visited[j] = true;
				}
			}
		}
		System.out.println();
	}
	void bfs_trav(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[vert_cnt];
		// push start vertex on queue and mark it as visited
		q.offer(start);
		visited[start] = true;
		// repeat until queue is empty
		while (!q.isEmpty()) {
			// pop a vertex from queue and print it
			int trav = q.poll();
			System.out.print(trav +", ");
			// push all non visited neighbours of trav to the queue
			// and mark them as visited.
			for (int j = 0; j < vert_cnt; j++) {
				if (mat[trav][j] == 1 && visited[j] == false) {
					q.offer(j);
					visited[j] = true;
				}
			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		AdjMatrixNonWeightedGraph g = new AdjMatrixNonWeightedGraph(6);
		g.accept(sc);
		g.display();
		g.dfs_trav(0);
		g.bfs_trav(0);			
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



