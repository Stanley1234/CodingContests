class Solution {
    List<String> ans = new ArrayList<>();
    
    private boolean[] mark(String s, List<String> wordDict) {
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
        return dp;
    }
    
    private void search(boolean[] dp, List<String> wordDict,
    					int curIndex, 
    					String original, String split) {
    	
    	int len = original.length();
    	
        if(curIndex >= len) {
            ans.add(new String(split));
            return;
        }
        for(int i = curIndex;i < len;i ++) {
            if(!dp[i] || !wordDict.contains(original.substring(curIndex, i + 1))) continue;
            String newSplit = split;
            if(newSplit != "")   newSplit += " ";
            newSplit += original.substring(curIndex, i + 1);
            search(dp, wordDict, i + 1, original, newSplit);
        }
    }
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        boolean[] dp = mark(s, wordDict);
        if(dp[s.length() - 1]) {
        	String tmpAns = "";
            search(dp, wordDict, 0, s, tmpAns);
        }
        
        return ans;
    }
   
}
