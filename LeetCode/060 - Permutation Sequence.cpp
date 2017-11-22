class Solution {
public:
    string getPermutation(int n, int k) {
        int *arr = new int[n];
        for(int i = 0;i < n;i ++)
            arr[i] = i + 1;
        
        while(-- k > 0) {
            next_permutation(arr, arr + n) ;
        }
        
        string ans;
        for(int i = 0;i < n;i ++) 
            ans += ('0' + arr[i]);
        delete arr;
        return ans;
    }
};
