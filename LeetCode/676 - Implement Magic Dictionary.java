class MagicDictionary {

    private class Node {
      Node[] children;
      boolean isWord;

      public Node() {
         children = new Node[26];
      }
   }

   private Node root;

   /** Initialize your data structure here. */
   public MagicDictionary() {
      root = new Node();
   }

   /** Build a dictionary through a list of words */

   private void insertWord(String word) {
      Node curNode = root;
      for (char ch : word.toCharArray()) {
         if (curNode.children[ch - 'a'] == null) {
            curNode.children[ch - 'a'] = new Node();
         }
         curNode = curNode.children[ch - 'a'];
      }
      curNode.isWord = true;
   }

   public void buildDict(String[] dict) {
      for (String word : dict) {
         insertWord(word);
      }
   }

   /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */

   private boolean searchHelper(Node node, String word, int index, boolean isModified) {
      boolean find = false;

      if (index == word.length()) {
         return isModified && node.isWord;
      }

      for (int i = 0;i < 26;i ++) {
         if (node.children[i] == null || (isModified && word.charAt(index) - 'a' != i)) {
            continue;
         }

         if (word.charAt(index) - 'a' != i) {
            find = searchHelper(node.children[i], word, index + 1, true);
         } else {
            find = searchHelper(node.children[i], word, index + 1, isModified);
         }

         if (find) {
            break;
         }
      }
      return find;
   }

   public boolean search(String word) {
      return searchHelper(root, word, 0, false);
   }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
