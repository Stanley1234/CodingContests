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
    
    private int ans;
    
    private int helper(TreeNode cur) {
        // invariant: cur is never null
        if (cur.left == null && cur.right == null) {   // leaf node
            return 1;
        } 
        
        int leftNum = (cur.left == null ? 0 : helper(cur.left));
        int rightNum = (cur.right == null ? 0 : helper(cur.right));
        int totalNum = 1;
        
        if (cur.left != null && cur.left.val == cur.val) {
            totalNum += leftNum;
            leftNum ++;
        } else {
            leftNum = 1;
        }
        if (cur.right != null && cur.right.val == cur.val) {
            totalNum += rightNum;
            rightNum ++;
        } else {
            rightNum = 1;
        }
        ans = Math.max(ans, totalNum - 1);
        return Math.max(leftNum, rightNum);
    }
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return ans;
    }
}
