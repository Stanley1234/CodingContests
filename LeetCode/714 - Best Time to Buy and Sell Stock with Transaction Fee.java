class Solution {
    
    // buy[i] is the max profit before i-th day and the last action ends with a 'buy'
    // sell[i] is the max profit before i-th and the last action ends with either a 'sell' or a 'noop'
    
    // sell[i] = max { sell[i - 1], buy[i - 1] + prices[i] - fee }
    // buy[i] = max { buy[i - 1], sell[i - 1] - prices[i] }
    
    public int maxProfit(int[] prices, int fee) {
        int[] sell = new int[prices.length];
        int[] buy = new int[prices.length];
        
        buy[0] = -prices[0];
        for (int i = 1;i < prices.length;i ++) {
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
        }
        return sell[prices.length - 1];
    }
}
