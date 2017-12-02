class Solution {
    
    List<String> ans;
    
    private void dfs(int usedLeft, int usedRight, int maxPar, StringBuilder tmpAns) {
        
        if(usedLeft == maxPar) {
            
            // if some right parenthesises are not used
            int k = maxPar - usedRight;
            while(k -- > 0) {
                tmpAns.append(")");
            }
            ans.add(tmpAns.toString());
            
            // delete k characters
            k = maxPar - usedRight;
            tmpAns.delete(tmpAns.length() - k, tmpAns.length());
            return ;
        }
        
        // choose left parenthesis
        tmpAns.append("(");
        dfs(usedLeft + 1, usedRight, maxPar, tmpAns);
        
        tmpAns.deleteCharAt(tmpAns.length() - 1);
        
        // choose right parentesis
        if(usedRight < usedLeft) {
            tmpAns.append(")");
            dfs(usedLeft, usedRight + 1, maxPar, tmpAns);
            tmpAns.deleteCharAt(tmpAns.length() - 1);
        }
    }
    
    public List<String> generateParenthesis(int n) {
        
        ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(0, 0, n, sb);
        
        return ans;
        
    }
}
