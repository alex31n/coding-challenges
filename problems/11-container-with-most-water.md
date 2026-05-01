# 11. Container With Most Water

**Difficulty:** `Medium`

**Topics :** `Array`, `Two Pointers`, `Greedy`

**Source:** [LeetCode - Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

---

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return *the maximum amount of water a container can store*.

**Notice** that you may not slant the container.


**Example 1:**  
![Container With Most Water](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)  
> Input: `height = [1,8,6,2,5,4,8,3,7]`  
> Output: `49`  
> Explanation: `The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.`  

**Example 2:**  
> Input: `height = [1,1]`  
> Output: `1`  

**Constraints:**  
- `n == height.length`  
- `2 <= n <= 10^5`  
- `0 <= height[i] <= 10^4`  

## Solution
The problem asks us to find two vertical lines in an array of heights that can hold the most water when forming a container with the x-axis. The amount of water is determined by the shorter of the two lines (the height) and the distance between them (the width).

**Goal:** Calculate the maximum possible area, where `area = min(height[i], height[j]) * (j - i)`.

### 1. Two Pointers (Optimal)
This is the most efficient approach. We start with the widest possible container and iteratively narrow it down, always keeping track of the maximum area found.

**Business Logic:**
- Initialize two pointers, `l` at the beginning of the array (index 0) and `r` at the end (index `n-1`).
- Initialize a variable `max_area` to 0.
- Loop as long as `l < r`:
    - Calculate the current area: `area = min(height[l], height[r]) * (r - l)`.
    - Update `max_area = max(max_area, area)`.
    - To potentially find a larger area, we need to move one of the pointers. If we move the pointer pointing to the taller line, the new height of the container will be limited by the shorter line, and the width will decrease, so the area can only decrease. Therefore, we should move the pointer pointing to the shorter line inward, in the hope of finding a taller line that could create a larger area.
    - If `height[l] < height[r]`, increment `l`.
    - Otherwise, decrement `r`.
- Return `max_area`.

### Complexity
- Time Complexity: $O(n)$ — Each pointer traverses the array at most once.
- Space Complexity: $O(1)$ — We only use a few variables to store pointers and the max area.

### Implementation
Python
```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        l, r = 0, len(height) - 1
        max_area = 0

        while l < r:
            area = min(height[l], height[r]) * (r - l)
            max_area = max(max_area, area)

            if height[l] < height[r]:
                l += 1
            else:
                r -= 1
        
        return max_area
```
Java
```java
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(maxArea, area);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        
        return maxArea;
    }
}
```

### 2. Brute Force (Educational Only)
This approach is straightforward but inefficient as it checks every possible pair of lines to find the one that forms the container with the maximum area.

**Business Logic:**
- Use a nested loop. The outer loop iterates through each line from the beginning (`i`).
- The inner loop iterates through the lines to the right of the current line (`j`).
- For each pair of lines `(i, j)`, calculate the area: `area = min(height[i], height[j]) * (j - i)`.
- Keep track of the maximum area found so far and update it if the current area is larger.

### Complexity
- Time Complexity: $O(n^2)$ — We check every possible pair of lines.
- Space Complexity: $O(1)$ — Only a few variables are used for tracking the maximum area.

### Implementation
Python
```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_area = 0
        n = len(height)
        for i in range(n):
            for j in range(i + 1, n):
                area = min(height[i], height[j]) * (j - i)
                max_area = max(max_area, area)
        return max_area
```
Java
```java
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
```


## Comparison
| Approach     | Time Complexity | Space Complexity | Notes                                                          |
| ------------ | --------------- | ---------------- | -------------------------------------------------------------- |
| Two Pointers | $O(n)$          | $O(1)$           | Optimal and efficient. The standard solution for this problem. |
| Brute Force  | $O(n^2)$        | $O(1)$           | Simple to understand but too slow for the given constraints.   |