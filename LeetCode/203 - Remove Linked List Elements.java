/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pHead = new ListNode(0);
        pHead.next = head;
        
        ListNode cur = head;
        ListNode prev = pHead;
        while(cur != null) {
            if(cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;    
            }
            cur = cur.next;
        }
        return pHead.next;
    }
}
