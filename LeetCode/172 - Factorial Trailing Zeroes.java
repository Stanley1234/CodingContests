class Solution {
    // all trailing zeroes result from 2 * 5
    
    public int trailingZeroes(int n) {
        long t = 5;
        int sum = 0;
        while(t <= (long)n) {
            sum += (n / (int)t);  // n / 5 is number of integer 1 ... n that is a multiple of t
            t *= 5;
        }
        return sum;
    }
}
