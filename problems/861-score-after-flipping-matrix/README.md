# [861. Score After Flipping Matrix](https://leetcode.com/problems/score-after-flipping-matrix/)

**Difficulty:** `Medium`

**Topics :** `Array` `Greedy` `Bit Manipulation` `Matrix`

---

You are given an `m x n` binary matrix `grid`.

A **move** consists of choosing any row or column and toggling each value in that row or column (i.e., changing
all `0`'s to `1`'s, and all `1`'s to `0`'s).

Every row of the matrix is interpreted as a binary number, and the **score** of the matrix is the sum of these
numbers.

Return _the highest possible **score** after making any number of **moves** (including zero moves)_.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/07/23/lc-toogle1.jpg)

> **Input:** grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]  
> **Output:** 39  
> **Explanation:** 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

**Example 2:**

> **Input:** grid = [[0]]  
> **Output:** 1

**Constraints:**

- `m == grid.length`  
- `n == grid[i].length`  
- `1 <= m, n <= 20`  
- `grid[i][j]` is either `0` or `1`.


---

## Code

Java

```java []

class Solution {
    public int matrixScore(int[][] grid) {

        int rows = grid.length, cols = grid[0].length;

        // Step1: flip all rows that is starting with a zero
        for (int r = 0; r < rows; r++) {

            if (grid[r][0] == 0) {

                for (int c = 0; c < cols; c++) {
                    grid[r][c] = grid[r][c] == 1 ? 0 : 1;
                }
            }
        }

        // Step 2: flip all columns where the number of ONE is less than the number of ZERO
        for (int c = 1; c < cols; c++) {

            int oneCount = 0;

            for (int r = 0; r < rows; r++) {
                oneCount += grid[r][c];
            }

            if (oneCount * 2 < rows) {

                for (int r = 0; r < rows; r++) {
                    grid[r][c] = grid[r][c] == 1 ? 0 : 1;
                }
            }
        }

        // Step 3: Calculate the total score
        int result = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

//                if (grid[r][c]==1)
//                    result += (int) Math.pow(2, (cols-c-1));

                result += grid[r][c] << (cols - c - 1);
            }
        }

        return result;
    }
}
```

Python

``` Python []
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

```
