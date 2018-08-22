class Solution {

   public int findMaxLength(int[] nums) {
      Map<Integer, Integer> nearestPrefixSumPos = new HashMap<>();
      Map<Integer, Integer> furthestPrefixSumPos = new HashMap<>();

      int sum = 0;
      nearestPrefixSumPos.put(0, -1);
      for (int i = 0;i < nums.length;i ++) {
         if (nums[i] == 0) {
            sum += -1;
         } else {
            sum += 1;
         }
         if (!nearestPrefixSumPos.containsKey(sum)) {
            nearestPrefixSumPos.put(sum, i);
         }
         furthestPrefixSumPos.put(sum, i);
      }

      int longestDist = 0;
      for (Map.Entry<Integer, Integer> entry : furthestPrefixSumPos.entrySet()) {
         int prefixSum = entry.getKey();
         if (nearestPrefixSumPos.containsKey(prefixSum)) {
            longestDist = Math.max(longestDist, furthestPrefixSumPos.get(prefixSum) - nearestPrefixSumPos.get(prefixSum));
         }
      }
      return longestDist;
   }

} 
