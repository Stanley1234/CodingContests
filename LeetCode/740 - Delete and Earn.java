class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) { return 0; }
        int[] dp = new int[nums.length + 1];
        int[][] nonDupe = new int[nums.length][2];
        
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0;i < nums.length;i ++) {
            if (i == 0) { 
                nonDupe[cnt][0] = nums[0]; 
                nonDupe[cnt][1] = 1;
            } else {
                if (nums[i] == nums[i - 1]) {
                    nonDupe[cnt][1] ++;
                } else {
                    cnt ++;
                    nonDupe[cnt][0] = nums[i];
                    nonDupe[cnt][1] = 1;
                }
            }
        }
        
        dp[1] = nonDupe[0][1] * nonDupe[0][0];
        for (int i = 1;i <= cnt;i ++) {
            dp[i + 1] = dp[i];
            if (nonDupe[i][0] != nonDupe[i - 1][0] + 1) {
                dp[i + 1] = dp[i] + nonDupe[i][1] * nonDupe[i][0];
            } else {
                dp[i + 1] = Math.max(dp[i + 1], nonDupe[i][1] * nonDupe[i][0] + dp[i - 1]);
            }
        }
        return dp[cnt + 1];
    }
}
