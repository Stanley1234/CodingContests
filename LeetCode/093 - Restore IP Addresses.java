class Solution {
    private List<String> ans;
	
	private void dfs(List<String> curIP, String s, int startIndex) {
		if(curIP.size() == 4 && startIndex == s.length()) {
			String curAns = "";
			for(int i = 0;i < 4;i ++) {
				if(i >= 1)
					curAns += ".";
				curAns += curIP.get(i);
			}
			ans.add(curAns);
			return;
		}
		
		// prune the branch
		if((s.length() - startIndex) > 3 * (4 - curIP.size()))
			return;
		for(int i = startIndex;i < s.length() && i < startIndex + 3;i ++) {
			String possibleSec = s.substring(startIndex, i + 1);
			if(Integer.parseInt(possibleSec) > 255 
					|| (possibleSec.length() >= 2 && possibleSec.charAt(0) == '0'))
				continue;
			curIP.add(possibleSec);
			dfs(curIP, s, i + 1);
			curIP.remove(curIP.size() - 1);
		}
		
	}
	
	
    public List<String> restoreIpAddresses(String s) {
        List<String> tmpIP = new ArrayList<>();
        ans = new ArrayList<>();
        dfs(tmpIP, s, 0);
        return ans;
    }
}
