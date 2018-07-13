class Solution {
    
    /*
    Digit root problem
    We have a1 * 100 + b1 * 10 + c1 = a1 + b1 + c1  (mod 9) 
    Then a1 + b1 + c1 = a2 * 10 + b2 = a2 + b2 (mod 9)
    Think about why this chain is useful :P
    
    643 = 6 + 4 + 3 = 13 = 1 + 3 = 4 (mod 9)
    
    
    */
    
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
