//  This algorithm works because the problem guarantess that 
//  only one number appears once

class Solution {
    public int singleNumber(int[] nums) {
        
    	int result = 0;
    	int[] count = new int[32];
    	
    	for(int i = 0;i < 32;i ++) {  // bits
    		for(int j = 0;j < nums.length;j ++) { // each element in an array
    			if(((nums[j] >> i) & 1) == 1)
    				count[i] ++;
    		}
    		result |= ((count[i] % 3) << i);
    	}
    	return result;
    }
}
