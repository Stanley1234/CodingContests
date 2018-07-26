class Solution {
    private boolean used1to9(int[][] grid, int r1, int c1, int r2, int c2) {
    	boolean[] used = new boolean[10];
    	for (int i = r1;i <= r2;i ++) {
    		for (int j = c1;j <= c2;j ++) {
    			if (grid[i][j] < 1 || grid[i][j] > 9 || used[grid[i][j]]) {
    				return false;
    			}
    			used[grid[i][j]] = true;
    		}
    	}
    	return true;
    }
    
    public int numMagicSquaresInside(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int count = 0;
        
        // enumerate the right bottom
        // to simplify codes: note that the sum of each row / col / diagnoal must be 15
        for (int i = 2;i < r;i ++) {
            for (int j = 2;j < c;j ++) {
                if (!used1to9(grid, i - 2, j - 2, i, j)) { continue; }
                
                if (grid[i - 2][j - 2] + grid[i - 2][j - 1] + grid[i - 2][j] != 15) { continue; }
                if (grid[i - 1][j - 2] + grid[i - 1][j - 1] + grid[i - 1][j] != 15) { continue; }
                if (grid[i][j - 2] + grid[i][j - 1] + grid[i][j] != 15) { continue; }
                
                if (grid[i - 2][j - 2] + grid[i - 1][j - 2] + grid[i][j - 2] != 15) { continue; }
                if (grid[i - 2][j - 1] + grid[i - 1][j - 1] + grid[i][j - 1] != 15) { continue; }
                if (grid[i - 2][j] + grid[i - 1][j] + grid[i][j] != 15) { continue; }
                
                if (grid[i - 2][j - 2] + grid[i - 1][j - 1] + grid[i][j] != 15) { continue; }
                if (grid[i][j - 2] + grid[i - 1][j - 1] + grid[i - 2][j] != 15) { continue; }
                
                count ++;
            }
        }
        return count;
    }
}
