class Solution {
    private class Trie {
       private class Node {
          Node[] children;
          boolean isWord;

          public Node() {
             children = new Node[26];
             isWord = false;
          }
       }

       private Node root;

       public Trie() {
          root = new Node();
       }

       public void insertWord(String word) {
          Node curNode = root;
          for (char ch : word.toCharArray()) {
             if (curNode.children[ch - 'a'] == null) {
                curNode.children[ch - 'a'] = new Node();
             }
             curNode = curNode.children[ch - 'a'];
          }
          curNode.isWord = true;
       }
       /**
        * If word = "rattle" and "rat" is inserted into trie
        * Then "rat" is a <b>prefix word</b> of "rattle"
        * */

       public String findPrefixWordInTrie(String word) {
          Node curNode = root;
          int index = 0;

          for (char ch : word.toCharArray()) {
             if (curNode.isWord || curNode.children[ch - 'a'] == null) {
                break;
             }
             curNode = curNode.children[ch - 'a'];
             index ++;
          }

          if (index == 0 || !curNode.isWord) {
             return null;
          }
          return word.substring(0, index);
       }
    }
    
    public String replaceWords(List<String> dict, String sentence) {
      String[] words = sentence.split("\\s+");
      Trie trie = new Trie();

      for (String word : dict) {
         trie.insertWord(word);
      }

      StringBuilder sb = new StringBuilder(words.length);
      for (int i = 0;i < words.length;i ++) {
         String word = words[i];

         String prefix = trie.findPrefixWordInTrie(word);
         if (prefix == null) {
            sb.append(word);
         } else {
            sb.append(prefix);
         }
         if (i < words.length - 1) {
            sb.append(' ');
         }
      }
      return sb.toString();
   }
}
