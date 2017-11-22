

class Solution {

	int size;
	List<List<String>> ans = new ArrayList();

	private List<String> numbersToStrings(Integer[] ansInt) {

		List<String> ansStrs = new ArrayList();
		for(int i = 0;i < size;i ++) {
			String str = new String();
			for(int j = 0;j < size;j ++)
				if(ansInt[i] == j)
					str += "Q";
				else
					str += ".";
			ansStrs.add(str);
		}
		return ansStrs;
	}

	private void dfs(int r, Integer[] tmp,
					Boolean[] col, Boolean[] rightUp,
					Boolean[] rightDown) {

		if (r == size) {
			ans.add(numbersToStrings(tmp));
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



    public List<List<String>> solveNQueens(int n) {
        size = n;

        Integer[] tmp = new Integer[n];
        Boolean[] col = new Boolean[n],
        		  rightUp = new Boolean[2 * n - 1],
        		  rightDown = new Boolean[2 * n - 1];

        Arrays.fill(col, false);
        Arrays.fill(rightUp, false);
        Arrays.fill(rightDown, false);

        dfs(0, tmp, col, rightUp, rightDown);

        return ans;
    }

    
}
