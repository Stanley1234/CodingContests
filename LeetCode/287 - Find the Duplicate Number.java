class Solution {
    public int findDuplicate(int[] nums) {
        // cycle detection
        // the number at each position tells which index to go at next step
        // nums[0] = 3  represents the index 0 is linked with index 3
        // [3, 1, 3, 4, 2] =>
        // (0) -> (3) -> (4) -> (2) -> (3)    (1)

        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[fast];
            fast = nums[fast];
        } while (slow != fast);
        
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
