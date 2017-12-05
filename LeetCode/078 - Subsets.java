class Solution {
    List<List<Integer>> ans;
	
	
	private void dfs(int[] nums, int lastIndex, 
					 int curDepth, int maxDepth,
					 List<Integer> tmpAns) {
		if(curDepth == maxDepth) {
			ans.add(new ArrayList<>(tmpAns));
			return;
		}
		for(int i = lastIndex + 1;i < nums.length;i ++) {
			
			tmpAns.add(nums[i]);
			dfs(nums, i, curDepth + 1, maxDepth, tmpAns);
			tmpAns.remove(tmpAns.size() - 1);
			
		}
	}
	
    public List<List<Integer>> subsets(int[] nums) {
    	ans = new ArrayList<>();
    	List<Integer> tmpAns = new ArrayList<>();
    	
    	for(int i = 0;i <= nums.length;i ++) 
    		dfs(nums, -1, 0, i, tmpAns);
        return ans;
    }
}
