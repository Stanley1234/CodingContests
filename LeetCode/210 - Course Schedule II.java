class Solution {
    
    private int inDegrees[];
    private List<List<Integer>> graph;
    
    private void init(int numCourses, int[][] preqs) {
        inDegrees = new int[numCourses];
        graph = new ArrayList<>();
        for(int i = 0;i < numCourses;i ++)
            graph.add(new ArrayList<>());
        
        for(int i = 0;i < preqs.length;i ++) {
            inDegrees[preqs[i][0]] ++;
            graph.get(preqs[i][1]).add(preqs[i][0]);
        }
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        int[] res = new int[numCourses];
        init(numCourses, prerequisites);
        
        for(int i = 0;i < numCourses;i ++) {
            
            boolean possible = false;
            for(int j = 0;j < numCourses;j ++) {
                if(inDegrees[j] == 0) {
                    res[i] = j;
                    inDegrees[j] --;
                    
                    List<Integer> adjNodes = graph.get(j);
                    for(int k = 0;k < adjNodes.size();k ++)
                        inDegrees[adjNodes.get(k)] --;
                    possible = true;
                    break;
                }
            }
            if(!possible)
                return new int[0];
        }
        return res;
    }
}
