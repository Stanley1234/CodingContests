class Solution {
    // dp[i][j] be the maximum length of square side that can be obtained with points (i, j) at bottom-right
    
    
    private int[][] constructMatrix(int r, int c) {
        int[][] arr = new int[r][];
        for (int i = 0;i < r;i ++) 
            arr[i] = new int[c];
        return arr;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)  return 0;
        
        int r = matrix.length, c = matrix[0].length;
        int[][] dp = constructMatrix(r, c);
        
        for (int i = 0;i < r;i ++) { dp[i][0] = (matrix[i][0] == '0' ? 0 : 1); }
        for (int j = 0;j < c;j ++) { dp[0][j] = (matrix[0][j] == '0' ? 0 : 1); }
        
        for (int i = 1;i < r;i ++) {
            for (int j = 1;j < c;j ++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0;i < r;i ++) {
            for (int j = 0;j < c;j ++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans * ans;
    }
}
