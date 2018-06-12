class Solution {
    public int[] countBits(int num) {        
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1;i <= num;i ++) {
            if (i % 2 == 0) {
                dp[i] = dp[i & (i - 1)] + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }
}
