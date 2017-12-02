class Solution {
    public int search(int[] nums, int target) {  
        int l = 0, r = nums.length - 1;
        int mid = 0;
        while(l <= r) {
            mid = (l + r) / 2;
            
            // target and nums[mid] are on the same side
            if((nums[0] <= target) != (nums[0] <= nums[mid])) {
                if(target >= nums[0]) {
                    nums[mid] = Integer.MAX_VALUE;
                } else if(target < nums[0]){
                    nums[mid] = Integer.MIN_VALUE;
                }
            }
            if(target == nums[mid])
                return mid;
            else if(target > nums[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }
    
    
}
