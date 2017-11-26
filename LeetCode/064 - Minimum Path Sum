class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0)
            return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        
        int[][] dp = new int[m][n];
        
        for(int i = 0;i < m;i ++)
            Arrays.fill(dp[i], -1);
        
        
        dp[0][0] = grid[0][0];
        for(int i = 0;i < m;i ++) {
            for(int j = 0;j < n;j ++) {
                if(i - 1 >= 0)
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                if(j - 1 >= 0)
                    if(dp[i][j] == -1)
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        
        
        return dp[m - 1][n - 1];
    }
}
