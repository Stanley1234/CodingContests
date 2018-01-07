class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int cur = 0, prev = 0;
        int sum = 0;
        int minLen = 0;
        
        while(cur < nums.length) {
            sum += nums[cur];
            while(sum >= s) {
                minLen = (minLen == 0 ? cur - prev + 1 : Math.min(minLen, cur - prev + 1));
                sum -= nums[prev];
                prev ++;
            }
            cur ++;
        }
        return minLen;
    }
}
