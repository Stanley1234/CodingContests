class Solution {
    List<List<Integer>> ans = new ArrayList<>();
	
	private int nextNonDupeIndex(int[] nums, int curIndex) {
		int cmp = nums[curIndex];
		int i;
		for(i = curIndex + 1;i < nums.length && cmp == nums[i];i ++);
		return i;
	}
	
	private void dfs(int[] nums,
					 int curDepth, int maxDepth,
					 int firstIndex, List<Integer> tmpAns) {
		
		if(curDepth == maxDepth) {
			ans.add(new ArrayList<>(tmpAns));
			return;
		}
		
		for(int i = firstIndex;i < nums.length;i = nextNonDupeIndex(nums, i)) {
			tmpAns.add(nums[i]);
			dfs(nums, curDepth + 1, maxDepth, i + 1, tmpAns);
			tmpAns.remove(tmpAns.size() - 1);
		}
		
	}
	
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
    	for(int i = 0;i <= nums.length;i ++) {
    		List<Integer> tmpAns = new ArrayList<>();
    		dfs(nums, 0, i, 0, tmpAns);
    	}
    	
    	return ans;
    }
}
