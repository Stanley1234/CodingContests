class Solution {

    private String removeLeadingZero(String num) {
        int index = 0;
        while (index < num.length() && num.charAt(index) == '0') { index ++; }
        if (index == num.length()) { return "0"; }
        else { return num.substring(index); }
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<Character>();
        int cur = 0;
        while (k > 0 && cur < num.length()) {
            char ch = num.charAt(cur);
            while (!stack.isEmpty() && stack.peek() > ch && k -- > 0) {
                stack.pop();
            }
            stack.push(ch);
            cur ++;
        }
        while (cur < num.length()) {
            stack.push(num.charAt(cur));
            cur ++;
        }
        while (k -- > 0) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return removeLeadingZero(sb.reverse().toString());
    }
}
