class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> appearTwiceNum = new ArrayList<>(); 
        
        for (int i = 0;i < nums.length;i ++) {
        	int index;
        	if (nums[i] < -nums.length) {
        		index = Math.abs(nums[i] + nums.length * 3) - 1;
        	} else {
        		index = Math.abs(nums[i]) - 1;
        	}
        	
        	// use extra information to remember the "value"
        	if (nums[index] > 0) {  // this index does not remember any info
        		nums[index] = -nums[index];	
        	} else if (nums[index] < 0) {  // this index records that the value occurs once
        		nums[index] -= (nums.length * 3);
        	}
        	
        }
        
        for (int i = 0;i < nums.length;i ++) {
        	if (nums[i] < -nums.length) {
        		appearTwiceNum.add(i + 1);   // (index + 1) is the actual value
        	}
        }
		
        return appearTwiceNum;
    }
}
