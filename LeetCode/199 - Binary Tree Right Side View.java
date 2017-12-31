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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
    		return res;
        
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
        	
        	res.add(q.getLast().val);
        	
        	Deque<TreeNode> newLayer = new LinkedList<>();
        	while(!q.isEmpty()) {
        		TreeNode cur = q.poll();
        		if(cur.left != null)
        			newLayer.offer(cur.left);
        		if(cur.right != null)
        			newLayer.offer(cur.right);
        	}
        	q = newLayer; 	
        }
        
        return res;
    }
}
