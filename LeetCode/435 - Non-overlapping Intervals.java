/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        // [1, 3], [1, 7]  remove [1, 7]
        // [1, 3], [2, 5]  remove [2, 5]
        if (intervals.length == 0) { return 0; }
        
        Comparator<Interval> comparator = (o1, o2) -> {
             if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        };
        Arrays.sort(intervals, comparator);

        int cnt = 0;
        int curRight = intervals[0].end;
        for (int i = 1;i < intervals.length;i ++) {
            if (intervals[i].start < curRight) {
                cnt ++;
            } else {
                curRight = intervals[i].end;
            }
        }
        return cnt;
    }
    // cannot sort by start, the below case shows why
    // [[-100,-87],[-86,7],[-85,-76],[-84,-51],[-83,-48],[-82,-36],[-79,-4],[-78,-63],[-77,-14],[-76,-10]]
    // [-86, 7] is a huge interval
    // so we must sort by end
}
