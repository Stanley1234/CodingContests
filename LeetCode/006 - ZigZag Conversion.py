class Solution(object):
    def convert(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        
        if k == 1 or k >= len(s):
            return s
        
        arr = [''] * len(s)
        
        r = 1
        cnt = index = 0
        shift = True
        while cnt < len(s):
            arr[cnt] = s[index]
            if r == 1 or r == k:
                index += (k - 1) + (k - 1)
            else:
                if shift:
                    index += (k - r) + (k - r)
                    shift = False
                else:
                    index += (r - 2) + r
                    shift = True
            
            if index >= len(s):
                r += 1
                index = r - 1
                shift = True
                
            cnt += 1
        
        new_str = ''
        for i in range(len(arr)):
            new_str += arr[i]
            
       
        return new_str
