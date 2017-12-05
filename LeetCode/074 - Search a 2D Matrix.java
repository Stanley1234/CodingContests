class Solution {
    private int findRow(int[][] matrix, int target) {
		
		final int rowLen = matrix.length;
		if(rowLen == 0)
			return -1;
		final int colLen = matrix[0].length;
		if(colLen == 0)
			return -1;
		
		int l = 0, r = rowLen - 1;
		while(l <= r) {
			int mid = (l + r) / 2;
			if(matrix[mid][0] <= target && target <= matrix[mid][colLen - 1]) 
				return mid;
			else if(target < matrix[mid][0])
				r = mid - 1;
			else   // target > matrix[mid][colLen - 1]
				l = mid + 1;
		}
		return -1;
	}
	
	
	public int findCol(int[][] matrix, int row, int target) {
		
		if(row == -1)
			return -1;
		final int colLen = matrix[0].length;
		
		int l = 0, r = colLen - 1;
		while(l <= r) {
			int mid = (l + r) / 2;
			if(target == matrix[row][mid])
				return mid;
			else if(target > matrix[row][mid])
				l = mid + 1;
			else
				r = mid - 1;
		}
		return -1;
	}
	
    public boolean searchMatrix(int[][] matrix, int target) {
		int pos = findCol(matrix, findRow(matrix, target), target);
		return pos != -1;
    }
}
