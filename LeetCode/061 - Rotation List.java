/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private int getLength(ListNode cur) {
        int cnt;
        for(cnt = 0;cur != null;cnt ++, cur = cur.next) ;
        return cnt;
    }
    
    private ListNode copyNode(ListNode cur) {
        if(cur == null)
            return null;
        ListNode newnode = new ListNode(cur.val);
        newnode.next = copyNode(cur.next);
        return newnode;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        
        int lengthOfList = getLength(head);
        if(lengthOfList == 0)
            return null;
        k = k % lengthOfList;   // k is in [0, lengthOfList)
        
        int newHeadPos = lengthOfList - k;
        
        // copy [newHeadPos, lengthOfList)
        ListNode newhead = null;
        ListNode curnode = head;
        for(int i = 0;i < newHeadPos;i ++) {
            if(i == newHeadPos - 1) {
                ListNode tmpNode = curnode.next;
                curnode.next = null;
                curnode = tmpNode;
            } else {
                curnode = curnode.next;
            }
        }
        
        newhead = copyNode(curnode);
        
        // copy [0, newHeadPos)
        curnode = newhead;
        while(curnode != null && curnode.next != null)
            curnode = curnode.next;
        
        if(curnode != null)
            curnode.next = copyNode(head);
        else
            newhead = copyNode(head);
        
        return newhead;
    }
}
