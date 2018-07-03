class Solution {
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0;i < S.length();i ++) {
            lastIndex.put(S.charAt(i), i);  // if exists, override
        }
        
        List<Integer> blocksSize = new ArrayList<>();
        int rightBoundary = lastIndex.get(S.charAt(0));
        int curBlockSize = 0;
        
        for (int i = 0;i < S.length();i ++) {
            curBlockSize ++;
            if (lastIndex.get(S.charAt(i)) > rightBoundary) {
                rightBoundary = lastIndex.get(S.charAt(i));
            } 
            if (i == rightBoundary) {
                blocksSize.add(curBlockSize);
                curBlockSize = 0;
            } 
        }
        if (curBlockSize != 0) {
            blocksSize.add(curBlockSize);
        }
        return blocksSize;
    }
}
