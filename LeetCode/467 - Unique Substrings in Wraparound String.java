class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) return 0;

        int[] maxLen = new int[26];

        int curLen = 0;
        for (int i = 0;i < p.length();i ++) {
            int index = p.charAt(i) - 'a';
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25)) {
                curLen ++;
            } else {
                curLen = 1;
            }
            maxLen[index] = Math.max(maxLen[index], curLen);
        }

        int sum = 0;
        for (int i = 0;i < 26;i ++) {
            sum += maxLen[i];
        }
        return sum;
    }
}
