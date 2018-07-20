class Solution {
    public List<String> removeComments(String[] source) {
        // each string in the answer list will be non-empty but could be whitespaces
		
		// the replaceAll method pauses matching at '\n' unless
		// it is explicitly required so
		// Use (.|\n)* rather than .* to explicitly require it
		String regex = "//(.*)|/\\*(.|\n)*?\\*/";
        String[] result = String.join("\n", source).replaceAll(regex, "").split("\n");
        List<String> ans = new ArrayList<>();
        for (String line : result) {
        	if (line.isEmpty()) {
        		continue;
        	}
        	ans.add(line);
        }
        return ans;
    }
}
