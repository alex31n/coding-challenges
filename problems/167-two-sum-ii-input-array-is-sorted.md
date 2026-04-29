# Two Sum II - Input Array Is Sorted

**Difficulty:** `Medium`

**Topics :** `Array` `Two Pointers` `Binary Search`

**Source:** [LeetCode - Two Sum II](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

---

Given a **1-indexed** array of integers `numbers` that is already **sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number. Let these two numbers be `numbers[index1]` and `numbers[index2]` where `1 <= index1 < index2 <= numbers.length`.

Return the indices of the two numbers, `index1` and `index2`, as an integer array `[index1, index2]` of length 2.

The tests are generated such that there is **exactly one solution**. You **may not** use the same element twice.

Your solution must use only constant extra space.

**Example 1:**
> Input: `numbers = [2,7,11,15], target = 9`
> Output: `[1,2]`
> Explanation: `The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2. We return [1, 2].`

**Example 2:**
> Input: `numbers = [2,3,4], target = 6`
> Output: `[1,3]`
> Explanation: `The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].`

**Example 3:**
> Input: `numbers = [-1,0], target = -1`
> Output: `[1,2]`
> Explanation: `The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].`
 
**Constraints:**
- `2 <= numbers.length <= 3 * 10^4`
- `-1000 <= numbers[i] <= 1000`
- `numbers` is sorted in **non-decreasing order**.
- `-1000 <= target <= 1000`
- The tests are generated such that there is **exactly one solution**.

## Solution
The problem asks us to find two numbers in a **1-indexed sorted array** (numbered starting from 1) that add up to a given target. We must return their positions as 1-indexed numbers. Importantly, we can only use a constant amount of extra space.

**Goal:** Find two positions, `index1` and `index2` (both starting from 1), in the sorted array `numbers` where `numbers[index1-1] + numbers[index2-1] == target`, and `1 <= index1 < index2 <= numbers.length`. Do this using only O(1) extra space.

### 1. Two Pointers (Optimal)
This approach leverages the sorted nature of the array and the constant space requirement.

**Business Logic:**
- Initialize two pointers, `left` at the beginning of the array (index 0) and `right` at the end of the array (index `len(numbers) - 1`).
- While `left` is less than `right`:
 - Calculate the `current_sum` of `numbers[left]` and `numbers[right]`.
 - If `current_sum` equals `target`, we have found our pair. Return `[left + 1, right + 1]` (since the problem requires 1-indexed output).
 - If `current_sum` is less than `target`, it means we need a larger sum. Since the array is sorted, increment `left` to consider a larger number.
 - If `current_sum` is greater than `target`, it means we need a smaller sum. Decrement `right` to consider a smaller number.
- The loop is guaranteed to find a solution because the problem states there is exactly one solution.

### Complexity
- Time Complexity: $O(n)$ — The pointers traverse the array at most once.
- Space Complexity: $O(1)$ — Only a few variables are used for pointers and sum.

### Implementation
Python
```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        l, r = 0, len(numbers) - 1
        
        while l < r:
            cur_sum = numbers[l] + numbers[r]
            
            if cur_sum == target:
                return [l + 1, r + 1]
            elif cur_sum < target:
                l += 1
            else: # cur_sum > target
                r -= 1
        
        # As per constraints, there's always exactly one solution,
        # so this part should ideally not be reached.
        return [] 
```
Java
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        
        while (l < r) {
            int curSum = numbers[l] + numbers[r];
            
            if (curSum == target) {
                return new int[] {l + 1, r + 1};
            } else if (curSum < target) {
                l++;
            } else { // curSum > target
                r--;
            }
        }
        
        // As per constraints, there's always exactly one solution,
        // so this part should ideally not be reached.
        return new int[] {};
    }
}
```

### 2. Hash Map (Not optimal for space, but a common approach for Two Sum)
This approach is generally used for unsorted arrays or when constant space is not a strict requirement. It does not meet the $O(1)$ space constraint of this specific problem.

**Business Logic:**
- Initialize a hash map to store numbers and their 0-indexed positions.
- Iterate through the array with an index `i` and value `num`.
- Calculate the `complement` needed: `target - num`.
- Check if the `complement` exists in the hash map.
 - If it does, we've found the pair. Return `[map.get(complement) + 1, i + 1]`.
- If the `complement` is not in the map, add the current `num` and its 0-indexed position `i` to the map.

### Complexity
- Time Complexity: $O(n)$ — Single pass through the array, with average $O(1)$ hash map operations.
- Space Complexity: $O(n)$ — In the worst case, the hash map might store all `n` elements.

### Implementation
Python
```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        hmap = {} # value -> index
        
        for i, num in enumerate(numbers):
            comp = target - num
            if comp in hmap:
                return [hmap[comp] + 1, i + 1]
            hmap[num] = i
        
        return []
```
Java
```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> hmap = new HashMap<>(); // value -> index
        
        for (int i = 0; i < numbers.length; i++) {
            int comp = target - numbers[i];
            if (hmap.containsKey(comp)) {
                return new int[] {hmap.get(comp) + 1, i + 1};
            }
            hmap.put(numbers[i], i);
        }
        
        return new int[] {};
    }
}
```

### 3. Brute Force (Educational Only)
This approach checks every possible pair of numbers. It does not meet the time complexity requirements for larger inputs.

**Business Logic:**
- Use nested loops. The outer loop iterates from `i = 0` to `n-1`.
- The inner loop iterates from `j = i + 1` to `n-1`.
- For each pair `(numbers[i], numbers[j])`, check if their sum equals `target`.
- If it does, return `[i + 1, j + 1]`.

### Complexity
- Time Complexity: $O(n^2)$ — Due to nested loops, checking all possible pairs.
- Space Complexity: $O(1)$ — No extra data structures are used.

### Implementation
Python
```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        n = len(numbers)
        for i in range(n):
            for j in range(i + 1, n):
                if numbers[i] + numbers[j] == target:
                    return [i + 1, j + 1]
        return []
```
Java
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[] {i + 1, j + 1};
                }
            }
        }
        return new int[] {};
    }
}
```

## Comparison
| Approach     | Time Complexity | Space Complexity | Notes                                                        |
| ------------ | --------------- | ---------------- | ------------------------------------------------------------ |
| Two Pointers | $O(n)$          | $O(1)$           | Optimal for sorted arrays and constant space requirement.    |
| Hash Map     | $O(n)$          | $O(n)$           | Efficient time-wise, but violates constant space constraint. |
| Brute Force  | $O(n^2)$        | $O(1)$           | Simple but too slow for larger inputs.                       |
