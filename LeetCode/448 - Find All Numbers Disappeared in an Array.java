class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> disappearedNum = new ArrayList<>();
        
        // O(1) extra space for processing  
        // O(n) time complexity
        
        // For example, if nums[1] = 3, then we make use of index 2 to remember that 
        // the number 3 has appeared. 
        
        for (int i = 0;i < nums.length;i ++) {
        	int value = Math.abs(nums[i]) - 1;
        	if (nums[value] > 0) {
        		nums[value] = -nums[value];
        	}
        }
        
        for (int i = 0;i < nums.length;i ++) {
        	if (nums[i] > 0) {
        		disappearedNum.add(i + 1);
        	}
        }
		
        return disappearedNum;
    }
}
