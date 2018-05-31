public class Solution {
    public ListNode detectCycle(ListNode head) {
      	if(head == null || head.next == null) return null;
      	if(head == head.next) return head;
      
        ListNode slow = head.next, fast = head.next.next;
        
        while(fast != null && fast.next != null) {
            if(slow == fast) 
                break;
            slow = slow.next;
            fast = fast.next.next;
        }      	
        if(fast == null || fast.next == null)  // no cycle
            return null;
        
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
