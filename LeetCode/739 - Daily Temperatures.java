class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
      int days = temperatures.length;
      int[] indices = new int[days];

      for (int i = days - 2;i >= 0;i --) {
         if (temperatures[i] < temperatures[i + 1]) {
            indices[i] = i + 1;
         } else {

            int nextIndex = indices[i + 1];
            while (nextIndex != 0 && temperatures[nextIndex] <= temperatures[i]) {
               nextIndex = indices[nextIndex];
            }
            indices[i] = nextIndex;
         }
      }

      for (int i = 0;i < days;i ++) {
         if (indices[i] == 0) {
            continue;
         }
         indices[i] = indices[i] - i;
      }

      return indices;
   }
}
