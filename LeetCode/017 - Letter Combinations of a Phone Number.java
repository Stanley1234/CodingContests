class Solution {
    
    private static final String[] combs = new String[] {
        "    ","abc", "def", 
        "ghi", "jkl", "mno", 
        "pqrs","tuv", "wxyz"
    };
    

    
    public List<String> letterCombinations(String digits) {
    	List<String> ans = new ArrayList<String>();
    	
        int start = 0;
        while(start < digits.length() && digits.charAt(start) == '1') start ++;
        if(start == digits.length()) 
        	return ans;
        
        
        for(int j = 0;j < combs[digits.charAt(start) - '1'].length();j ++)
            ans.add(combs[digits.charAt(start) - '1'].charAt(j) + "");
        
        start ++;
        for(int i = start;i < digits.length();i ++) {
            
            // copy
            int oldSize = ans.size();
            for(int k = 0;k < combs[digits.charAt(i) - '1'].length() - 1;k ++) {
                for(int j = 0;j < oldSize;j ++) {
                    ans.add(ans.get(j));
                }    
            }
            
            
            // add to each 
            for(int k = 0;k < combs[digits.charAt(i) - '1'].length();k ++) {
                
                for(int j = oldSize * k;j < oldSize * (k + 1);j ++) {
                	
                	String tmp = ans.get(j);
                    tmp += combs[digits.charAt(i) - '1'].charAt(k);
                    ans.set(j, tmp);
                }
            }
            
            
        }
        return ans;
        
    }
    
    
}
