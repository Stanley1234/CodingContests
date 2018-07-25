class Solution {
    
    private boolean allAreZeroes(int[] arr) {
        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        int[] charsRequired = new int[26];
        for (char ch : s1.toCharArray()) {
            charsRequired[ch - 'a'] ++;
        }
        
        int left = 0;
        int right = -1;
        while (true) {
            if (right - left + 1 == s1.length()) {
                if (allAreZeroes(charsRequired)) {
                    return true;
                }
                charsRequired[s2.charAt(left) - 'a'] ++;
                left ++;
            }
            right ++;
            if (right == s2.length()) {
                break;
            }
            charsRequired[s2.charAt(right) - 'a'] --;
        }
        return false;
    }
}
