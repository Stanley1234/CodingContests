class Solution {
    private int hashPosn(int r, int c, int width) {
		return r * width + c;
	}
    
    private boolean dfs(char[][] board, String word,
						int curFindIndex, int r, int c,
						Map<Integer, Boolean> visited) {
		
		
		if(board[r][c] != word.charAt(curFindIndex))
			return false;
		if(curFindIndex == word.length() - 1) 
			return true;
		
		final int height = board.length;
		final int width = board[0].length;
		
		final int[] rOff = {-1, 0, 1, 0};
		final int[] cOff = {0, 1, 0, -1};
		
		
		visited.put(hashPosn(r, c, width), true);
		for(int k = 0;k < 4;k ++) {
			
			int nr = r + rOff[k], nc = c + cOff[k];
			if(nr < 0 || nc < 0 || nr >= height || nc >= width)
				continue;
			if(visited.containsKey(hashPosn(nr, nc, width)) && 
					visited.get(hashPosn(nr, nc, width)) == true)
				continue;
			if(dfs(board, word, curFindIndex + 1, nr, nc, visited))
				return true;	
		}
		visited.put(hashPosn(r, c, width), false);
		return false;
	}
	
    public boolean exist(char[][] board, String word) {
    	
    	if(board.length == 0 || board[0].length == 0)
    		return false;
    	final int height = board.length;
    	final int width = board[0].length;

    	for(int i = 0;i < height;i ++) {
    		for(int j = 0;j < width;j ++) {
    			if(word.charAt(0) == board[i][j]) {
    				Map<Integer, Boolean> visited = new HashMap<>();
    				if(dfs(board, word, 0, i, j, visited))
    					return true;
    			}
    		}
    	}
    	
        return false;
    }
}
