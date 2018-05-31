class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {       
        // pos : if the substring [0 ... pos] is made up from words in dict
        Set<Integer> validPos = new HashSet<>();
        for(int i = 0;i < s.length();i ++) {
            for(String word : wordDict) {          
                if((i == word.length() - 1 || 
                   validPos.contains(i - word.length())) && s.substring(i - word.length() + 1, i + 1).equals(word)) {                  	
                    validPos.add(i);
                    break;
                }
            }          	
        }
        return validPos.contains(s.length() - 1);
    }
}
