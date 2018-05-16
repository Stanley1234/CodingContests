// The following method is slow but explain one thing:
// when searching, the area to be searched is supposed to narrowen after each iteration.

public class Soln_240 {

    private boolean search(int[][] matrix, int target, int lrow, int lcol, int rrow, int rcol) {
        if(lrow > rrow || lcol > rcol) return false;
        int midRow = (lrow + rrow) / 2;
        int midCol = (lcol + rcol) / 2;
        if(matrix[midRow][midCol] == target)
            return true;
        if(target < matrix[midRow][midCol])
            return search(matrix, target, lrow, lcol, midRow, midCol - 1) ||
                    search(matrix, target, lrow, midCol, midRow -1 , rcol) ||
                    search(matrix, target, midRow + 1, lcol, rrow, midCol - 1);
        return search(matrix, target, lrow, midCol + 1, midRow, rcol) ||
                search(matrix, target, midRow + 1, midCol + 1, rrow, rcol) ||
                search(matrix, target, midRow + 1, lcol, rrow, midCol);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        return search(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,   2,  3,  4,  5},
                {6,   7,  8,  9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        Soln_240 soln_240 = new Soln_240();
        //System.out.println(soln_240.searchMatrix(matrix, 1) == true);
        //System.out.println(soln_240.searchMatrix(matrix, 13) == true);
        //System.out.println(soln_240.searchMatrix(matrix, 14) == true);
        System.out.println(soln_240.searchMatrix(matrix, 10) == true);
        //System.out.println(soln_240.searchMatrix(matrix, 8) == true);
        //System.out.println(soln_240.searchMatrix(matrix, 15) == true);
    }
}
