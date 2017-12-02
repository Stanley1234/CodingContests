class Solution {
    public String multiply(String num1, String num2) {
       
    	int[] n1 = new int[num1.length()];
    	int[] n2 = new int[num2.length()];
    	
    	for(int i = num1.length() - 1;i >= 0;i --) 
    		n1[n1.length - 1 - i] = num1.charAt(i) - '0';
    	for(int i = num2.length() - 1;i >= 0;i --)
    		n2[n2.length - 1 - i] = num2.charAt(i) - '0';

    	int[] res = new int[num1.length() + num2.length()];
    	Arrays.fill(res, 0);
        for(int i = 0;i < n1.length;i ++) 
        	for(int j = 0;j < n2.length;j ++) 
        		res[i + j] += n1[i] * n2[j];
        	  
        for(int i = 0;i < res.length - 1;i ++) {
        	res[i + 1] += res[i] / 10;
        	res[i] %= 10;
        }
        int lastIndex = res.length - 1;
        while(lastIndex > 0 && res[lastIndex] == 0)
        	lastIndex --;
        
        String resStr = "";
        for(int i = lastIndex;i >= 0;i --)
        	resStr += (char)(res[i] + '0');
       
        return resStr;
    }
}
