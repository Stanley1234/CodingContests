// Very easy to make mistakes
// "aa" "aa"  => true 
// ""  "" => false
// We could swap characters of the former, but not the latter.

class Solution {
    
    
    private class CharCounter extends HashMap<Character, Integer> {
        public Integer add(Character key) {
            return containsKey(key) ? super.put(key, get(key) + 1) : super.put(key, 1);
        }
    }
    
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) { 
            return false; 
        }
        int differByCount = 0;
        int swapIndex0 = 0, swapIndex1 = 0;
        CharCounter charCounter = new CharCounter();
        
        for (int i = 0;i < A.length();i ++) {
            charCounter.add(A.charAt(i));
            if (A.charAt(i) == B.charAt(i)) {
                continue;
            }
            differByCount ++;
            if (differByCount == 1) {
                swapIndex0 = i;
            } else if (differByCount == 2) {
                swapIndex1 = i;
            }
        }
        if (differByCount == 0) {
            for (Map.Entry<Character, Integer> entry : charCounter.entrySet()) {
                if (entry.getValue() >= 2) {
                    return true;
                }
            } 
            return false;
        } else if (differByCount == 2) {
            return A.charAt(swapIndex0) == B.charAt(swapIndex1) && A.charAt(swapIndex1) == B.charAt(swapIndex0);
        } 
        return false;
    }
}
