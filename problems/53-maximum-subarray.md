# 53. Maximum Subarray

**Difficulty:** `Medium`  

**Topics :** `Array` `Divide and Conquer` `Dynamic Programming`

**Source:** [LeetCode](https://leetcode.com/problems/maximum-subarray/)

---

Given an integer array nums, find the subarray with the largest sum, and return its sum.

**Example 1:**
Input: `nums = [-2,1,-3,4,-1,2,1,-5,4]`  
Output: `6`  
Explanation: `The subarray [4,-1,2,1] has the largest sum 6.`  

**Example 2:**  
Input: `nums = [1]`  
Output: `1`  
Explanation: `The subarray [1] has the largest sum 1.`  

**Example 3:**
Input: `nums = [5,4,-1,7,8]`  
Output: `23`  
Explanation: `The subarray [5,4,-1,7,8] has the largest sum 23.`  
 

**Constraints:**  
`1 <= nums.length <= 105`  
`-104 <= nums[i] <= 104`  
 
**Follow up:** If you have figured out the $O(n)$ solution, try coding another solution using the divide and conquer approach, which is more subtle.

## Solution
The problem asks us to find a contiguous subarray within a one-dimensional array of numbers which has the largest sum. A subarray is a slice of the original array that maintains the order of elements and has no gaps.

**Goal:** Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its value.


### Brute Force ($O(n^3)$)
- **Three-Layer Approach:** The algorithm uses three nested loops to exhaustively check every possibility.
- **Identify Bounds:** The first two loops define the start and end boundaries of every possible subarray.
- **Calculate Sum:** The third (innermost) loop iterates from start to end to calculate the sum of that specific subarray.
- **Global Maximum:** If the calculated sum is greater than any seen before, it is stored as the result.

### Complexity
- Time Complexity: $O(n^3)$ — Due to three nested loops.  
- Space Complexity: $O(1)$ — Only a few variables are stored.  
 

### Implementation
Python
```python
def maxSubArray(nums: list[int]) -> int:
    max_sum = float('-inf')
    n = len(nums)
    
    for i in range(n):              # Start index
        for j in range(i, n):       # End index
            current_sum = 0
            for k in range(i, j + 1): # Summing loop
                current_sum += nums[k]
            max_sum = max(max_sum, current_sum)
            
    return max_sum
```
Java
```java
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {              // Start index
            for (int j = i; j < n; j++) {          // End index
                int currentSum = 0;
                for (int k = i; k <= j; k++) {     // Summing loop
                    currentSum += nums[k];
                }
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
```

### 2. Optimized Brute Force ($O(n^2)$)
- **Eliminate Redundancy:** Instead of re-calculating the sum from scratch in a third loop, we notice that the sum of `nums[i...j]` is just the sum of `nums[i...j-1]` plus `nums[j]`.
- **Cumulative Sum:** We maintain a running `current_sum` as the inner loop moves the `end` boundary forward.
- **Efficiency Gain:** By removing the innermost loop, we reduce the complexity by an entire factor of $n$.

### Complexity
- Time Complexity: $O(n^2)$
- Space Complexity: $O(1)$

### Implementation
Python
```python
def maxSubArray(nums: list[int]) -> int:
    max_sum = float('-inf')
    n = len(nums)
    
    for i in range(n):
        current_sum = 0
        for j in range(i, n):
            current_sum += nums[j]
            max_sum = max(max_sum, current_sum)
            
    return max_sum
```
Java
```java
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
```

### 3. Kadane’s Algorithm ($O(n)$)
- **Single Pass:** The algorithm iterates through the array exactly once, resulting in linear time complexity.
- **Reset Rule:** Before adding the current element, we check if the existing `curr_sum` is negative. If it is, we reset `curr_sum` to `0` because adding a negative prefix would only decrease the potential maximum of any future subarray.
- **Cumulative Addition:** We add the current element `i` to the `curr_sum`.
- **Global Tracking:** After each addition, we compare `curr_sum` with `max_sum`. If the current streak is the highest we've seen so far, we update `max_sum`.

### Complexity
- Time Complexity: $O(n)$
- Space Complexity: $O(1)$

### Implementation
Python
```python
def maxSubArray(nums: List[int]) -> int:
    curr_sum = 0
    max_sum = nums[0]

    for i in nums:
        # If currentSum is a negative, reset to 0
        if curr_sum < 0:
            curr_sum = 0

        curr_sum += i
        max_sum = max(max_sum, curr_sum)

    return max_sum

```
Java
```java
public class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = 0;
        int maxSum = nums[0];

        for (int i : nums) {
            // If currentSum is a negative, reset to 0
            if (currentSum < 0) {
                currentSum = 0;
            }
            currentSum += i;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
```

## Comparison
 | Approach              | Time Complexity | Space Complexity | Best For...                      |
 | --------------------- | --------------- | ---------------- | -------------------------------- |
 | Brute Force           | $O(n^3)$        | $O(1)$           | Beginners learning nested loops. |
 | Optimized Brute Force | $O(n^2)$        | $O(1)$           | Small datasets where $n < 1000$. |
 | Kadane’s Algorithm    | $O(n)$          | $O(1)$           | Production code and interviews.  |