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
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        
        if (!(t.left == null && t.right == null)) {  
            // as long as there is something in either subtree, then the left should be printed
            sb.append("(");
            sb.append(tree2str(t.left));
            sb.append(")");
        }
        
        if (t.right != null) {
            // if there is nothing in right subtree, nothing will be printed to the right
            sb.append("(");
            sb.append(tree2str(t.right));
            sb.append(")");
        }
        
        return sb.toString();
    }
}
