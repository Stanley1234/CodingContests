class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        
        M = len(matrix)
        if matrix:
            N = len(matrix[0])
        else:
            N = 0
        
        spiral_list = []

        up_bound = 1
        down_bound = M - 1
        left_bound = 0
        right_bound = N - 1

        c = -1
        r = 0
        while len(spiral_list) < (M * N):
        	# right
        	while c < right_bound:
        		c += 1
        		spiral_list.append(matrix[r][c])
        	right_bound -= 1

        	# down
        	while r < down_bound:
        		r += 1
        		spiral_list.append(matrix[r][c])
        	down_bound -= 1

        	# left
        	while c > left_bound:
        		c -= 1
        		spiral_list.append(matrix[r][c])
        	left_bound += 1

        	# up
        	while r > up_bound:
        		r -= 1
        		spiral_list.append(matrix[r][c])
        	up_bound += 1

        
        return spiral_list[: (M * N)]
