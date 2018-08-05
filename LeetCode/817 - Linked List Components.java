/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
     public int numComponents(ListNode head, int[] G) {
      int numOfComponents = 0;

      Set<Integer> unvisited = new HashSet<>();
      for (int n : G) {
         unvisited.add(n);
      }

      boolean connected = false;
      while (head != null) {
         if (!connected && unvisited.contains(head.val)) {
            connected = true;
            numOfComponents ++;
         } else if (connected && !unvisited.contains(head.val)) {
            connected = false;
         }
         head = head.next;
      }
      
      return numOfComponents;
   }

}
