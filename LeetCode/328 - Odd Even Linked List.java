/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
      if (head == null) {
         return head;
      }

      ListNode odd = head;
      ListNode even = head.next;
      ListNode evenStart = even;
      while (even != null && even.next != null) {
         ListNode evenNext = even.next.next;

         odd.next = even.next;
         odd = odd.next;
         odd.next = evenStart;

         even.next = evenNext;
         even = even.next;
      }
      return head;
   }
}
