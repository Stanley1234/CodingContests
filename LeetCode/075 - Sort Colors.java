class Solution {
    
    
    
    public void sortColors(int[] nums) {
        int lp = 0, rp = nums.length - 1;
        int index = 0;
        while(index < nums.length && index <= rp) {
            if(nums[index] == 0) {
            	if(lp < index) {
            		nums[index] = nums[lp];
            		nums[lp] = 0;
            		index --;
            	} 
            	lp ++;
            } else if(nums[index] == 2) {           	
            	nums[index] = nums[rp];
                nums[rp --] = 2;
                index --;
            } 
            index ++;
        }
    }
}
