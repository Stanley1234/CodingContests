class Solution(object):
    def generateMatrix(self, n):
		"""
		:type n: int
		:rtype: List[List[int]]
		"""
		spiral_matrix = [[0 for i in xrange(n)] for i in xrange(n)]

		cnt = 1
		r = 0
		c = -1
		while cnt <= n * n:
			# right
			c += 1
			while c < n and spiral_matrix[r][c] == 0:
				spiral_matrix[r][c] = cnt
				c += 1
				cnt += 1
			c -= 1

			# down
			r += 1
			while r < n and spiral_matrix[r][c] == 0:
				spiral_matrix[r][c] = cnt
				r += 1
				cnt += 1
			r -= 1

			# left
			c -= 1
			while c >= 0 and spiral_matrix[r][c] == 0:
				spiral_matrix[r][c] = cnt
				c -= 1
				cnt += 1
			c += 1

			# up
			r -= 1
			while r >= 0 and spiral_matrix[r][c] == 0:
				spiral_matrix[r][c] = cnt
				r -= 1
				cnt += 1
			r += 1

			
			

		return spiral_matrix
        
