public class Soln_621 {

    /*
    * Fill Strategy:
    * For each row, fill as many as possible, but not exceed n + 1
    *
    * 1. AAABBB, n = 2
    * ABX
    * ABX
    * ABX
    *
    * 2. AAAABBBEEFFGG, n = 3
    * ABEF
    * ABGE
    * ABGF
    * A
    *
    *
    * */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A'] ++;
        }
        Arrays.sort(count);

        int time = 0;
        while (count[25] > 0) { // some tasks are still unassigned
            int i = 0;
            while (i < n + 1) {
                if (count[25] == 0) {  // the last row, all tasks are finished
                    break;
                }
                if (25 - i > 0 && count[25 - i] > 0) {
                    count[25 - i] --;
                }
                time ++;
                i ++;
            }
            Arrays.sort(count);
        }
        return time;
    }

}
