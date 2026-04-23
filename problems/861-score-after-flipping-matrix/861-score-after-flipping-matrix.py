import math
from typing import List


class Solution(object):
    def matrixScore(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])

        # Step1: flip all rows that is starting with a ZERO
        for r in range(rows):
            if grid[r][0] == 0:
                for c in range(cols):
                    grid[r][c] = 0 if grid[r][c] else 1

        # Step 2: flip all columns where the number of ONE is less than the number of ZERO
        for c in range(1, cols):
            one_count = 0
            for r in range(rows):
                one_count += grid[r][c]

            if one_count * 2 < rows:
                for r in range(rows):
                    grid[r][c] = 0 if grid[r][c] else 1

        # Step 3: Calculate the total score
        result = 0
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    result += math.pow(2, (cols - c - 1))
                # result += grid[r][c] << (cols-c-1)

        return int(result)
