/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
      if (head == null)   {
          return null;
      }
        
      Stack<Node> nodeStack = new Stack<>();
      Node lastNode = null;

      nodeStack.push(head);
      while (!nodeStack.isEmpty()) {
         Node curNode = nodeStack.pop();

         curNode.prev = lastNode;
         if (lastNode != null) {
            lastNode.next = curNode;
         }
         lastNode = curNode;

         if (curNode.next != null) {
            nodeStack.push(curNode.next);
         }
         if (curNode.child != null) {
            nodeStack.push(curNode.child);
         }
         curNode.child = null;
      }
      return head;
    }
}
