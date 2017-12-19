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
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
        if(root == null)
        	return ans;
    	
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode cur = root;
    	do {
    		while(cur != null) {
    			ans.add(cur.val);
    			stack.push(cur);
    			cur = cur.left;
    		}
    		cur = stack.pop();
    		cur = cur.right;
    	} while(!stack.empty() || cur != null);
    	
    	return ans;
    }
}
