class Solution {
    public List<String> removeComments(String[] source) {
        // each string in the answer list will be non-empty but could be whitespaces
        List<String> ans = new ArrayList<>();
        
        int i = 0;
        while (i < source.length) {
            String nonComment = source[i];
            
            while (true) {
            	// The "//" takes effect only if it comes before "/*"
                if (nonComment.indexOf("/*") == -1 || nonComment.indexOf("//") < nonComment.indexOf("/*")) {
                	nonComment = nonComment.replaceAll("//.*", "");
                }
                
                int startBlockIndex = nonComment.indexOf("/*");
                if (startBlockIndex == -1) {
                    break;
                }
                      
                int endBlockIndex = nonComment.indexOf("*/", startBlockIndex + 2);         
                if (endBlockIndex == -1) {
                    // find other lines
                	nonComment = nonComment.substring(0, startBlockIndex);
                    do { 
                        i ++; 
                        endBlockIndex = source[i].indexOf("*/");
                    } while (endBlockIndex == -1);
                    nonComment += source[i].substring(endBlockIndex + 2);
                } else {
                	// on the same line
                	nonComment = nonComment.substring(0, startBlockIndex) + nonComment.substring(endBlockIndex + 2);
                }    
            }
            
            if (nonComment.length() > 0) {
                ans.add(nonComment);
            }
            i ++;
        }
        return ans;
    }
}
