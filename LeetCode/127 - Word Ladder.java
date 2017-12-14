// SLOW - 1000 Ms

class Solution {
    
    private int ans = 0;
    
    private int differBy(String w1, String w2) {
    	int d = 0;
    	for(int i = 0;i < w1.length();i ++)
    		if(w1.charAt(i) != w2.charAt(i))
    			d ++;
    	return d;
    }
   
    private int createMap(List<List<Integer>> wordMap,
    					String beginWord, List<String> wordList) {	
    	int beginWordIndex = -1;
    	for(int i = 0;i < wordList.size();i ++) {		
    		if(wordList.get(i).equals(beginWord)) {
    			beginWordIndex = i;
    			continue;
    		}	
    		List<Integer> curVertex = new ArrayList<>();
    		for(int j = 0;j < wordList.size();j ++) {
    			if(i == j) continue;
    			if(differBy(wordList.get(i), wordList.get(j)) == 1) {
    				curVertex.add(j);
    			}
    		}
    		wordMap.add(curVertex);
    	}
    	// add beginWord into map
    	List<Integer> beginVertex = new ArrayList<>();
    	for(int i = 0;i < wordList.size();i ++) {
    		if(beginWordIndex == i || differBy(beginWord, wordList.get(i)) != 1) continue;
    		beginVertex.add(i);
    	}
    	if(beginWordIndex == -1)
    		beginWordIndex = wordList.size();
    	wordMap.add(beginWordIndex, beginVertex);
    	return beginWordIndex;
    }
    
    private void BFS(int beginIndex, int endIndex,  List<List<Integer>> wordMap) {
    	 Queue<Integer> q = new LinkedList<>();
    	 Map<Integer, Integer> steps = new HashMap<>();
    	 
    	 q.add(beginIndex);
    	 steps.put(beginIndex, 1);
    	 while(!q.isEmpty()) {
    		 int curIndex = q.poll();
    		 List<Integer> curVertex = wordMap.get(curIndex);
    		 
    		 if(curIndex == endIndex) {
    			 ans = steps.get(endIndex);
    			 return;
    		 }
    		 
    		 for(int i = 0;i < curVertex.size();i ++) {
    			 int destIndex = curVertex.get(i);
    			 if(steps.containsKey(destIndex))
    				 continue;
       			 q.add(destIndex);
    			 steps.put(destIndex, steps.get(curIndex) + 1);
    		 }
    	 }
    }
    
    private int search(String s, List<String> list) {
    	for(int i = 0;i < list.size();i ++)
    		if(s.equals(list.get(i)))
    			return i;
    	return -1;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	// create a map
    	List<List<Integer>> wordMap = new ArrayList<>();
    	int beginIndex = createMap(wordMap, beginWord, wordList);
    	int endIndex = search(endWord, wordList);
    	if(endIndex == -1)
    		return 0;
    	// bfs to search all paths
    	BFS(beginIndex, endIndex, wordMap);
    	return ans;
    }
}
