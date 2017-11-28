class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int optimal = 0;
        
        while(l < r) {
            optimal = Math.max(optimal, Math.min(height[l], height[r]) * (r - l));
            if(height[l] < height[r])
                l ++;
            else
                r --;
        }
        return optimal;
    }
}
