
import java.util.Scanner;

public class WarshallFloyd {
    public static final int INF = 999;
    private int mat[][];
    private int vert_cnt, edge_cnt;

    public WarshallFloyd(int v_cnt) {
        vert_cnt = v_cnt;
        mat = new int[vert_cnt][];
        for (int i = 0; i < vert_cnt; i++) {
            mat[i] = new int[vert_cnt];
            for (int j = 0; j < vert_cnt; j++) {
                mat[i][j] = INF;
            }
        }
    }

    public void accept(Scanner sc) {
        System.out.print("Enter number of edges: ");
        edge_cnt = sc.nextInt();
        int src, dest, weight;
        System.out.println("enter src dest weight for " + edge_cnt + " edges");
        for (int i = 0; i < edge_cnt; i++) {
            src = sc.nextInt();
            dest = sc.nextInt();
            weight = sc.nextInt();
            mat[src][dest] = weight;
            // mat[dest][src] = weight;
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

    public void warshallFloyd() {
        // create distance matrix and init it.
        int[][] dist = new int[vert_cnt][];
        for (int i = 0; i < vert_cnt; i++) {
            dist[i] = new int[vert_cnt];
            for (int j = 0; j < vert_cnt; j++)
                dist[i][j] = mat[i][j];
            // dist of a vertex from itself is zero
            dist[i][i] = 0;
        }

        // try intermediate (i) between each pair of start (s) and end (e).
        for (int i = 0; i < vert_cnt; i++) {
            for (int s = 0; s < vert_cnt; s++) {
                for (int e = 0; e < vert_cnt; e++) {
                    if(dist[s][i] != INF && dist[i][e] != INF && dist[s][i] + dist[i][e] < dist[s][e])
                        dist[s][e] = dist[s][i] + dist[i][e];
                }
            }
        }

        // print distance matrix
        System.out.println("\nDistance matrix: ");
        char inf = '#'; // 236;
        for (int i = 0; i < vert_cnt; i++) {
            for (int j = 0; j < vert_cnt; j++) {
                if (dist[i][j] == INF)
                    System.out.print(inf + "\t");
                else
                    System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WarshallFloyd g = new WarshallFloyd(5);
        g.accept(sc);
        g.display();
        g.warshallFloyd();
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
