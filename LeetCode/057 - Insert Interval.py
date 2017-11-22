class Solution(object):
    def insert(self, intervals, newInterval):
        """
        :type intervals: List[Interval]
        :type newInterval: Interval
        :rtype: List[Interval]
        """
        intervals.append(newInterval)
        intervals.sort(key = lambda x : x.start)
        
        
        ansIntervals = []
        for i, interval in enumerate(intervals):
            curInterval = intervals[i]
            if i > 0:
                if intervals[i].start <= ansIntervals[-1].end:
                    curInterval = ansIntervals.pop()
                    curInterval.end = max(curInterval.end, intervals[i].end)
            ansIntervals.append(curInterval)
            
        return ansIntervals
