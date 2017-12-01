import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    
    
    private int nextDistinct(int[] nums, int start, int offset) {
        int cmp = nums[start];
        start += offset;
        while(start >= 0 && start < nums.length && nums[start] == cmp)
        	start += offset;
        return start;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        
        
        for(int i = 0;i < nums.length - 3;i = nextDistinct(nums, i, 1)) {
            for(int j = i + 1;j < nums.length - 2;j = nextDistinct(nums, j, 1)) {
                
                int l = j + 1;
                int r = nums.length - 1;
                
                while(l < r) {
                    if(nums[i] + nums[j] + nums[l] + nums[r] == target) {
                        List<Integer> curAns = new ArrayList<>();
                        curAns.add(nums[i]);
                        curAns.add(nums[j]);
                        curAns.add(nums[l]);
                        curAns.add(nums[r]);
                        ans.add(curAns);
                        
                        l = nextDistinct(nums, l, 1);
                        r = nextDistinct(nums, r, -1);
                    } else if(nums[i] + nums[j] + nums[l] + nums[r] > target) {
                    	r = nextDistinct(nums, r, -1);
                    } else {
                    	l = nextDistinct(nums, l, 1);
                    }
                    
                    
                }
            }
        }
        
        return ans;
    }
}
