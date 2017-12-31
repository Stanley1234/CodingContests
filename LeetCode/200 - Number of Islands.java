class Solution {
    
    private final int[] offsetR = {-1, 0, 1, 0};
	private final int[] offsetC = {0, 1, 0, -1};
	
	private boolean withinBoundary(int r, int c, int height, int width) {
		return 0 <= r && r < height && 0 <= c && c < width;
	}
	
    private void bfs(char[][] grid, int r, int c) {
        int height = grid.length, width = grid[0].length;
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        q.offer(r * width + c);
        while(!q.isEmpty()) {
        	int curPos = q.poll();
        	int curR = curPos / width;
        	int curC = curPos % width;
        	
        	for(int i = 0;i < 4;i ++) {
        		int nr = curR + offsetR[i], nc = curC + offsetC[i];
        		int newPos = nr * width + nc;
        		if(withinBoundary(nr, nc, height, width) && grid[nr][nc] == '1' && visited.add(newPos)) {
        			grid[nr][nc] = '0';
        			q.offer(newPos);
        		}
        	}
        }
    }
    
    public int numIslands(char[][] grid) {
        int height = grid.length;
        if(height == 0) 
            return 0;
        int width = grid[0].length;
        int cnt = 0;
        for(int i = 0;i < height;i ++) {
            for(int j = 0;j < width;j ++) {
                if(grid[i][j] == '1') {
                    bfs(grid, i, j);
                    cnt ++;
                }
            }
        }
        return cnt;
    }
}
