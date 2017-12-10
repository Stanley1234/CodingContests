class Solution {
    public int numTrees(int n) {
    	// G(n) represents the number of unique BSTs consisting of 1 ... n
    	// F(n, i) represents the number of unique BSTs with root i consisting of 1...n
    	// G(n) = F(n, 1) + F(n, 2) + F(n, 3) + ... + F(n, n)
    	// F(n, i) = G(i - 1) * G(n - i)
    	// Thus G(n) = G(0) * G(n - 1) + G(1) * G(n - 2) + ... + G(n - 1) * G(0)
    	int[] G = new int[n + 1];
    	G[0] = G[1] = 1;
    	for(int i = 2;i <= n;i ++) {
    		G[i] = 0;
    		for(int j = 0;j <= i - 1;j ++) {
    			G[i] += G[j] * G[i - 1 - j];  
    		}
    	}
    	return G[n];
    }
}
