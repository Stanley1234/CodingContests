public String fractionToDecimal(long numerator, long denominator) {
    	StringBuilder res = new StringBuilder();
    	
    	if((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) 
    		res.append("-");
    	numerator = Math.abs(numerator);
		denominator = Math.abs(denominator);
    	res.append(numerator / denominator);
    	
    	numerator %= denominator;
        if(numerator == 0)
        	return res.toString();
        res.append(".");
        
        int cnt = res.length();
        
        Map<Integer, Integer> usedRemainder = new HashMap<>();
        usedRemainder.put((int)numerator, cnt);
        cnt ++;
        
        while(true) {
        	numerator *= 10;
        	
        	int remainder = (int) (numerator % denominator);
        	int quotient = (int) (numerator / denominator);
        	if(remainder == 0) {
        		res.append(quotient);
        		break;
        	}
        	if(usedRemainder.containsKey(remainder)) {
        		if(remainder != 0) {
        			res.insert(usedRemainder.get(remainder), "(");
        			res.append(quotient);
        			res.append(")");
        		}
        		break;
        	}
        	res.append(quotient);
        	usedRemainder.put(remainder, cnt);
        	numerator %= denominator;
        	cnt ++;
        }
        return res.toString();
    }
