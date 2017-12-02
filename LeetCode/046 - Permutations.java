class Solution {
    private boolean nextPermutation(int[] cur) {
		
		if(cur.length <= 1) 
			return false;
		
		
		int i = cur.length - 1;
		while(i > 0 && cur[i] <= cur[i - 1])
			i --;
		if(i == 0)  // all in descending form
			return false;
		
		// find the next larger number wrt cur[i]
		int k = cur.length - 1;
		while(cur[k] <= cur[i - 1])
			k --;
		
		// swap cur[i] and cur[k]
		int temp = cur[i - 1];
		cur[i - 1] = cur[k];
		cur[k] = temp;
		
		Arrays.sort(cur, i, cur.length);
		return true;
	}
	
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        
        int[] cur = nums;
        do {
        	List<Integer> curAns = new ArrayList<>();
        	for(int i = 0;i < cur.length;i ++)
        		curAns.add(cur[i]);
        	ans.add(curAns);
        } while(nextPermutation(cur));
        
        return ans;
    }
}
