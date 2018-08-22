class Solution {
    public int subarraySum(int[] nums, int k) {
      Map<Integer, List<Integer>> prefixSumRecord = new HashMap<>();

      prefixSumRecord.put(0, new ArrayList<>());
      prefixSumRecord.get(0).add(-1);

      int sum = 0;
      for (int i = 0;i < nums.length;i ++) {
         sum += nums[i];
         if (!prefixSumRecord.containsKey(sum)) {
            prefixSumRecord.put(sum, new ArrayList<>());
         }
         prefixSumRecord.get(sum).add(i);
      }

      int totalCnt = 0;
      for (Map.Entry<Integer, List<Integer>> entry : prefixSumRecord.entrySet()) {
         if (prefixSumRecord.containsKey(entry.getKey() + k)) {

            for (int firstIndex : entry.getValue()) {
               for (int nextIndex : prefixSumRecord.get(entry.getKey() + k)) {
                  if (firstIndex >= nextIndex) {
                     continue;
                  }
                  totalCnt ++;
               }
            }

         }
      }

      return totalCnt;
   }
}
