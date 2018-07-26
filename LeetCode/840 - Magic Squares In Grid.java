class Solution {
    
    private int rowSum(int[][] grid, int c1, int c2, int r) {
        int sum = 0;
        for (int j = c1;j <= c2;j ++) {
            sum += grid[r][j];
        }
        return sum;
    }
    
    private int colSum(int[][] grid, int r1, int r2, int c) {
        int sum = 0;
        for (int i = r1;i <= r2;i ++) {
            sum += grid[i][c];
        }
        return sum;
    }
    
    private int diaSum(int[][] grid, int r1, int c1, int r2, int c2) {
        int sum = 0;
        if (r1 < r2) {
            for (int i = 0;i <= r2 - r1;i ++) {
                sum += grid[r1 + i][c1 + i];
            }
        } else {
            for (int i = 0;i <= r1 - r2;i ++) {
                sum += grid[r1 - i][c1 + i];
            }
        }
        return sum;
    }
    
    private boolean used1to9(int[][] grid, int r1, int c1, int r2, int c2) {
    	boolean[] used = new boolean[10];
    	for (int i = r1;i <= r2;i ++) {
    		for (int j = c1;j <= c2;j ++) {
    			if (1 > grid[i][j] || grid[i][j] > 9 || used[grid[i][j]]) {
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
        for (int i = 2;i < r;i ++) {
            for (int j = 2;j < c;j ++) {
                int r1 = rowSum(grid, j - 2, j, i), r2 = rowSum(grid, j - 2, j, i - 1), 
                    r3 = rowSum(grid, j - 2, j, i - 2);
                int c1 = colSum(grid, i - 2, i, j), c2 = colSum(grid, i - 2, i, j - 1), 
                    c3 = colSum(grid, i - 2, i, j - 2);
                int d1 = diaSum(grid, i - 2, j - 2, i, j), d2 = diaSum(grid, i, j - 2, i - 2, j);
                if (r1 == r2 && r2 == r3 && r3 == c1 && c1 == c2 
                		&& c2 == c3 && c3 == d1 && d1 == d2 && used1to9(grid, i - 2, j - 2, i, j)) {
                    count ++;
                }
            }
        }
        return count;
    }
}
