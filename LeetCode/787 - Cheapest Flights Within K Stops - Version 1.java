class Solution {
    private class Node {
        int index;
        int step;
        public Node(int index, int step) {
            this.index = index;
            this.step = step;
        }
    }

    private int[][] constructGraph(int[][] flights, int n) {
        int[][] graph = new int[n][n];
        for (int[] node : flights) {
            int u = node[0], v = node[1], w = node[2];
            graph[u][v] = w;
        }
        return graph;
    }


    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
        // construct graph
        int[][] graph = constructGraph(flights, N);
        // BFS
        int ans = Integer.MAX_VALUE;
        int[] dist = new int[N];
        Queue<Node> q = new LinkedList<>();


        Arrays.fill(dist, Integer.MAX_VALUE);
        q.offer(new Node(src, 0));

        dist[src] = 0;

        while (!q.isEmpty()) {
            Node u = q.poll();
            for (int v = 0;v < N;v ++) {
                if (graph[u.index][v] == 0) { continue; }  // not adjacent
                if (u.step + 1 > K && v != dst) { continue; }

                if (dist[u.index] + graph[u.index][v] >= ans) { continue; }  // prune
                if (dist[u.index] + graph[u.index][v] >= dist[v]) { continue; }  // prune

                dist[v] = dist[u.index] + graph[u.index][v];
                q.offer(new Node(v, u.step + 1));
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
