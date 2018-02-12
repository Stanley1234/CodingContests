// This problem is an interview question by xxxxxxxxxx company
// Split SMS messages:
// SMS messages is sent after spliting into each packet whose length does not exceed the maximum length 
// Make each packet as long as possible

import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Solution {
   
	public int solution(String S, int K) {
		
		StringTokenizer stk = new StringTokenizer(S);
		List<String> words = new ArrayList<>();
		
		while(stk.hasMoreTokens()) {
			words.add(stk.nextToken());
		}
		
		int split = 0;
		int curLen = 0;
		for(int i = 0;i < words.size();i ++) {
			
			String curWord = words.get(i);
			if(curLen == 0 && curWord.length() > K)  // cannot satisfy requirement
				return -1;
			
			if(curLen == 0 && curWord.length() <= K) {
				curLen += curWord.length();
			} else if(curLen > 0 && curLen + curWord.length() + 1 <= K) {
				curLen += curWord.length();
			} else {
				split ++;
				i -- ;
				curLen = 0;
			}
		}
		return split + 1;
    }
	
	public static void main(String[] args) {
		Task task = new Task();
		System.out.println(task.solution("t s u v du", 1));
		System.out.println(task.solution("SMS message are really short", 12));
	}
	
}
