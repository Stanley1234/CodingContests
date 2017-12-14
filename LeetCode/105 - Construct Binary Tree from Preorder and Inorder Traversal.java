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
	
	private TreeNode addNodes(int[] inorder, int[] preorder, 
							  int leftInorder, int rightInorder,
							  int leftPreorder, int rightPreorder) {
		
		if(leftInorder > rightInorder)
			return null;
		int rootIndex = findPos(inorder, preorder[leftPreorder], leftInorder, rightInorder);
		int leftLen = rootIndex - leftInorder;
		
		TreeNode curNode = new TreeNode(inorder[rootIndex]);
		curNode.left = addNodes(inorder, preorder, leftInorder, rootIndex - 1, leftPreorder + 1, leftPreorder + leftLen);
		curNode.right = addNodes(inorder, preorder, rootIndex + 1, rightInorder, leftPreorder + leftLen + 1, rightPreorder);
		return curNode;
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        return addNodes(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
    }
}
