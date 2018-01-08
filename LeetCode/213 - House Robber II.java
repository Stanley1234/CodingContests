// The optimal answer either includes the first house or not
// thus explore this problem in two different cases

class Solution {
    
    // first house is being robbed
    private int round1(int[] nums) {
        int n = nums.length - 1;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = dp[0];
        for(int i = 2;i < n;i ++) 
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        dp[n] = dp[n - 1]; // last house cannot be robbed
        return dp[n];
    }
    
    private int round2(int[] nums) {
        int n = nums.length - 1;
        int[] dp = new int[nums.length];
        dp[0] = 0;
        dp[1] = nums[1];
        for(int i = 2;i <= n;i ++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        return dp[n];
    }
    
    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        else if(nums.length == 1)
            return nums[0];
        
        return Math.max(round1(nums), round2(nums));
    }
}
