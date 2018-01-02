class Solution {
    
    private int next(int cur) {
        int sum = 0;
        while(cur > 0) {
            sum += (cur % 10) * (cur % 10);
            cur /= 10;
        }
        return sum;
    }
    
    public boolean isHappy(int n) {
        Set<Integer> used = new HashSet<>();
        
        int cur = n;
        while(cur != 1 && used.add(cur)) {
            cur = next(cur);
        }
        return cur == 1;
    }
}
