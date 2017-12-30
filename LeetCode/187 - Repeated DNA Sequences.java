class Solution {
    private int convertToBin(char ch) {
		if(ch == 'A') 
			return 0b001;
		else if(ch == 'C')
			return 0b010;
		else if(ch == 'G')
			return 0b011;
		return 0b100;
	}
	
    public List<String> findRepeatedDnaSequences(String s) {
        
    	Set<Integer> words = new HashSet<>();
    	Set<Integer> addedWords = new HashSet<>();
    	List<String> res = new ArrayList<String>();
    	
    	for(int i = 0;i <= s.length() - 10;i ++) {
    		int hash = 0;
    		for(int j = 0;j < 10;j ++) {
    			hash |= convertToBin(s.charAt(i + j));
    			if(j < 9)
    				hash <<= 3;
    		}
    		if(!words.add(hash) && addedWords.add(hash))
    			res.add(s.substring(i, i + 10));
    	}
    	return res;
    }
}

 
