# 739. Daily Temperatures

**Difficulty:** `Medium`

**Topics :** `Array` `Stack` `Monotonic Stack`

**Source:** [LeetCode - Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)

---

Given an array of integers `temperatures` represents the daily temperatures, return an array `answer` such that `answer[i]` is the number of days you have to wait after the <code>i<sup>th</sup></code> day to get a warmer temperature. If there is no future day for which this is possible, keep `answer[i] == 0` instead.

**Example 1:**
> Input: `temperatures = [73,74,75,71,69,72,76,73]`
> Output: `[1,1,4,2,1,1,0,0]`

**Example 2:**
> Input: `temperatures = [30,40,50,60]`
> Output: `[1,1,1,0]`

**Example 3:**
> Input: `temperatures = [30,60,90]`
> Output: `[1,1,0]`

**Constraints:**
- `1 <= temperatures.length <= 10^5`
- `30 <= temperatures[i] <= 100`

## Solution

We need to find the number of days to wait until a warmer temperature occurs for each day in the given `temperatures` array.

**Goal:** Calculate the difference in indices between the current day and the next day with a strictly higher temperature.

### 1. Monotonic Stack (Optimal)
We can use a decreasing monotonic stack to efficiently find the next warmer day. The stack will store the indices of the days for which we haven't found a warmer day yet. Since we are looking for the *next greater element*, a decreasing monotonic stack is a perfect fit.

**Business Logic:**
- Initialize an `answer` array of the same length as `temperatures` with zeros.
- Initialize an empty `stack` to keep track of the indices of temperatures.
- Iterate through the `temperatures` array using index `i` and value `t`:
    - While the stack is not empty and the current temperature `t` is strictly greater than the temperature at the index stored at the top of the stack (`temperatures[stack[-1]]`):
        - We have found a warmer day for the day at the top of the stack.
        - Pop the index from the stack. Let's call it `prev_index`.
        - The number of days to wait for the `prev_index` is `i - prev_index`. Update `answer[prev_index]` with this difference.
    - Push the current index `i` onto the stack.
- After the loop finishes, any indices remaining in the stack don't have a future warmer day, and their corresponding values in the `answer` array will remain `0` as initialized.

### Complexity
- Time Complexity: $O(n)$ where $n$ is the length of the `temperatures` array. Each element is pushed onto the stack exactly once and popped at most once. Thus, the inner while loop runs at most $n$ times overall across all iterations of the outer loop.
- Space Complexity: $O(n)$ in the worst case, the stack can hold all elements of the array if the temperatures are strictly decreasing (e.g., `[100, 90, 80, 70]`).

### Implementation
Python
```python
class Solution:
    def dailyTemperatures(self, temperatures: list[int]) -> list[int]:
        n = len(temperatures)
        answer = [0] * n
        stack = []  # stores indices

        for i, t in enumerate(temperatures):
            while stack and t > temperatures[stack[-1]]:
                prev_index = stack.pop()
                answer[prev_index] = i - prev_index
            stack.append(i)

        return answer
```
Java
```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices, Deque is faster than Stack

        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }

        return answer;
    }
}
```

### 2. Brute Force (Educational Only)
For each day, we can simply iterate through the remaining days to find the first day with a strictly higher temperature.

**Business Logic:**
- Iterate through each day `i` from `0` to `n-1`.
- For each day `i`, start an inner loop from `j = i + 1` to `n-1`.
- If `temperatures[j] > temperatures[i]`, we found the next warmer day.
- Set `answer[i] = j - i` and break out of the inner loop.
- If the inner loop finishes without finding a warmer day, `answer[i]` remains `0`.

### Complexity
- Time Complexity: $O(n^2)$ where $n$ is the length of the `temperatures` array. In the worst case (e.g., strictly decreasing array), the inner loop runs to the end of the array for every element.
- Space Complexity: $O(1)$ auxiliary space, as we don't use any extra data structures other than the output array.

### Implementation
Python
```python
class Solution:
    def dailyTemperatures(self, temperatures: list[int]) -> list[int]:
        n = len(temperatures)
        answer = [0] * n
        
        for i in range(n):
            for j in range(i + 1, n):
                if temperatures[j] > temperatures[i]:
                    answer[i] = j - i
                    break
                    
        return answer
```
Java
```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        
        return answer;
    }
}
```

## Comparison
| Approach        | Time Complexity | Space Complexity | Notes or Best For or any                                          |
| --------------- | --------------- | ---------------- | ----------------------------------------------------------------- |
| Monotonic Stack | $O(n)$          | $O(n)$           | Optimal approach using a stack to keep track of unresolved days.  |
| Brute Force     | $O(n^2)$        | $O(1)$           | Simple but results in Time Limit Exceeded (TLE) for large inputs. |
