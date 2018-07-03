class Solution {
    private int findInsertionIndex(List<int[]> ans, int val, int freq) {
        int index = 0;
        int cnt = 0;
        for (int[] elem : ans) {
            // in order to preserve invariants of previous int[]
            // find the index as large as possible
            if (val <= elem[0]) {
                cnt ++;
            }
            if (cnt > freq) {
                break;
            }
            index ++;
        }
        return index;
    }

    public int[][] reconstructQueue(int[][] people) {
        Comparator<int[]> comparator = (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        };
        Arrays.sort(people, comparator);

        List<int[]> ans = new LinkedList<>();
        for (int[] person : people) {
            int val = person[0], freq = person[1];
            int insertPnt = findInsertionIndex(ans, val, freq);
            ans.add(insertPnt, new int[] {val, freq});
        }

        int[][] res = new int[people.length][2];
        int cnt = 0;
        for (int[] elem : ans) {
            res[cnt][0] = elem[0];
            res[cnt][1] = elem[1];
            cnt ++;
        }
        return res;
    }
}
