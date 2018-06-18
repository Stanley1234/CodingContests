class Solution {
    public int[] nextGreaterElements(int[] nums) {
        // for a certain number x in nums, it has Next Greater Element
        // iff there exists a number y > x in nums
        int[] ans = new int[nums.length];
        for (int i = 0;i < nums.length;i ++) {
            int j = (i + 1) % nums.length;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    break;
                }
                j ++;
                if (j >= nums.length) { 
                    j = 0; 
                }
            }
            if (i == j) {
                ans[i] = -1;
            } else {
                ans[i] = nums[j];
            }
        }
        return ans;
    }
}
