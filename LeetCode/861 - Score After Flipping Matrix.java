class Solution {
    public int matrixScore(int[][] A) {
        // 1. mark the first column entries all as 1
        // 2. iterate through the rest column, flip each column so that there are more 1s as possible
        
        int r = A.length, c = A[0].length;
        int sum = 0;
        
        // always guarantee that the leftmost have as many 1s as possible
        for (int i = 0;i < r;i ++) {
            if (A[i][0] == 1) { continue; }
            for (int j = 0;j < c;j ++) {
                A[i][j] = (A[i][j] == 1 ? 0 : 1);  // flip the whole row
            }
        }
        sum += (1 << (c - 1)) * r;
        
        for (int j = 1;j < c;j ++) {  
            int numOfZeroes = 0;
            for (int i = 0;i < r;i ++) {
                if (A[i][j] == 0) { numOfZeroes ++; }
            }
            // can only flip each column
            sum += (1 << (c - j - 1)) * Math.max(numOfZeroes, r - numOfZeroes);
        }
        return sum;
    }
}
