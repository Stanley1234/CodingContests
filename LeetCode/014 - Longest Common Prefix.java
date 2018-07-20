/**
Approach 1: 
  sort the list of strings and compare the first and last
  
Approach 2:
  compare each string
  
  
How to find longest common suffix?

Approach 1:
  reverse each string and find the longest common prefix
  
Approach 2:
  use endsWith instead of startsWith

*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) { return ""; }
        
        String prefix = strs[0];
        for (int i = 1;i < strs.length;i ++) {
            while (!strs[i].startsWith(prefix) && prefix.length() >= 1) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
}
