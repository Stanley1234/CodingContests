class Solution {
    private char coeffToChar(int coeff) {
		if(coeff == 0) 
			return 'Z';
		return (char) (coeff - 1 + 'A');
	}
	
    public String convertToTitle(int n) {
    	// n = M[n] * 26^n + M[n - 1] * 26^(n - 1) + ... + M[0] * 26^0  (1 <= M[i] <= 26 for sufficient n)
    	// extra: note that in this set, n can never be 0
    	
    	StringBuilder res = new StringBuilder();
    	while(n > 0) {
    		int coeff = n % 26;  // 0 <= coeff <= 25 but coeff == 0 iff M[i] == 26
    		res.append(coeffToChar(coeff));
    		
    		if(coeff != 0)
    			n /= 26;
    		else
    			n = n / 26 - 1;
    	}
    	
    	return res.reverse().toString();
    }
}
