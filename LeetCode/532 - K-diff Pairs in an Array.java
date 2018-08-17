class Solution {
    public int findPairs(int[] nums, int k) {
      int left = 0, right = 1;
      int cnt = 0;

      Arrays.sort(nums);

      while (left < nums.length && right < nums.length) {
         if (nums[right] - nums[left] == k) {
            cnt ++;
            left ++;
            right ++;
         } else if (nums[right] - nums[left] < k) {
            right ++;
         } else {
            left ++;
         }

         while (left < nums.length && left > 0 && nums[left] == nums[left - 1]) {
            left ++;
         }
         while (right < nums.length && right > 0 && nums[right] == nums[right - 1]) {
            right ++;
         }
         if (left == right) {
            right ++;
         }
      }

      return cnt;
   }
}
