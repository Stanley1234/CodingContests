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
    
    private boolean check(TreeNode node, long greater, long less) {  
        if(node == null) return true;
        if(!(node.val > greater && node.val < less)) return false;
        return (node.left != null ? check(node.left, greater, node.val) : true)
        		&& (node.right != null ? check(node.right, node.val, less) : true);
    }
    
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
