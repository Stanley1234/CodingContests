class Solution {
    public int evalRPN(String[] tokens) {
    	if(tokens.length == 0)
    		return 0;
        Stack<Integer> numbers = new Stack<>();
        for(int i = 0;i < tokens.length;i ++) {
        	if(tokens[i].equals("+")) {
        		numbers.push(numbers.pop() + numbers.pop());		
        	} else if(tokens[i].equals("-")) {
        		int s2 = numbers.pop();
        		int s1 = numbers.pop();
        		numbers.push(s1 - s2);
        	} else if(tokens[i].equals("*")) {
        		numbers.push(numbers.pop() * numbers.pop());
        	} else if(tokens[i].equals("/")) {
        		int s2 = numbers.pop();
        		int s1 = numbers.pop();
        		numbers.push(s1 / s2);
        	} else {
        		numbers.push(Integer.parseInt(tokens[i]));
        	}
        }
        return numbers.pop();
    }
}
