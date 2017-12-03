class Solution {
    private class Info implements Comparable<Info>{
      String primal;
      String sorted;

      public Info(String primal, String sorted) {
        this.primal = primal;
        this.sorted = sorted;
      }
      @Override
      public int compareTo(Info o) {
        return sorted.compareTo(o.sorted);
      }
    }
	
    public List<List<String>> groupAnagrams(String[] strs) {
    	
    	Info[] infos = new Info[strs.length];
    	for(int i = 0;i < strs.length;i ++) {
    		char[] charArray = strs[i].toCharArray();
    		Arrays.sort(charArray);
    		
    		infos[i] = new Info(strs[i], new String(charArray));
    	}
    	Arrays.sort(infos);
    	
 
    	List<List<String>> ans = new ArrayList<>();
    	List<String> curGroup = new ArrayList<>();
    	curGroup.add(infos[0].primal);
    	for(int i = 1;i < infos.length;i ++) {
    		if(infos[i].compareTo(infos[i - 1]) == 0) {
    			curGroup.add(infos[i].primal);
    		} else {
    			ans.add(curGroup);
    			curGroup = new ArrayList<>();
    			curGroup.add(infos[i].primal);
    		}
    	}
    	ans.add(curGroup);
    	return ans;
    }
}
