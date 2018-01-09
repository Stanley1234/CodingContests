class Solution {
    
    private List<List<Integer>> res;
    
    private void dfs(int curSum, int targetSum, 
                     int remainNo, int lastInt,
                     List<Integer> tmp) {
        if(remainNo == 0) {
            if(curSum == targetSum)
                res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = lastInt + 1;i <= 9;i ++) {
            if(curSum + i > targetSum)
                break;
            tmp.add(i);
            dfs(curSum + i, targetSum, remainNo - 1, i, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(0, n, k, 0, tmp);
        return res;
    }
}
