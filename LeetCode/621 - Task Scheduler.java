import java.util.Arrays;

public class Soln_621 {

    /* 
    * Idle Time Method
    * 
    * 1. AAABBB, 2
    * ABX
    * ABX
    * AB
    * 
    * 2. AAAABBBEEFFGG, 3
    * ABEF
    * ABEF
    * ABGG
    * A
    * 
    * In the above example, we could always find a valid idle table which is a permutation of above table 
    * 
    * ABGF
    * ABEF
    * ABGE
    * A
    * 
    * 3. AAAABBBCCCDDDEE, n = 3
    * 
    * ABCD|E      ABCD|E
    * ABCD|E  or  ABCD|      and so on ....
    * ABCD|       ABCD|E 
    * A           A
    * 
    * 
    * */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A'] ++;
        }
        Arrays.sort(count);

        int height = count[25] - 1, width = n + 1;
        int idleTime = height * width;
        int i = 25;
        while (i >= 0 && count[i] > 0) {
            idleTime -= Math.min(count[i], height);
            i --;
        }
        return idleTime > 0 ? idleTime + tasks.length : tasks.length;
    }
}
