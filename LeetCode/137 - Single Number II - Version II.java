class Solution {
    public int singleNumber(int[] nums) {
        // see dicussion of this problem for further explanations
    	int x1 = 0, x2 = 0;
    	
    	for(int num : nums) {
    		x2 ^= (x1 & num);
    		x1 ^= num;
    		int mask = ~(x1 & x2);
    		x1 &= mask;
    		x2 &= mask;
    	}
    	return x1;
    }
}
