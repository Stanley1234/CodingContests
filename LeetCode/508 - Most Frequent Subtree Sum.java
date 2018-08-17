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
    private Map<Integer, Integer> sumCounter = new HashMap<>();
	
	private int getSum(TreeNode root) {
		if (root.left == null && root.right == null) {
			sumCounter.put(root.val, sumCounter.getOrDefault(root.val, 0) + 1);
			return root.val;
		}
		
		int leftSum = 0;
		if (root.left != null) {
			leftSum = getSum(root.left);
		}
		
		int rightSum = 0;
		if (root.right != null) {
			rightSum = getSum(root.right);
		}
		
		int totalSum = root.val + leftSum + rightSum;
		sumCounter.put(totalSum, sumCounter.getOrDefault(totalSum, 0) + 1);
		return totalSum;
	}
    
    private int[] getResult() {
		int mostFrequentTimes = 0;
		for (int val : sumCounter.values()) {
			mostFrequentTimes = Math.max(mostFrequentTimes, val);
		}
		List<Integer> frequentSum = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : sumCounter.entrySet()) {
			if (entry.getValue() == mostFrequentTimes) {
				frequentSum.add(entry.getKey());
			}
		}
		int[] results = new int[frequentSum.size()];
		for (int i = 0;i < frequentSum.size();i ++) {
			results[i] = frequentSum.get(i);
		}
		return results;
	}
	
	public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
        	return new int[0];
        }
        getSum(root);
        return getResult();
    }
}
