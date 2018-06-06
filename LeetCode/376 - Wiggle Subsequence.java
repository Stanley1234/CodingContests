class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;  // by def, number of elements fewer than two are wiggle sequence

        for (int i = 0;i < nums.length - 1;i ++) {
            nums[i] = nums[i + 1] - nums[i];
        }

        int cur = 0;
        while (cur < nums.length - 1 && nums[cur] == 0) { cur ++; }
        if (cur == nums.length - 1) return 1;  // if an array only contains zero, then a single zero is a wiggle sequence

        // if an array contains a non-zero, then it must have 
        int len = 1;
        boolean sign;
        if (nums[cur] < 0) { sign = false; }
        else { sign = true; }

        while (cur < nums.length - 1) {
            if ((nums[cur] < 0) && sign || (nums[cur] > 0 && !sign)) {
                len ++;
                sign = !sign;
            }
            cur ++;
        }
        return len + 1;
    }
}
