// a knapsack problem
// but the knapsack has two types of restrictions to consider


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0) { return 0; }
        
        int[][] cost = new int[strs.length][2];
        int[][] dp = new int[m + 1][n + 1];
        
        for (int k = 0;k < strs.length;k ++) {
            int u = 0, v = 0;
            for (int i = 0;i < strs[k].length();i ++) {
                if (strs[k].charAt(i) == '0') {
                    u ++;
                } else {
                    v ++;
                }
            }
            cost[k][0] = u;  // number of 0s
            cost[k][1] = v;  // number of 1s
        }
        
        for (int i = 0;i < strs.length;i ++) {
            for (int u = m;u >= cost[i][0];u --) {
                for (int v = n;v >= cost[i][1];v --) {
                    dp[u][v] = Math.max(dp[u][v], dp[u - cost[i][0]][v - cost[i][1]] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
