/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private int getLen(ListNode head) {
        int len = 0;
        while(head != null && head.next != null) {
            len ++;
            head = head.next;
        }
        return len;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if(head == null)
            return null;
        n = getLen(head) - n;
        ListNode cur = head;
        ListNode prev = null;
        while(n -- >= 0) {
            prev = cur;
            cur = cur.next;
        }
        if(prev == null) {
            return cur.next;
        } else {
            prev.next = cur.next;
            return head;
        }   
    }
}
