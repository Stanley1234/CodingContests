/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
		ListNode pHead = new ListNode(Integer.MAX_VALUE);
		pHead.next = head;
		
        ListNode cur = head;
        ListNode prev = pHead;
        while(cur != null) {
        	ListNode p = pHead;
        	ListNode next = cur.next;

        	boolean swapped = false;
        	while(p.next != cur) {
        		if(cur.val >= p.next.val) 
        			p = p.next;
        		else {
        			prev.next = cur.next;
        			cur.next = p.next;
        			p.next = cur;
        			swapped = true;
        			break;
        		}
        	}
        	if(!swapped)  // no swap happen
        		prev = cur;
        	cur = next;
        }
        return pHead.next;
    }
}
