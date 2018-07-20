class Solution {
    
    private boolean isAPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i ++;
            j --;
        }
        return true;
    }
    
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        // AAAAA BDCDBD  AAAAA
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                break;    
            }
            i ++;
            j --;
        }
        if (i >= j) { 
            return true; 
        }
        return isAPalindrome(s, i + 1, j) || isAPalindrome(s, i, j - 1);
    }
}
