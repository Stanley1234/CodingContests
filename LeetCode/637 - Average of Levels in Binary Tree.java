/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private class Counter {
        private HashMap<Integer, Integer> levelCnt = new HashMap<>();
        private HashMap<Integer, Long> levelSum = new HashMap<>(); 
        private int maxLevel;
        
        public void addVal(int level, int newVal) {
            if (!levelCnt.containsKey(level)) {
                levelCnt.put(level, 1);
                levelSum.put(level, (long)newVal);   // autoboxing int -> long -> Long
                                                     // int cannot be autoboxed to Long
            } else {
                levelCnt.put(level, levelCnt.get(level) + 1);
                levelSum.put(level, levelSum.get(level) + (long)newVal);  // autoboxing long -> Long
                                                                          // java has no implicit type conversion rule like c
                                                                          // newVal will not be elevated to long automatically
                        
            }
            maxLevel = Math.max(maxLevel, level);
        }
        
        public int getMaxLevel() {
            return maxLevel;
        }
        
        public double getAvg(int level) {
            return (double)levelSum.get(level) / levelCnt.get(level);
        }
    }
    
    private Counter counter = new Counter();
    
    private void traverse(TreeNode cur, int level) {
        if (cur == null) {
            return;
        }
        counter.addVal(level, cur.val);
        traverse(cur.left, level + 1);
        traverse(cur.right, level + 1);
    }
    
    private List<Double> createAns() {
        int maxLevel = counter.getMaxLevel();
        List<Double> ans = new ArrayList<>(maxLevel);
        
        // initialize array list
        for (int i = 0;i <= maxLevel;i ++) {
            ans.add(counter.getAvg(i));
        }
        return ans;
    }
    
    
    public List<Double> averageOfLevels(TreeNode root) {
        traverse(root, 0);
        return createAns();
    }
}
