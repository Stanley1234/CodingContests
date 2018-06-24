class Solution {
    private boolean[] light = new boolean[4 + 6];
    private ArrayList<String> ans = new ArrayList<>();

    private void addAns() {
        int hour = 0;
        for (int i = 0;i < 4;i ++) {
            hour *= 2;
            if (light[i]) {
                hour ++;
            }
        }
        int minute = 0;
        for (int i = 4;i < 10;i ++) {
            minute *= 2;
            if (light[i]) {
                minute ++;
            }
        }
        if (hour > 11) { return; }
        if (minute >= 60) { return; }
        StringBuilder sb = new StringBuilder();
        sb.append(hour);
        sb.append(":");
        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute);
        ans.add(sb.toString());
    }

    public void dfs(int targetNum, int curNum, int index) {
        if (curNum == targetNum) {
            addAns();
            return;
        }
        // prune
        if (curNum + (9 - index + 1) < targetNum) {
            return;
        }

        for (int i = index;i < light.length;i ++) {
            light[i] = true;
            dfs(targetNum, curNum + 1, i + 1);
            light[i] = false;
        }
    }

    public List<String> readBinaryWatch(int num) {
        dfs(num, 0, 0);
        return ans;
    }
}
