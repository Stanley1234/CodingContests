class Solution {
    public int[] searchRange(int[] nums, int target) {
     
        int[] ans = new int[2];
        ans[0] = findFirst(nums, target);
        ans[1] = findSecond(nums, target);
        
        
        
        return ans;
    }
    
    private int findFirst(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(target == nums[mid]) {
                
                if(mid > 0 && nums[mid - 1] == nums[mid]) {
                    r = mid - 1;
                } else {
                    return mid;
                }
                
            } else if(target < nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    private int findSecond(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(target == nums[mid]) {
                
                if(mid < nums.length - 1 && nums[mid + 1] == nums[mid]) {
                    l = mid + 1;
                } else {
                    return mid;
                }
                
            } else if(target < nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    
}
