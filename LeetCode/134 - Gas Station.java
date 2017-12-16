class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	int n = gas.length;
        if(n == 0) 
            return 0;
        int curSum = gas[0] - cost[0];
        int minSum = gas[0] - cost[0];
        int startIndex = 1;
        for(int i = 1;i < n;i ++) {
            curSum += (gas[i] - cost[i]);
            if(curSum < minSum) {
                minSum = curSum;
                startIndex = i + 1;
            }
        }
        if(curSum < 0) 
        	return -1;
        return startIndex % n;
    }
}
