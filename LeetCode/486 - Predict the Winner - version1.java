class Solution {
    // DFS(brutal)

    private boolean helper(int[] nums, int left, int right, int p1, int p2, boolean player) {
        if (left > right) { 
            if (p1 >= p2) { return true; }
            else { return false; }
        }
        if (player) {
            // for player 1, he wants to choose the strategy that he will definitely win at this step
            return helper(nums, left + 1, right, p1 + nums[left], p2, !player) 
                || helper(nums, left, right - 1, p1 + nums[right], p2, !player);
        } else {
            // for player 2, if one strategy allows him to win, then player 1 loses
            // if there is a 'false', then player 1 loses from this step
            return helper(nums, left + 1, right, p1, p2 + nums[left], !player)
                && helper(nums, left, right - 1, p1, p2 + nums[right], !player);
        }
    }
    
    public boolean PredictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length - 1, 0, 0, true);
    }
}   
