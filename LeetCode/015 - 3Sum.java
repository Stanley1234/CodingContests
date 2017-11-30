class Solution {
    
    
    private int nextDistinctPos(int[] nums, int pos, int offset) {
        int comp = nums[pos];
        pos += offset;
        while(pos < nums.length &&  pos >= 0 && nums[pos] == comp)
            pos += offset;
        return pos;
        
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        
        Arrays.sort(nums);
        
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        int i = 0;
        while(i < nums.length) {
            
            int l = i + 1;
            int r = nums.length - 1;
            while(l < r) {
                if(nums[i] + nums[l] + nums[r] == 0) {
                    List<Integer> curAns = new ArrayList<>();
                    curAns.add(nums[i]);
                    curAns.add(nums[l]);
                    curAns.add(nums[r]);
                    ans.add(curAns);
                    
                    
                    l = nextDistinctPos(nums, l, 1);
                    r = nextDistinctPos(nums, r, -1);
                    
                    
                } else if(nums[i] + nums[l] + nums[r] < 0) {
                    l = nextDistinctPos(nums, l, 1);
                } else if(nums[i] + nums[l] + nums[r] > 0) {
                    r = nextDistinctPos(nums, r, -1);
                }
                
            }
            i = nextDistinctPos(nums, i, 1);
        }
        return ans;
        
    }
}
