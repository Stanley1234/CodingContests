class Solution {
    // sell[i] means max profit of the sequence ending with a 'sell' before i-th day if there exists a buy before 
    // or max profix of the sequence without any operation

    // buy[i] means max profit of the sequence ending with a 'buy' before i-th day if there exists a buy before
    
    // sell[i] = max { sell[i - 1],  buy[i - 1] + price[i] }
    // buy[i] = max { buy[i - 1], sell[i - 2] - price[i] }
    
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) { return 0; }
        
        int[] sell = new int[prices.length];
        int[] buy = new int[prices.length];
        
        buy[0] = -prices[0];
        buy[1] = Math.max(buy[0], -prices[1]);
        for (int i = 1;i < prices.length;i ++) {
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            if (i >= 2) { buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]); }
        }
        return Math.max(sell[prices.length - 1], buy[prices.length - 1]);
    }
}
