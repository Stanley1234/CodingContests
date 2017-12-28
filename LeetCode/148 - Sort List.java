/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
	 private ListNode mergeSort(ListNode head, ListNode tail, int size) {
		 if(size <= 1)
			 return head;
		 
		 int cnt = size / 2;
		 ListNode cur = head;
		 while(-- cnt > 0) {
			 cur = cur.next;
		 }
		 ListNode fTail = cur;
		 ListNode sHead = cur.next;
		 cur.next = null;
		
		 
		 head = mergeSort(head, fTail, size / 2);
		 sHead = mergeSort(sHead, tail, size - size / 2);
		 
		 
		 head = merge(head, fTail, sHead, tail);
		 return head;
	 }
	 private ListNode merge(ListNode fhead, ListNode ftail, ListNode shead, ListNode stail) {
		 ListNode fCur = fhead;
		 ListNode sCur = shead;
		 
		 ListNode newhead = new ListNode(-1);
		 ListNode cur = newhead;
		 while(fCur != null && sCur != null) {
			 
			 //ListNode nextNode;
			 if(fCur.val <= sCur.val) {
				 /*nextNode = fCur.next;
				 fCur.next = null;
				 cur.next = fCur;
				 fCur = nextNode;*/
				 cur.next = fCur;
				 fCur = fCur.next;
				 
			 } else {
				 cur.next = sCur;
				 sCur = sCur.next;
			 }
			 cur = cur.next;
		 }
		 
		 
		 if(fCur != null) 
			 cur.next = fCur;
		 if(sCur != null)
			 cur.next = sCur;
		 return newhead.next; 
	 }
	
	 private int getSize(ListNode head) {
		 int cnt = 0;
		 while(head != null) {
			 cnt ++;
			 head = head.next;
		 }
		 return cnt;
	 }
	 public ListNode sortList(ListNode head) {
		 if(head == null)
			 return null;
		 
		 ListNode tail = head;
		 while(tail.next != null)
			 tail = tail.next;
		 return mergeSort(head, tail, getSize(head));
	 }
}
