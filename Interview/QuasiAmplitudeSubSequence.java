// [1 2 3 4 5 6]
// [1 2 4] has amplitude 3 because 4 - 1 = 3
// [1 2 6] has amplitude 5 because 6 - 1 = 5
// A subsequence is of quasi-amplitude if the difference between maximum and minimum is equal to one
// Find the longest subsequence of quasi-amplitude

// Time complexity: O(nlogn)
// Space complexity: O(n) (beyond the storage of A)

class Solution {
  // given that A is nonempty 
	public int solution(int[] A) {  		
	    Arrays.sort(A);
	    
	    int maxLen = 0;
	    int curLen = 0;
	    int fLen = 0;
	    
	    long[] B = new long[A.length];  // avoid overflow arithmetics
	    for(int i = 0;i < A.length;i ++)
	    	B[i] = A[i];
	    
	    long f = B[0];
	    for(int i = 0;i < A.length;i ++) {
	    	if(B[i] == f) {
	    		curLen ++;
	    		fLen ++;
	    	} else if(B[i] == f + 1) {
	    		curLen ++;
	    	} else {
	    		if(B[i] - B[i - 1] > 1) {
	    			curLen = 0;
	    			f = B[i];
	    		} else {
	    			curLen -= fLen;
	    			fLen = curLen;
	    			f = B[i - 1];
	    		}
	    		i --;
	    	}
	    	maxLen = Math.max(maxLen, curLen);
	    }
	   
		return maxLen;
	}
}
