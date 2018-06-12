class Solution {    
    private int A(int n, int k) {
        if (k == 0) { return 1; }
        return n * A(n - 1, k - 1);
    }
    
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) { n = 10; }
        
        int sum = 1;
        for (int i = 1;i <= n;i ++) {
            sum += A(10, i - 1) * (11 - i) - A(9, i - 1);
        }
        return sum;
    }
}
