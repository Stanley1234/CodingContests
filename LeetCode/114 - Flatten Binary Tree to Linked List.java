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
    
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode cur = root.left;
        while(cur != null && cur.right != null) cur = cur.right;
        if(cur != null) {
            cur.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
}
