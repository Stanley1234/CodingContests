// Time complexity: O(n^2 m)
// where n is the length of string and m is the longest length of string in wordDict

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        
        for(int i = 0;i < s.length();i ++) {
            dp[i] = false;
            for(int j = i;j >= 0;j --) {
                if(wordDict.contains(s.substring(j, i + 1))) {
                    if(j == 0 || dp[j - 1] == true) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }
}
