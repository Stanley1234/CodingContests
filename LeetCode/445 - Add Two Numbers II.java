/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private int getLen (ListNode head) {
      int len = 0;
      while (head != null) {
         len++;
         head = head.next;
      }
      return len;
   }

   private ListNode compute (ListNode l1, ListNode l2) {
      if (l1 == null) {
         return null;
      }
      ListNode restSumNode = compute(l1.next, l2.next);
      ListNode curSumNode = new ListNode(l1.val + l2.val);

      if (restSumNode != null) {
         curSumNode.val += restSumNode.val / 10;
         restSumNode.val %= 10;
      }
      curSumNode.next = restSumNode;
      return curSumNode;
   }

   public ListNode addTwoNumbers (ListNode l1, ListNode l2) {
      int len1 = getLen(l1), len2 = getLen(l2);

      if (len1 < len2) {
         while (len1 < len2) {
            ListNode newHead = new ListNode(0);
            newHead.next = l1;
            l1 = newHead;

            len1++;
         }
      } else if (len2 < len1) {
         while (len2 < len1) {
            ListNode newHead = new ListNode(0);
            newHead.next = l2;
            l2 = newHead;

            len2++;
         }
      }

      ListNode sumList = compute(l1, l2);
      if (sumList.val >= 10) {
         ListNode newHead = new ListNode(sumList.val / 10);
         newHead.next = sumList;
         sumList.val %= 10;
         sumList = newHead;
      }
      return sumList;
   }
}
