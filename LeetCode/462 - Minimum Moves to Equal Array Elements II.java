class Solution {
    public int minMoves2(int[] nums) {
        // Find an x such that |x - a1| + |x - a2| + ... + |x - an| is minized
        // x is the median
        Arrays.sort(nums);
        int goal = nums[nums.length / 2];
        int moves = 0;
        for (int n : nums) {
            moves += Math.abs(n - goal);
        }
        return moves;
    }
}
