/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
        	return null;
       
        
        ListNode newHead = new ListNode(-1);
        ListNode newCur = newHead;
        ListNode oldCur = head;
        
        while(oldCur != null) {
        	if(oldCur.next == null || oldCur.val != oldCur.next.val) {
        		newCur.next = new ListNode(oldCur.val);
        		newCur = newCur.next;
        		oldCur = oldCur.next;
        	} else {
        		int cmp = oldCur.val;
        		while(oldCur != null && oldCur.val == cmp)
        			oldCur = oldCur.next;
        	}
        }
        return newHead.next;
    }
}
