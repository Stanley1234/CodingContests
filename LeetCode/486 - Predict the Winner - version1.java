class Solution {
    // DFS(brutal)

    private boolean helper(int[] nums, int left, int right, int p1, int p2, boolean player) {
        if (left > right) { 
            if (p1 >= p2) { return true; }
            else { return false; }
        }
        boolean res1, res2;
        if (player) {
            // player 1 
            res1 = helper(nums, left + 1, right, p1 + nums[left], p2, !player);
            res2 = helper(nums, left, right - 1, p1 + nums[right], p2, !player);
        } else {
            // player 2
            res1 = helper(nums, left + 1, right, p1, p2 + nums[left], !player);
            res2 = helper(nums, left, right - 1, p1, p2 + nums[right], !player);
        }
        if (player) {
            // for player 1, he wants to choose the strategy that he will definitely win at this step
            return res1 || res2;
        } else {
            // for player 2, if one strategy allows him to win, then player 1 loses
            // if there is a 'false', then player 1 loses from this step
            return res1 && res2;
        }
    }
    
    public boolean PredictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length - 1, 0, 0, true);
    }
}   
