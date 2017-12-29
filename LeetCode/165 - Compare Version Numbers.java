// cases may be overlooked:
// "1.0.0.0"  "1"  => 0

class Solution {
    public int compareVersion(String version1, String version2) {
        
    	
    	String[] s1 = version1.split("\\.");
    	String[] s2 = version2.split("\\.");
    	
    	int i = 0;
    	while(i < s1.length && i < s2.length) {
    		
    		if(Integer.parseInt(s1[i]) > Integer.parseInt(s2[i]))
    			return 1;
    		else if(Integer.parseInt(s1[i]) < Integer.parseInt(s2[i]))
    			return -1;
    		
    		i ++;
    	}
    	// at most one of the following while statements is to be executed  
    	while(i < s1.length && Integer.parseInt(s1[i]) == 0) 
    		i ++;
    	while(i < s2.length && Integer.parseInt(s2[i]) == 0) 
    		i ++;
    	if(i < s1.length)
    		return 1;
    	else if(i < s2.length)
    		return -1;
    	return 0;
    }
}
