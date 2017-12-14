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
    List<List<Integer>> ans = new ArrayList<>();
	private void traverse(TreeNode root, int destSum, int curSum, List<Integer> curAns) {
		if(root == null)
			return;
		if(root.left == null && root.right == null) {
			if(curSum + root.val == destSum) {
				curAns.add(root.val);
				ans.add(new ArrayList<>(curAns));
                curAns.remove(curAns.size() - 1);
			}
			return;
		}
		curAns.add(root.val);
		traverse(root.left, destSum, curSum + root.val, curAns);
		traverse(root.right, destSum, curSum + root.val, curAns);
		curAns.remove(curAns.size() - 1);
	}
    
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> curAns = new ArrayList<>();
        traverse(root, sum, 0, curAns);
		return ans;
    }
}
