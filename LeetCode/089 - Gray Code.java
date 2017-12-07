class Solution {
    public List<Integer> grayCode(int n) {
        /*
         * n = 4
         * 0000
         * 0001
         * 0011
         * 0010
         * 0110
         * ...
         * */
    	
    	Set<Integer> visited = new HashSet<>();
    	List<Integer> ans = new ArrayList<>();
    	ans.add(0);
    	visited.add(0);
    	for(int i = 1;i < (1 << n);i ++) {
    		int lastVal = ans.get(i - 1);
    		
    		for(int j = 0;j < n;j ++) {
    			// toggle between 1 and 0
    			if(((lastVal & (1 << j)) == (1 << j)) && !visited.contains(lastVal - (1 << j))) {
    				ans.add(lastVal - (1 << j));
    				visited.add(lastVal - (1 << j));
    				break;
    			} else if(((lastVal & (1 << j)) == 0) && !visited.contains(lastVal + (1 << j))) {
    				ans.add(lastVal + (1 << j));
    				visited.add(lastVal + (1 << j));
    				break;
    			}	
    		}
    	}
    	return ans;
    }
}
