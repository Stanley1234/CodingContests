/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;t
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private ListNode refToList;
    
    private int getLength(ListNode list) {
        int len = 0;
        while(list != null) {
            len ++;
            list = list.next;
        }
        return len;
    }
    
    private TreeNode buildBST(int size) {
        // one pass
        if(size <= 0)
            return null;
        TreeNode curnode = new TreeNode(-1);
        curnode.left = buildBST(size / 2);
        curnode.val = refToList.val;
        refToList = refToList.next;
        curnode.right = buildBST(size - size / 2 - 1);
        return curnode;
    }
   
    public TreeNode sortedListToBST(ListNode head) {
        refToList = head;
        return buildBST(getLength(head));
    }
}
