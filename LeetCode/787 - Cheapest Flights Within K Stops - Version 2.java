import java.util.*;

public class Soln_787 {
    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
        // DP
        int[][] dist = new int[N][K + 2];  // dist[i][j] saves the distance from src to i with <= j stops(plus arrive at i)
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        dist[src][0] = 0;
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            if (u == src && v == dst) { dist[dst][0] = w; }
        }

        /**
         * Why this works?
         *
         * Notice for a iteration k, since all edges are iterated to at this iteration,
         * the nodes that can be reached from src within k stops can definitely be calculated
         *
         * For a node v with k stops from src, it depends on a node u with k - 1 stops from src.
         * In terms of the entrie graph, now dist[u] may not be optimal, but it must store
         * the shortest distance within k - 1 stops or k stop
         *
         * Regardless of u being updated prior to v in this iteration, dist[v] must save the shortest distance
         * within k stops
         *
         *
         */

        for (int k = 1;k <= K + 1;k ++) {
            dist[src][k] = 0;
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];

                if (dist[u][k - 1] == Integer.MAX_VALUE) { continue; }
                dist[v][k] = Math.min(dist[v][k], dist[u][k - 1] + w);
            }

            for (int i = 0;i < N;i ++) {
                System.out.println(i + ":" + dist[i][k]);
            }
            System.out.println();
        }
        return dist[dst][K + 1] == Integer.MAX_VALUE ? -1 : dist[dst][K + 1];
    }

    public static void main(String[] args) {
        Soln_787 soln_787 = new Soln_787();
        int[][] flights = {
                {0, 1, 1000},
                {0, 2, 10},
                {2, 1, 40},
                {1, 3, 40}

        };
        System.out.print(soln_787.findCheapestPrice(4, flights, 0, 3, 1));
    }
}
