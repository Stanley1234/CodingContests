class Solution {
    private boolean isLegalEncoded(String s, int begin, int end) {
		if(begin == end && s.charAt(begin) != '0')
			return true;
		if(begin == end - 1 && Integer.parseInt(s.substring(begin, end + 1)) <= 26 
				&& Integer.parseInt(s.substring(begin, end + 1)) >= 10)
			return true;
		return false;
	}
	
    public int numDecodings(String s) {
    	if(s.length() == 0)
    		return 0;
    	
        int[] dp = new int[s.length()];
        
        if(isLegalEncoded(s, 0, 0))
        	dp[0] = 1;
        else
        	dp[0] = 0;
        for(int i = 1;i < s.length();i ++) {
    		dp[i] = 0;
    		if(isLegalEncoded(s, i, i))
    			dp[i] += dp[i - 1];
    		if(isLegalEncoded(s, i - 1, i))
    			dp[i] += (i >= 2 ? dp[i - 2] : 1);
    	
        }
        return dp[s.length() - 1];
    }
    
}
