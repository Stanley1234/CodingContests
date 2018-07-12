/*
Other Approaches:
1. Flatten the binary search tree, and do 2 sum problem on array
2. Instead of having two pointers as left and right, we can have one pointer, which traverses through the binary
   search tree, and records the value of tree node that is enocuntered. For the current node val t, if we have
   already encountered target - t, then we are done.

*/


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
    private abstract class BaseTraverseStack {
        protected Stack<TreeNode> stack = new Stack<>();
        
        public abstract void next();
        
        public boolean hasNext() {
            return stack.size() != 0;
        }
        public int val() {
            return node().val;
        }
        public TreeNode node() {
            return stack.peek();
        }
    }
    
    private class LeftmostTraverseStack extends BaseTraverseStack { 
        public LeftmostTraverseStack(TreeNode cur) {
            addLeftUntilNull(cur);
        }
        
        private void addLeftUntilNull(TreeNode cur) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        
        @Override
        public void next() {
            TreeNode cur = stack.peek();
            stack.pop();
            if (cur.right != null) {
                cur = cur.right;
                addLeftUntilNull(cur);
            }
        }
    }
    
    private class RightmostTraverseStack extends BaseTraverseStack {
       
        public RightmostTraverseStack(TreeNode cur) {
            addRightUntilNull(cur);
        }
        
        private void addRightUntilNull(TreeNode cur) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
        }
        
        
        @Override
        public void next() {
            TreeNode cur = stack.peek();
            stack.pop();
            if (cur.left != null) {
                cur = cur.left;
                addRightUntilNull(cur);
            }
        }
    }
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        LeftmostTraverseStack lts = new LeftmostTraverseStack(root);
        RightmostTraverseStack rts = new RightmostTraverseStack(root);
        
        while (lts.node() != rts.node()) {
            if (lts.val() + rts.val() == k) {
                return true;
            } else if (lts.val() + rts.val() < k) {
                lts.next();
            } else {
                rts.next();
            }
        }
        return false;
    }
}
