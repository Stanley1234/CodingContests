class Solution {
    // DFS + Memorization
    private Map<List<Integer>, Integer> visited = new HashMap<>();
    
    private int buyNonSpecial(List<Integer> price, List<Integer> needs) {
        int cost = 0;
        for (int i = 0;i < needs.size();i ++) {
            cost += price.get(i) * needs.get(i);
        }
        return cost;
    } 
    
    /*
        cost[n0, n1, n2, ...] refers to minimum cost to buy n0 A, n1 B, n2 C
        The dp function is 
            cost[n0, n1, n2, ...] 
                = min{ 
                        buyNonSpecial(n0, n1, ...), 
                        min { special_cost[i] + cost[n0 - special_offer[0], n1 - special_offer[n1], ...] }
                     }
        Trick 1: use memorization  31 ms -> 23 ms
        
        Trick 2: modify def of cost[n0, n1, n2, ...]
                 now cost[n0, n1, n2, ...] refers to minimum cost to buy n0 A, n1 B, n2 C under condition that
                 only from special i and onward can be taken
        Problem: can memorization be applied to Trick 2? 
                 If it applies, if under condition B visited the needs whose minimum cost is calculated under
                 condition A and memorized, then it may cause a mistake
    */
    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (visited.containsKey(needs)) { return visited.get(needs); }
        
        int minCost = buyNonSpecial(price, needs);
        // buy special
        for (int i = 0;i < special.size();i ++) {
            List<Integer> remainingNeeds = new ArrayList<>(needs);
            List<Integer> specialOfferDetails = special.get(i);
            int specialOfferCost = specialOfferDetails.get(specialOfferDetails.size() - 1);

            // if selecting the current plan
            boolean doable = true;
            for (int j = 0;j < specialOfferDetails.size() - 1;j ++) {
                if (needs.get(j) < specialOfferDetails.get(j)) {
                    doable = false;
                    break;
                }
                remainingNeeds.set(j, needs.get(j) - specialOfferDetails.get(j));
            }
            if (!doable) { continue; }
            minCost = Math.min(minCost, specialOfferCost + helper(price, special, remainingNeeds));
        }
        visited.put(needs, minCost);
        return minCost;
    }
    
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs);
    }
}
