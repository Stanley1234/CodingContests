class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())
            return false;
        
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> mapped = new HashSet<>();
        
        for(int i = 0;i < s.length();i ++) {
        	
        	// if the rule already exists
        	if(mapping.containsKey(s.charAt(i))) {
        		if(mapping.get(s.charAt(i)) != t.charAt(i))
        			return false;
        		else
        			continue;
        	}
        	// if the rule does not exist 
            if(mapped.contains(t.charAt(i)))  // no two characters can map to the same characters
				return false;
            mapping.put(s.charAt(i), t.charAt(i));
            mapped.add(t.charAt(i));
        }
        return true;
    }
}
