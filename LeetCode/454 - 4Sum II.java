class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
      HashMap<Integer, Integer> BCAchievableSumCounter = new HashMap<>();

      for (int bElem : B) {
         for (int cElem : C) {
            BCAchievableSumCounter.put(bElem + cElem,
               BCAchievableSumCounter.getOrDefault(bElem + cElem, 0) + 1);
         }
      }

      int totalCnt = 0;
      for (int aElem : A) {
         for (int dElem : D) {
            if (BCAchievableSumCounter.containsKey(- (aElem + dElem))) {
               totalCnt += BCAchievableSumCounter.get(- (aElem + dElem));
            }
         }
      }

      return totalCnt;
   }
}
