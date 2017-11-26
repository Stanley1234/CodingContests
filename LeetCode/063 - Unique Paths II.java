class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        
        int[][] dp = new int[m][n];
        
        for(int i = 0;i < m;i ++)
            Arrays.fill(dp[i], 0);
        
        if(obstacleGrid[0][0] == 0)
            dp[0][0] = 1;
        for(int i = 0;i < m;i ++) {
            for(int j = 0;j < n;j ++) {
                if(obstacleGrid[i][j] == 1)
                    continue;
                    
                if(i - 1 >= 0 && obstacleGrid[i - 1][j] == 0)
                    dp[i][j] += dp[i - 1][j];
                if(j - 1 >= 0 && obstacleGrid[i][j - 1] == 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        
        
        return dp[m - 1][n - 1];
    }
}
