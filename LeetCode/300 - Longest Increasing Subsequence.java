/*
O(nlogn)
*/

import java.util.Arrays;

public class Soln_300 {

    /*
    * This algorithm works because
    * 1. sequence stored in dp is ascending
    * 2. the length of sequence stored in dp is non-decreasing after each iteration
    * 3.
    *   1) if cur element is found, everything is ok
    *   2) is cur element is not found, but insertion is at the end,
    *      it increases the length
    *   3) if cur element is not found, but insertion is not at the end,
    *      it <b>replaces that element in dp with a smaller one</b>
    *
    * */

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) { return 0; }
        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int pos = Arrays.binarySearch(dp, 0, len, num);
            if (pos < 0) {
                pos = -(pos + 1); // get insertion point
            }
            if (pos == len) {
                len ++; // the insertion point == len iff all elements in dp are less than num
            }
            dp[pos] = num;
        }
        return len;
    }

    public static void main(String[] args) {
        Soln_300 soln_300 = new Soln_300();
        int[] num = {4, 1, 2, 6, 7, 8};
        System.out.print(soln_300.lengthOfLIS(num));
    }
}
