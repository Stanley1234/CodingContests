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
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)
			return new ArrayList<>();
        return generateSubtrees(1, n);
    }
	
	private List<TreeNode> generateSubtrees(int s, int e) {
		List<TreeNode> res = new ArrayList<>();
		if(s > e) {
			res.add(null);   // an empty tree
			return res;
		}
		// select a root
		for(int i = s;i <= e;i ++) {
			List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
			List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
			
			for(TreeNode leftRoot : leftSubtrees) {
				for(TreeNode rightRoot : rightSubtrees) {
					TreeNode curRoot = new TreeNode(i);
					curRoot.left = leftRoot;
					curRoot.right = rightRoot;
					res.add(curRoot);
				}
			}
		
		}
		return res;
	}
}
