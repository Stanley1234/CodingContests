class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode phead = new ListNode(-1);
        phead.next = head;
        
        ListNode cur = head;
        while(cur != null) {
            ListNode nextnode = cur.next;
            cur.next = (phead.next == cur ? null : phead.next);
            phead.next = cur;
            cur = nextnode;
        }
        return phead.next;
    }
}
