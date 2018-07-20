class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int i = 0, j = 0;
        int ans = 0;
        while (i < houses.length) {
            int curRadius = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && curRadius >= Math.abs(houses[i] - heaters[j + 1])) {  // must be ">="
                j ++;
                curRadius = Math.abs(houses[i] - heaters[j]);
            }
            ans = Math.max(ans, curRadius);
            i ++;
        }
        return ans;

        // 1 x x x 5 6 x x x x
        //   y           y
        // must be ">=", consider the following data:
        // [1522,1522, 1589]
        // [1522,1522, 1589]
    }
}
