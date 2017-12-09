class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        
    	ListNode newHead = new ListNode(-1);
    	ListNode newCur = newHead;
    	ListNode cur = head;
    	
    	int cnt = 1;
    	while(cnt < m) {
    		newCur.next = new ListNode(cur.val);
    		newCur = newCur.next;
    		cur = cur.next;
    		cnt ++;
    	}
    	
    	// reverse the middle part
    	ListNode middleHead = null;
    	while(cnt <= n) {
    		
    		ListNode nextNode = middleHead;
    		middleHead = new ListNode(cur.val);
    		middleHead.next = nextNode;
    		cur = cur.next;
    		cnt ++;
    	}
    	
    	// add middle head to the first part
    	newCur.next = middleHead;
    	
    	// add the last part
    	while(newCur.next != null)
    		newCur = newCur.next;
    	while(cur != null) {
    		newCur.next = new ListNode(cur.val);
    		newCur = newCur.next;
    		cur = cur.next;
    	}
    	
    	
    	
		return newHead.next;
    }
}
