class Solution {
    
    private long neigh(int[] nums, int index) {
        if(index == -1 || index == nums.length)
            return Long.MIN_VALUE;
        return nums[index];
    }
    
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mid = 0;
        while(lo <= hi) {
        	mid = (lo + hi) / 2;
        	
            if(neigh(nums, mid - 1) < (long)nums[mid] && (long)nums[mid] > neigh(nums, mid + 1))
            	break;
            else if(neigh(nums, mid - 1) < (long)nums[mid])
            	lo = mid + 1;
            else
            	hi = mid - 1;
        }
        return mid;
    }
}
