# Definition for an interval.
# class Interval(object):
#     def __init__(self, s=0, e=0):
#         self.start = s
#         self.end = e

class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        
        """
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
