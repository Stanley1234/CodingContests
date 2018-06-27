import javafx.util.Pair;

import java.util.*;

public class Soln_808 {
    private class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V val) {
            this.key = key;
            this.value = val;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }
    private class Node {
        public int u;
        public int v;
        public double prob;

        public Node(int u, int v, double prob) {
            this.u = u;
            this.v = v;
            this.prob = prob;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                return this.u == ((Node) obj).u && (this.v == ((Node) obj).v);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return u * 10000 + v;
        }
    }

    private static final int[] A = {100, 75, 50, 25};
    private static final int[] B = {0, 25, 50, 75};

    private HashMap<Node, Pair<Double, Double>> visited = new HashMap<>();

    private Pair<Double, Double> dfs(Node cur) {
        if (cur.u == 0) {
            if (cur.v != 0) {
                return new Pair<>(cur.prob, 0.0);
            } else {
                return new Pair<>(0.0, cur.prob);
            }
        }
        if (cur.v == 0) {
            return new Pair<>(0.0, 0.0);
        }
        if (visited.containsKey(cur)) {
            return visited.get(cur);
        }

        double a1 = 0.0, a2 = 0.0;
        for (int i = 0;i < 4;i ++) {
            int nextA = cur.u - A[i] >= 0 ? cur.u - A[i] : 0;
            int nextB = cur.v - B[i] >= 0 ? cur.v - B[i] : 0;

            Pair<Double, Double> result = dfs(new Node(nextA, nextB, cur.prob * 0.25));
            a1 += result.getKey();
            a2 += result.getValue();
        }
        Pair<Double, Double> ret = new Pair<>(a1, a2);
        visited.put(cur, ret);
        return ret;
    }

    public double soupServings(int N) {
        Pair<Double, Double> result = dfs(new Node(N, N, 1));
        return result.getKey() + 0.5 * result.getValue();
    }

    public static void main(String[] args) {
        Soln_808 soln_808 = new Soln_808();
        System.out.print(soln_808.soupServings(800));
    }
}
