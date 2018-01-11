class Solution {
     public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0 || k <= 0)  // absolute difference cannot be smaller than 0
        	return false;
    	
    	Map<Long, Long> buckets = new HashMap<>();
    	for(int i = 0;i < nums.length;i ++) {
    		long reIndex = (long)nums[i] - Integer.MIN_VALUE;
    		long bucketId = reIndex / ((long)t + 1);
    		
    		if(buckets.containsKey(bucketId)
    				|| (buckets.containsKey(bucketId - 1) && reIndex - buckets.get(bucketId - 1) <= t)
    				|| (buckets.containsKey(bucketId + 1) && buckets.get(bucketId + 1) - reIndex <= t)) 
    			return true;
    		if(buckets.size() >= k) {
    			long prevReindex = (long)nums[i - k] - Integer.MIN_VALUE;
    			buckets.remove(prevReindex / ((long)t + 1));
    		}
    		buckets.put(bucketId, reIndex);
    	}
    	return false;
    }
}
