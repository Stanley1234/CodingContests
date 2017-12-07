/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode mergeInto(ListNode oldNode, ListNode newNode) {
		while(oldNode != null) {
			newNode.next = new ListNode(oldNode.val);           
			newNode = newNode.next;
			oldNode = oldNode.next;           
		}
        return newNode;
	}
	
	public ListNode partition(ListNode head, int x) {
		ListNode headP1 = new ListNode(-1);
		ListNode headP2 = new ListNode(-1);
		
		ListNode cur = head;
		ListNode curP1 = headP1;
		ListNode curP2 = headP2;
		
		while(cur != null) {
			if(cur.val < x) {
				curP1.next = new ListNode(cur.val);
				curP1 = curP1.next;
			} else {
				curP2.next = new ListNode(cur.val);
				curP2 = curP2.next;
			}
			cur = cur.next;
		}
		
		// merge together
		ListNode newHead = new ListNode(-1);
		mergeInto(headP2.next, mergeInto(headP1.next, newHead));
		return newHead.next;
    }
}
