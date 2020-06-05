
import java.util.Scanner;

public class BellmanFord {
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

        @Override
        public String toString() {
            return src + " -> " + dest + " ( " + weight + " ) ";
        }
    }

    public static final int INF = 999;
    private int mat[][];
    private int vert_cnt, edge_cnt;
    private Edge[] edges;

    public BellmanFord(int v_cnt) {
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

    public void accept(Scanner sc) {
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
        //    mat[dest][src] = weight;
            edges[i] = new Edge(src, dest, weight);
        }
    }

    void display() {
        char inf = '#'; // 236;
        for (int i = 0; i < vert_cnt; i++) {
            for (int j = 0; j < vert_cnt; j++) {
                if (mat[i][j] == INF)
                    System.out.print(inf + "\t");
                else
                    System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public boolean bellmanFord(int start) {
        int dist[] = new int[vert_cnt];
        // init dist of each vertex to INF & dist of start vertex to 0.
        for (int i = 0; i < dist.length; i++)
            dist[i] = INF;
        dist[start] = 0;
        // repeat V-1 times
        for (int i = 1; i < vert_cnt; i++) {
            for (Edge e : edges) {
                System.out.println(e);
                // update dist of dest vertex (if applicable)
                if (dist[e.src] != INF && (dist[e.src] + e.weight) < dist[e.dest])
                    dist[e.dest] = dist[e.src] + e.weight;
            }
        }
        // check if -ve cycle
        for (Edge e : edges) {
           if (dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
               return true; // -ve edge cycle present
        }
        // print dist
        for (int i = 0; i < dist.length; i++) 
            System.out.println("distance of " + i + " from " + start + " vertex = " + dist[i]);
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BellmanFord g = new BellmanFord(5);
        g.accept(sc);
        g.display();
        g.bellmanFord(0);
        sc.close();
    }
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
