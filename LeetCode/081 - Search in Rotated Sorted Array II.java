class Solution {
    // best case O(logn), worst case O(n)
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
           int mid = (l + r) / 2;
           
           if(target == nums[mid])
        	   return true;
           if(nums[mid] < nums[r]) {
        	   if(nums[mid] <= target && target <= nums[r])
        		   l = mid + 1;
        	   else 
        		   r = mid - 1;
           } else if(nums[mid] > nums[r]) {
        	   if(target >= nums[0] && target < nums[mid])
        		   r = mid - 1;
        	   else
        		   l = mid + 1;	   
           } else 
        	     r --;            
        }
        return false;
    }
}
