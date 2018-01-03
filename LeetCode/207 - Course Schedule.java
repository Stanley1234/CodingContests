class Solution {

	private List<List<Integer>> graph;
	private int[] inDeg;
	
	private void init(int numCourses, int[][] preqs) {
		inDeg = new int[numCourses];
		graph = new ArrayList<>(numCourses);
		for(int i = 0;i < numCourses;i ++)
			graph.add(new ArrayList<>());
		
		for(int i = 0;i < preqs.length;i ++) {
			inDeg[preqs[i][0]] ++;
			graph.get(preqs[i][1]).add(preqs[i][0]);
		}
	}
	
	private boolean topoSort(int numCourses, int[][] preqs) {
		for(int i = 0;i < numCourses;i ++) {
			boolean chosen = false;
			for(int j = 0;j < numCourses;j ++) {
				if(inDeg[j] == 0) {
					for(int k = 0;k < graph.get(j).size();k ++) 
						inDeg[graph.get(j).get(k)] --;	
					inDeg[j] = -1;
					chosen = true;
					break;
				}
			}
			if(!chosen) 
				return false;
		}
		return true;
	}
	
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0) 
            return true;
        
        init(numCourses, prerequisites);
        return topoSort(numCourses, prerequisites);
    }
}
