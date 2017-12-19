/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private int getSize(ListNode cur) {
		int cnt = 0;
		while(cur != null) {
			cnt ++;
			cur = cur.next;
		}
		return cnt;
	}
	
	private ListNode reverseBack(ListNode head) {
		int size = getSize(head);
        // if size is odd, then (n + 1) lefts and n rights
        int reversedSize = size / 2;
        ListNode pre = head;
        for(int i = 0;i < size - reversedSize - 1;i ++)  
        	pre = pre.next;
        ListNode cur = pre.next;
        while(cur != null && cur.next != null) {
            ListNode p = cur.next;
            ListNode nextNode = cur.next.next;
            p.next = pre.next;
            pre.next = p;
            cur.next = nextNode;
        }
        ListNode nextNode = pre.next;
        pre.next = null;
        return nextNode;
	}
	
	private void merge(ListNode head, ListNode tail) {
		// then merge together
        ListNode back = tail;
        ListNode front = head;
        while(back != null || front != null) {
            ListNode frontNextNode = front.next;  // front.next.next == null
            ListNode backNextNode = (back == null ? null : back.next);
            front.next = back;
            if(back != null) back.next = frontNextNode;
            front = frontNextNode;
            back = backNextNode;
        }
	}
    
    public void reorderList(ListNode head) {
        if(head == null)
            return;
        ListNode back = reverseBack(head);
        merge(head, back);
    }
}

