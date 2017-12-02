

class Solution {
    
	List<List<Integer>> ans;
	
	
	private void dfs(List<Integer> tmpAns, int leftValue, int lastIndex, int[] candidates) {
		if(leftValue == 0) {
			ans.add(new ArrayList<>(tmpAns));
			return;
		}
		
		for(int j = lastIndex;j >= 0;j --) {
			if(leftValue - candidates[j] < 0)
				continue;
			tmpAns.add(candidates[j]);
			dfs(tmpAns, leftValue - candidates[j], j, candidates);
			tmpAns.remove(tmpAns.size() - 1);
		}
	}
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	
    	ans = new ArrayList<>();
    	List<Integer> tmpAns = new ArrayList<>();
    	Arrays.sort(candidates);
    	dfs(tmpAns, target, candidates.length - 1, candidates);
    	
    	
        return ans;
    }
    
    
}
