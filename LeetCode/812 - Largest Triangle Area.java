class Solution {
    
    private class Vector {
        private int x;
        private int y;
        
        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private double area(Vector v1, Vector v2) {
         return Math.abs(v1.x * v2.y - v1.y * v2.x) / 2.0;
    }
    
    public double largestTriangleArea(int[][] points) {
        double ans = 0.0;
        for (int i = 0;i < points.length - 2;i ++) {
            for (int j = i + 1;j < points.length - 1;j ++) {
                for (int k = j + 1;k < points.length;k ++) {
                    Vector v1 = new Vector(points[k][0] - points[j][0], points[k][1] - points[j][1]);
                    Vector v2 = new Vector(points[i][0] - points[j][0], points[i][1] - points[j][1]);
                    ans = Math.max(ans, area(v1, v2));
                }
            }
        }
        return ans;
    }
}
