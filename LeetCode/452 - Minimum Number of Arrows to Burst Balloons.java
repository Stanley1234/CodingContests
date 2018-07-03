class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) { return 0; }
        
        Comparator<int[]> comparator = (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        };
        Arrays.sort(points, comparator);
        
        int cnt = 0;
        int leftIntersect = points[0][0];
        int rightIntersect = points[0][1];
        
        for (int i = 1;i < points.length;i ++) {
            if (points[i][0] > rightIntersect) {
                cnt ++;
                leftIntersect = points[i][0];
                rightIntersect = points[i][1];
            } else {
                leftIntersect = Math.max(leftIntersect, points[i][0]);
                rightIntersect = Math.min(rightIntersect, points[i][1]);
            }
        }
        cnt ++;
        
        return cnt;
    }
}
