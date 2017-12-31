public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int cnt = 0;
        int res = 0;
        while(cnt < 32) {
            int bit = (n & (1 << cnt)) >>> cnt;
            res = res | (bit << (31 - cnt));
            cnt ++;
        }
        return res;
    }
}
