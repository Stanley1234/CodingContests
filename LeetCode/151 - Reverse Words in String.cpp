class Solution {
    
    bool isChar(char ch) {
        return ch != ' ';
    }

    
public:
    void reverseWords(string &s) {
        vector<int> wordStartIndex;
        int lenOfStr = s.length();
        bool newWord = false;
        for(int i = 0;i < lenOfStr;i ++) {
            if(isChar(s[i])) {
                if(!newWord) {
                    wordStartIndex.push_back(i);
                    newWord = true;
                }
            } else {
                newWord = false;
            }
        }
        
        string t;
        int sizeOfVec = wordStartIndex.size();
        for(int i = sizeOfVec - 1;i >= 0;i --) {
            int j = wordStartIndex[i];
            while(j < lenOfStr && isChar(s[j])) {
                t += s[j];
                j ++;
            }
            if(i > 0)
                t += " ";
        }
        s = t;
    }
};
