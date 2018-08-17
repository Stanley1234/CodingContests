public int[] findErrorNums(int[] nums) {
      int dup = 0, missing = 0;

      for (int i = 0;i < nums.length;i ++) {
         int curNum = Math.abs(nums[i]);

         if (nums[curNum - 1] < 0) {
            dup = curNum;
         }
         nums[curNum - 1] = -nums[curNum - 1];
         missing = missing ^ (i + 1) ^ curNum;
      }
      
      missing ^= dup;
      return new int[] {dup, missing};
   }
