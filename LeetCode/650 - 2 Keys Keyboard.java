class Solution {
    
    // A more general approach, for example, you can copy/paste a partial substring of the string
    // Time: O(n^3), Space: O(n^2)
    // dp[n, j] saves the minimum number of copy/paste for first n characters and can paste j characters now
    // dp[n, j] = min { dp[n - j, j] + 1, { dp[n - j, k] + 2 where k != j } }
    
    // Approach 2:
    // Time: O(n^2), Space: O(n)
    // dp[n] = min{ dp[i] + (n / i) } if n % i == 0
    
    
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2;i <= n;i ++) {
            dp[i] = i;
            for (int j = i - 1;j > 1;j --) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }
        return dp[n];
    }
}
