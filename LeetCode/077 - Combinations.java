class Solution {
    List<List<Integer>> ans;
	
	void dfs(int n, int k, int lastValue, int curSize, List<Integer> tmpAns) {
		if(curSize == k) {
			ans.add(new ArrayList<>(tmpAns));
			return;
		}
		
		for(int i = lastValue + 1;i <= n;i ++) {
			tmpAns.add(i);
			dfs(n, k, i, curSize + 1, tmpAns);
			tmpAns.remove(tmpAns.size() - 1);
		}
	}
	
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        List<Integer> tmpAns = new ArrayList<>();
        dfs(n, k, 0, 0, tmpAns);
    	return ans;
    }
}
