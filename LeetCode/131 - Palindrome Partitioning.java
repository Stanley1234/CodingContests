class Solution {
    
    private List<List<String>> ans = new ArrayList<>();
    
    private boolean[][] markPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0;i < s.length();i ++)
            dp[i][i] = true;
        for(int i = 0;i < s.length() - 1;i ++)
            if(s.charAt(i) == s.charAt(i + 1))
                dp[i][i + 1] = true;
        for(int k = 3;k <= s.length();k ++) {
            for(int i = 0;i < s.length() - k + 1;i ++) {
                int j = i + k - 1;
                if(s.charAt(i) != s.charAt(j) || !dp[i + 1][j - 1])
                    continue;
                dp[i][j] = true;
            }
        }
        return dp;
    }
    
    private void findPartition(boolean[][] dp, int from, List<String> tmpAns, String s) {
        
        if(from >= dp.length) {
            ans.add(new ArrayList<>(tmpAns));
            return;
        }
        
        for(int to = from;to < dp.length;to ++) {
            if(dp[from][to]) {
                tmpAns.add(s.substring(from, to + 1));
                findPartition(dp, to + 1, tmpAns, s);
                tmpAns.remove(tmpAns.size() - 1);
            }
        }
    }
    
    public List<List<String>> partition(String s) {
        boolean[][] dp = markPalindrome(s);
        List<String> tmpAns = new ArrayList<>();
        findPartition(dp, 0, tmpAns, s);
        return ans;
    }
}
