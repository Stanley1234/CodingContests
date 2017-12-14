class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) 
            return 0;
        int[] dp = new int[triangle.size()];
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for(int i = 0;i < lastRow.size();i ++) {
            dp[i] = lastRow.get(i);
        }
        int rowIndex = lastRow.size() - 1;
        while(--rowIndex >= 0) {
            int[] newDp = new int[rowIndex + 1];
            List<Integer> curRow = triangle.get(rowIndex);
            for(int i = 0;i < curRow.size();i ++)
                newDp[i] = curRow.get(i) + Math.min(dp[i], dp[i + 1]);
            dp = newDp;
        }
        return dp[0];
    }
}
