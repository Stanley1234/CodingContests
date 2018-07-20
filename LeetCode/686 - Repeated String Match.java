class Solution {
    
    private String repeat(String str, int n) {
        StringBuilder sb = new StringBuilder();
        while (n -- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    /**
    abc
    bcabcabca
    
    repeat 3 times => abcabcabc
    */
    
    public int repeatedStringMatch(String A, String B) {
        int repeatTimes = 0;
        if (A.length() >= B.length()) {
            repeatTimes = 1;
        } else {
           if (B.length() % A.length() == 0) {
               repeatTimes = B.length() / A.length();
           } else {
               repeatTimes = B.length() / A.length() + 1;
           }
        }
        if (repeat(A, repeatTimes).contains(B)) {
            return repeatTimes;
        } else if (repeat(A, repeatTimes + 1).contains(B)) {
            return repeatTimes + 1;
        } else {
            return -1;
        }
    }
}
