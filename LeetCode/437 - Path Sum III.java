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
    
    private int ans = 0;
    
    private void compute(TreeNode toNode, int target, int curSum) {
        if (toNode == null) {
            return;
        }
        
        curSum += toNode.val;
        if (curSum == target) {
            ans ++;
        }
        compute(toNode.left, target, curSum);
        compute(toNode.right, target, curSum);
    }
    
    private void fixRoot(TreeNode cur, int target) {
        if (cur == null) {
            return;
        }
        compute(cur, target, 0);
        fixRoot(cur.left, target);
        fixRoot(cur.right, target);
    }
    
    public int pathSum(TreeNode root, int sum) {
        fixRoot(root, sum);
        return ans;
    }
}
