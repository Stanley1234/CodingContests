

class Solution {

	int size;
	int ansNum = 0;

	private void dfs(int r, Integer[] tmp,
					Boolean[] col, Boolean[] rightUp,
					Boolean[] rightDown) {

		if (r == size) {
			ansNum ++;
			return;
		}

		for(int c = 0;c < size;c ++) {

			if(col[c] || rightUp[r + c] || rightDown[size - 1 - r + c])
				continue;

			col[c] = rightUp[r + c] = rightDown[size - 1 - r + c] = true;
			tmp[r] = c;
			dfs(r + 1, tmp, col, rightUp, rightDown);
			col[c] = rightUp[r + c] = rightDown[size - 1 - r + c] = false;
		}
	}



    public int totalNQueens(int n) {
        size = n;

        Integer[] tmp = new Integer[n];
        Boolean[] col = new Boolean[n],
        		  rightUp = new Boolean[2 * n - 1],
        		  rightDown = new Boolean[2 * n - 1];

        Arrays.fill(col, false);
        Arrays.fill(rightUp, false);
        Arrays.fill(rightDown, false);

        dfs(0, tmp, col, rightUp, rightDown);

        return ansNum;
    }

    
}
