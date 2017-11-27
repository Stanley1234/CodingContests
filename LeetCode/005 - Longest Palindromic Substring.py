class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        
        if s == "":
            return ""
        
        dp = [[False for i in range(len(s))] for i in range(len(s))]
        
        for i in range(len(s)):
            dp[i][i] = True
            
        optimal_len = 1
        optimal_index = 0
        
        for i in range(1, len(s)):
            if s[i - 1] == s[i]:
                dp[i - 1][i] = True
                if optimal_len == 1:
                    optimal_len = 2
                    optimal_index = i - 1
                
        
        for k in range(3, len(s) + 1):
            for start in range(len(s) - k + 1):
                end = start + k - 1
                if s[start] == s[end] and dp[start + 1][end - 1] == True:
                    dp[start][end] = True
                    if end - start + 1 > optimal_len:
                        optimal_len = end - start + 1
                        optimal_index = start
        
        return s[optimal_index : optimal_index + optimal_len]
        
        
        
        
