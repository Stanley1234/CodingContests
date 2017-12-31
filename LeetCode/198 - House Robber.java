class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        // dp[0] = nums[0]
        // dp[i] = max(dp[i - 1], max(dp[j]) + nums[i], nums[i]), 0 <= j <= i - 2
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        
        for(int i = 1;i < nums.length;i ++) {
            dp[i] = Math.max(dp[i - 1], nums[i]);
            for(int j = 0;j <= i - 2;j ++)
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
