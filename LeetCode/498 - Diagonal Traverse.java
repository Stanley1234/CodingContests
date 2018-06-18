class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int r = matrix.length, c = matrix[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        boolean startDir = true;  // upward - true
        int curR = 0, curC = 0;

        while (curR != r && curC != c) {
            if (startDir) { // upward
                while (curR >= 0 && curC < c) {
                    ans.add(matrix[curR][curC]);
                    curR --;
                    curC ++;
                }
                curR ++;
                curC --;
                if (curC < c - 1) {
                    curC ++;
                } else {
                    curR ++;
                }

            } else { // downward
                while (curR < r && curC >= 0) {
                    ans.add(matrix[curR][curC]);
                    curR ++;
                    curC --;
                }
                curR --;
                curC ++;
                if (curR < r - 1) {
                    curR ++;
                } else {
                    curC ++;
                }
            }
            startDir = !startDir;
        }

        return ans.stream().mapToInt(i->i).toArray();
    }
}
