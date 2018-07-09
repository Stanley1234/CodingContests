class Solution {

    
    private enum Senate {
        Dire,
        Radiant
    }
    
    public String predictPartyVictory(String senate) {
        int numOfRadiantAlive = 0, numOfRadiantBanned = 0;
        int numOfDireAlive = 0, numOfDireBanned = 0;
        Queue<Senate> order = new LinkedList<>();
        
        for (int i = 0;i < senate.length();i ++) {
            if (senate.charAt(i) == 'R') {
                order.offer(Senate.Radiant);
                numOfRadiantAlive ++;
            } else {
                order.offer(Senate.Dire);
                numOfDireAlive ++;
            }
        }
        
        while (numOfDireAlive > 0 && numOfRadiantAlive > 0) {
            Senate cur = order.poll();
            if (cur == Senate.Dire) {
                if (numOfDireBanned > 0) {
                    numOfDireBanned --;
                } else {
                    numOfRadiantAlive --;
                    numOfRadiantBanned ++;
                    order.offer(cur);
                }
            } else {
                if (numOfRadiantBanned > 0) {
                    numOfRadiantBanned --;
                } else {
                    numOfDireAlive --;
                    numOfDireBanned ++;
                    order.offer(cur);
                }
            }
        }
        
        return numOfDireAlive > 0 ? "Dire" : "Radiant";
    }
}
