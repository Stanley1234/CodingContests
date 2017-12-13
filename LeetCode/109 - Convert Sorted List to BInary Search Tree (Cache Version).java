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
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private int getLength(ListNode list) {
        int len = 0;
        while(list != null) {
            len ++;
            list = list.next;
        }
        return len;
    }
    
    private TreeNode buildTree(int[] arr, int left, int right) {
        if(left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode curNode = new TreeNode(arr[mid]);
        curNode.left = buildTree(arr, left, mid - 1);
        curNode.right = buildTree(arr, mid + 1, right);
        return curNode;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        int[] cache = new int[getLength(head)];
        ListNode cur = head;
        int cnt = 0;
        while(cur != null) {
            cache[cnt ++] = cur.val;
            cur = cur.next;
        }
        return buildTree(cache, 0, cache.length - 1);
    }
}
