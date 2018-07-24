class Solution {
    public int minMoves(int[] nums) {
        int moves = 0;
        Arrays.sort(nums);
        for (int i = 1;i < nums.length;i ++) {
            // increasing n-1 by one is eqiv to decreasing 1 by one
            // imagine each number as mountain
            moves += (nums[i] - nums[0]);
        }
        return moves;
    }
}
