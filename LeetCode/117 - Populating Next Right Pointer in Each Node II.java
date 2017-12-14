/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        Queue<TreeLinkNode> q = new LinkedList();
        q.add(root);
        while(!q.isEmpty()) {
            
            Queue<TreeLinkNode> children = new LinkedList();
            while(!q.isEmpty()) {
                TreeLinkNode cur = q.poll();
                if(q.size() >= 1) {
                    TreeLinkNode next = q.peek();
                    cur.next = next;
                }
                if(cur.left != null)
                    children.add(cur.left);
                if(cur.right != null)
                    children.add(cur.right);
                
            }
            
            q = children;
        }
    }
}
