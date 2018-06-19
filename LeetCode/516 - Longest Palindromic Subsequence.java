class Solution {
    public int longestPalindromeSubseq(String s) {
        // dp[i][j] saves the length of the longest palindromic subsequence
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0;i < s.length();i ++) {
            dp[i][i] = 1;
        }
        for (int i = 0;i < s.length() - 1;i ++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 2;
            } else {
                dp[i][i + 1] = 1;
            }
        }
        for (int k = 3;k <= s.length();k ++) {
            for (int i = 0;i <= s.length() - k;i ++) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;   // dp[i + 1][j - 1] + 2 > dp[i + 1][j] or dp[i][j - 1]
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
