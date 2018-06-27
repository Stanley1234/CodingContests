class Solution {
    
    private class State {
        public int r;
        public int c;
        public int k;   // memorization depends on how many steps left
        public State(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof State) {
                State os = (State)o;
                return r == os.r && c == os.c && k == os.k;
            }
            return false;
        }
        
        @Override 
        public int hashCode() {
            return k * 2500 + r * 50 + c;
        }
    }
    
    private static final int[] R_OFFSET = {-1, 0, 0, 1};
    private static final int[] C_OFFSET = {0, 1, -1, 0};
    private static final int mod = 1000000007;
    
    private int dfs(State curState, HashMap<State, Integer> visited, int m, int n) {
        if (curState.r < 0 || curState.c < 0 || curState.r >= m || curState.c >= n) {   // out of bounds
            return 1;
        }
        if (curState.k == 0) { 
            return 0; 
        }
        if (visited.containsKey(curState)) {
            return visited.get(curState);
        }
        int ans = 0;
        for (int i = 0;i < 4;i ++) {
            State next = new State(curState.r + R_OFFSET[i], curState.c + C_OFFSET[i], curState.k - 1);
            ans = (ans % mod + dfs(next, visited, m, n) % mod) % mod;
        }
        visited.put(curState, ans);
        return ans;
    }
    
    public int findPaths(int m, int n, int N, int i, int j) {
        HashMap<State, Integer> visited = new HashMap<>();
        return dfs(new State(i, j, N), visited, m, n);
    }
}
