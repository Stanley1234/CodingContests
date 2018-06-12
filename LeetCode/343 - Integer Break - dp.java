class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2;i <= n;i ++) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1;j <= i;j ++) {
                if (i == n && j == i) { break; }
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[n];
    }
}
