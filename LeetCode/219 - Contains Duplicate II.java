class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        for(int i = 0;i < nums.length;i ++) {
            if(i > k)
                visited.remove(nums[i - k - 1]);
            if(!visited.add(nums[i]))
                return true;
        }
        return false;
    }
}
