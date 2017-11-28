class Solution {
    
    private final int INT_MAX = 2147483647;
    private final int INT_MIN = -2147483648;
    
    public int myAtoi(String str) {
        int index = 0;
        while(index < str.length() && str.charAt(index) == ' ') {
            index ++;
        }
        
        if(index == str.length() || !(('0' <= str.charAt(index) && str.charAt(index) <= '9') 
            || str.charAt(index) == '+' || str.charAt(index) == '-'))
            return 0;
        
        int factor = 1;
        if(str.charAt(index) == '-' || str.charAt(index) == '+') {
            if(str.charAt(index) == '-')
                factor = -1;
            index ++;
        }
        
        Long res = 0L;
        while(index < str.length() && ('0' <= str.charAt(index) && str.charAt(index) <= '9')) {
            res = res * 10 + (str.charAt(index) - '0');
            index ++;
            
            if(factor * res >= INT_MAX)
                return INT_MAX;
            if(factor * res <= INT_MIN)
                return INT_MIN;
        }
        return factor * res.intValue();
        
        
    }
}
