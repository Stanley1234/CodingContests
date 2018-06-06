class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) { return false; }
        if (k == 0) { 
            for (int i = 0;i < nums.length;i ++) 
                if (nums[i] != 0) 
                    return false;
            return true;
        } 
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(0, -1);
        
        int sum = 0;
        for (int i = 0;i < nums.length;i ++) {
            sum = ((sum % k) + (nums[i] % k)) % k;  
            if (visited.containsKey(sum)) {
                int index = visited.get(sum);
                if (i - index > 1) return true;
            } else {
                visited.put(sum, i);
            }
        }
        return false;
    }
}
