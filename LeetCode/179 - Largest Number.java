class Solution {
    private boolean isZero(String res) {
		for(int i = 0;i < res.length();i ++)
			if(res.charAt(i) != '0')
				return false;
	
		return true;
	}
	
    public String largestNumber(int[] nums) {
        String[] cache = new String[nums.length];
        for(int i = 0;i < nums.length;i ++) 
        	cache[i] = Integer.toString(nums[i]);
        Arrays.sort(cache, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o1 + o2).compareTo(o2 + o1);
			}
        });
    	String res = "";
    	for(int i = cache.length - 1;i >= 0;i --) 
    		res += cache[i];
        
    	if(isZero(res))
    		return "0";
        return res;
    }
}
