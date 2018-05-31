/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    
    private HashMap<RandomListNode, RandomListNode> used = new HashMap<>();  // more robust
   
    public RandomListNode copyRandomList(RandomListNode cur) {
        if(cur == null)
            return null;
        RandomListNode newnode = new RandomListNode(cur.label);
        used.put(cur, newnode);
        if(cur.next != null && !used.containsKey(cur.next))
            newnode.next = copyRandomList(cur.next);  
        else 
            newnode.next = used.get(cur.next);
        if(cur.random != null && !used.containsKey(cur.random))
            newnode.random = copyRandomList(cur.random);
        else
            newnode.random = used.get(cur.random);
        return newnode;
    }
}
