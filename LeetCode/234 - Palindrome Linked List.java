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
        int cnt = 0;
        while (head != null) {
          cnt ++;
          head = head.next;
        }
        return cnt;
	  }
	
    private ListNode findMid(ListNode head) {
        // null => null
        // 1      => 1
        // 1 -> 2    => 2
        // 1 -> 3 -> 2    =>   3
        // 1 -> 3 -> 2 -> 4   =>  2
        
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode begin, ListNode end) {
        // end exclusively
        ListNode helper = new ListNode(-1);
        helper.next = begin;
        
        while (begin != end) {
            ListNode next = begin.next;
            if (helper.next != begin) {
                begin.next = helper.next;
                helper.next = begin;
            } else {
                begin.next = end;
            }
            begin = next;
        }
        
        return helper.next;
    }
    
    private boolean checkPalindrome(ListNode p1Head, ListNode p2Head) {
        while (p2Head != null) {
          if (p1Head.val != p2Head.val) {
            return false;
          }
          p1Head = p1Head.next;
          p2Head = p2Head.next;
        }
        return true;
    }
    
    public boolean isPalindrome(ListNode head) {
        // find middle
        ListNode mid = findMid(head);
        int len = getLen(head);
        
        // reverse the first half
        // mid stays the same
        head = reverse(head, mid);
        
        if (len % 2 == 1) {
            mid = mid.next;
        }
        // check
        return checkPalindrome(head, mid);
    }
}
