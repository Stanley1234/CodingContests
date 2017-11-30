class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int closest = nums[0] + nums[1] + nums[2];
        int i = 0;
        while(i < nums.length) {
            
            int l = i + 1;
            int r = nums.length - 1;
            while(l < r) {
                
                if(nums[i] + nums[l] + nums[r] == target) {
                    return target;
                } else {
                    if(Math.abs(nums[i] + nums[l] + nums[r] - target) < Math.abs(closest - target))
                        closest = nums[i] + nums[l] + nums[r];
                    
                    if(nums[i] + nums[l] + nums[r] < target)
                        l ++;
                    else
                        r --;   
                }
            }
            i ++;
            
        }
        return closest;
    }
}
