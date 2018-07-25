class Solution {
    
    private boolean allAreZeroes(int[] count) {
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        
        if (p.length() > s.length()) {
            return ans;
        }
        
        int[] charsRequired = new int[26];
        for (char ch : p.toCharArray()) {
            charsRequired[ch - 'a'] ++;
        }
        
        int left = 0;
        int right = -1;
        while (true) {
            if (right - left + 1 == p.length()) {
                if (allAreZeroes(charsRequired)) {
                    ans.add(left);
                }
                charsRequired[s.charAt(left) - 'a'] ++;
                left ++;
            }
            right ++;
            if (right == s.length()) {
                break;
            }
            charsRequired[s.charAt(right) - 'a'] --; 
            
        }
        
        return ans;
    }
}
