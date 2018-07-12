/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {

    public int firstBadVersion(int n) {
        // all versions after a bad version is also bad
        int left = 1, right = n;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;   // trap, should not be (left + right) / 2
            if (isBadVersion(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
