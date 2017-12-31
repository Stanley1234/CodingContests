class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int cnt = 31;
        int res = 0;
        while(cnt >= 0) {
            if(((1 << cnt) & m) != ((1 << cnt) & n))
                break;
            res |= ((1 << cnt) & m);
            cnt --;
        }
        return res;
    }
}
