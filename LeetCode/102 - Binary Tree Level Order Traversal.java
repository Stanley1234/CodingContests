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
    public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		Deque<TreeNode> deq = new LinkedList<>();
		
		TreeNode prevRoot = new TreeNode(-1);
		prevRoot.left = root;
		deq.addLast(prevRoot);

		do {
			
			// add children nodes into deque
			int cnt = deq.size();
			while(cnt -- > 0) {
				TreeNode parent = deq.pollFirst();
				if(parent.left != null) 
					deq.addLast(parent.left);
				if(parent.right != null) 
					deq.addLast(parent.right);
			}
			
			// add answers
			List<Integer> curAns = new ArrayList<>();
			Iterator<TreeNode> iter = deq.iterator();
			while(iter.hasNext()) {
				curAns.add(iter.next().val);
			}
			ans.add(curAns);
		} while(!deq.isEmpty());
		ans.remove(ans.size() - 1); // the last one will always be empty
		return ans;
    }
}
