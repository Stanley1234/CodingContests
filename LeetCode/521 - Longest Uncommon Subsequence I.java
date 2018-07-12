class Solution {
    
    // If a is not a subsequence of b, then clearly comparing a against yields a.length()
    // If a is indeed a subsequence of b, then any subsequence of a is also a subsequence of b, thus -1
    
    private int compareUncommonSeq(String src, String cmp) {
        int i = 0, j = 0;
        while (i < src.length() && j < cmp.length()) {
            if (cmp.charAt(j) == src.charAt(i)) {
                i ++;
            }
            j ++;
        }
        return i == src.length() ? -1 : src.length();
    }
    
    public int findLUSlength(String a, String b) {
        return Math.max(compareUncommonSeq(a, b), compareUncommonSeq(b, a));
    }
}
