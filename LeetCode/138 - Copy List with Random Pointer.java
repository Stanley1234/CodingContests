/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return null;
        
        
        // construct a new list ignorant of random part
        RandomListNode cur = head;
        RandomListNode newHead = new RandomListNode(-1);
        RandomListNode newCur = newHead;
        
        Map<Integer, RandomListNode> nodeMap = new HashMap<>();
        
        while(cur != null) {
            newCur.next = new RandomListNode(cur.label);
            newCur = newCur.next;            
            nodeMap.put(cur.label, newCur);
            cur = cur.next;
        }
        
        // add the random part
        newCur = newHead.next;
        cur = head;
        while(cur != null) {
            if(cur.random == null)
                newCur.random = null;
            else
                newCur.random = nodeMap.get(cur.random.label);
            newCur = newCur.next;
            cur = cur.next;
        }
            
        return newHead.next;
    }
}
