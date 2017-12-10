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
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
    	if(root == null) return ans;
    	
    	Stack<TreeNode> visitedOrder = new Stack<>();
    	TreeNode cur = root;
    	
    	do {
    		while(cur != null) {
    			visitedOrder.add(cur);
    			if(cur.left == null)
    				break;
    			cur = cur.left;
    		}
    		// nextNode is not necessarily cur
    		// it is the next node to be visited
    		TreeNode nextNode = visitedOrder.pop();
    		ans.add(nextNode.val);
    		cur = nextNode.right;
    	
    		
    	} while(cur != null || !visitedOrder.empty());
    	
    	return ans;
    }
}
