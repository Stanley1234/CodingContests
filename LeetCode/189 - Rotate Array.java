class Solution {
    private void reverse(int[] nums, int s, int e) {
		for(int i = s;i <= (s + e) / 2;i ++) {
			int tmp = nums[i];
			nums[i] = nums[e - (i - s)];
			nums[e - (i - s)] = tmp;
		}
	}
	
    public void rotate(int[] nums, int k) {
        k %= nums.length;
    	reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, 0, nums.length - 1);
    }
}
