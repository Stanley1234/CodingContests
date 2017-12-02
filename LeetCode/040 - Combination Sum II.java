
class Solution {
    
	List<List<Integer>> ans;
	
	
	private void dfs(List<Integer> tmpAns, int leftValue, int lastIndex, int[] candidates) {
		
		//System.out.println(tmpAns);
		if(leftValue == 0) {
			ans.add(new ArrayList<>(tmpAns));
			return;
		}
		
		int j = lastIndex;
		while(j >= 0) {
			
			int nextJ = j - 1;
			while(nextJ >= 0 && candidates[nextJ] == candidates[j])
				nextJ --;
			
			
			if(leftValue - candidates[j] >= 0) {
				tmpAns.add(candidates[j]);
				dfs(tmpAns, leftValue - candidates[j], j - 1, candidates);
				tmpAns.remove(tmpAns.size() - 1);
			}
				
			
			j = nextJ;
			//j --;
		}
	}
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	
    	ans = new ArrayList<>();
    	List<Integer> tmpAns = new ArrayList<>();
    	Arrays.sort(candidates);
    	dfs(tmpAns, target, candidates.length - 1, candidates);
    	
    	
        return ans;
    }

}
