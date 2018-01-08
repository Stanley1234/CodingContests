
// Solution 1: Binary Search Tree 
// Time: ~450 ms (super inefficient)
class Solution {
    
    private class BSTree {
        private class Node {
            public int val;
            public int size = 1;
            public Node left;
            public Node right;
            
            public Node(int val) {
                this.val = val;
            }
        }
        
        private Node root;
        
        private Node insertNode(Node cur, int val) {
            if(cur == null)
                return new Node(val);
            
            if(val <= cur.val) {
                cur.left = insertNode(cur.left, val);
                cur.size ++;
            } else {
                cur.right = insertNode(cur.right, val);
            }
            return cur;
        }
        
        public void insert(int val) {
            root = insertNode(root, val);
        }
        
        private int searchNodes(Node cur, int k) {
            if(cur.size == k)
                return cur.val;
            if(k > cur.size)
                return searchNodes(cur.right, k - cur.size);
            return searchNodes(cur.left, k);
        }
        
  
        public int searchKthSmallest(int k) {
            return searchNodes(root, k);
        }
        
    }
    
    
    public int findKthLargest(int[] nums, int k) {
        BSTree bstree = new BSTree();
        
        for(int i = 0;i < nums.length;i ++)
            bstree.insert(nums[i]);
        return bstree.searchKthSmallest(nums.length - k + 1);
    }
}

// Solution 2: Simple Sort
class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
}

