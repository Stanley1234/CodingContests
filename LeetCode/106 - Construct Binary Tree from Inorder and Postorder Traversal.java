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
    private int findPos(int[] arr, int key, int left, int right) {
		for(int i = left;i <= right;i ++)
			if(arr[i] == key)
				return i;
		return -1;
	}
	
	private TreeNode addNodes(int[] inorder, int[] postorder, 
							  int leftInorder, int rightInorder,
							  int leftPostorder, int rightPostorder) {
		
		
		
		if(leftInorder > rightInorder)
			return null;
		
		int rootIndex = findPos(inorder, postorder[rightPostorder], leftInorder, rightInorder);
		int leftLen = rootIndex - leftInorder;
		TreeNode root = new TreeNode(inorder[rootIndex]);
		root.left = addNodes(inorder, postorder, leftInorder, rootIndex - 1, leftPostorder, leftPostorder + leftLen - 1);
		root.right = addNodes(inorder, postorder, rootIndex + 1, rightInorder, leftPostorder + leftLen, rightPostorder - 1);
		return root;
	}
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        return addNodes(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
}
