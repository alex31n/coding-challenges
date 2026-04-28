# Find Minimum in Rotated Sorted Array

**Difficulty:** `Medium`

**Topics :** `Array` `Binary Search`

**Source:** [LeetCode - Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)

---

Suppose an array of length `n` sorted in ascending order is rotated between `1` and `n` times. For example, the array `nums = [0,1,2,4,5,6,7]` might become:

- `[4,5,6,7,0,1,2]` if it was rotated `4` times.
- `[0,1,2,4,5,6,7]` if it was rotated `7` times.  

Notice that rotating an array `[a[0], a[1], a[2], ..., a[n-1]]` 1 time results in the array `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]`.

Given the sorted rotated array `nums` of unique elements, return the minimum element of this array.

You must write an algorithm that runs in `O(log n) time`.

 
**Example 1:**  
> Input: `nums = [3,4,5,1,2]`  
> Output: `1`  
> Explanation: `The original array was [1,2,3,4,5] rotated 3 times.`  

**Example 2:**  
> Input: `nums = [4,5,6,7,0,1,2]`  
> Output: `0`  
> Explanation: `The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.`  

**Example 3:**  
> Input: `nums = [11,13,15,17]`  
> Output: `11`  
> Explanation: `The original array was [11,13,15,17] and it was rotated 4 times.`  
 

**Constraints:**
- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- All the integers of `nums` are unique.
- `nums` is sorted and rotated between `1` and `n` times.


## Solution
The problem provides an array of length n that was originally sorted in ascending order but has been rotated between 1 and n times. For example, `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`. Our task is to find the minimum element in this array.  
**Goal:** Identify the smallest value in the rotated sorted array in $O(\log n)$ time complexity.

### 1. Optimized Binary Search
To meet the $O(\log n)$ requirement, we must use Binary Search. We leverage the fact that even in a rotated array, one half of the array will always be "normally" sorted.

**Business Logic:**
- Use a two-pointer approach (`l` and `r`) to maintain a search window. In each iteration, we halve the window size by comparing the middle element to the boundaries.
- Within the while loop, first check if `nums[l] < nums[r]`. If this condition is met, the current range is already sorted (non-rotated), allowing for an immediate return of `nums[l]`.
- Calculate the middle as `mid`
- Pivot Location:
  - If `nums[mid] > nums[r]`, the midpoint is part of the "left" larger sorted sequence, and the drop to the minimum must occur to the right. We move the left pointer to `mid + 1`.
  - If `nums[mid] <= nums[r]`, the minimum is either the `mid` element itself or located in the left half. We move the right pointer to `mid`.
- The loop terminates when `l == r`, at which point both pointers converge on the minimum element.


### Complexity
- Time Complexity: $O(\log n)$ — The search space is halved in each step.
- Space Complexity: $O(1)$ — Only constant extra space is used for pointers.
  

### Implementation
Python
```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        
        while l < r:
            # Range is already sorted
            if nums[l] < nums[r]:
                return nums[l]
            
            mid = (l + r) // 2
            
            if nums[mid] > nums[r]:
                l = mid + 1
            else:
                r = mid
                
        return nums[l]
```
Java
```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        
        while (l < r) {
            // Range is already sorted
            if (nums[l] < nums[r]) {
                return nums[l];
            }
            
            int mid = l + (r - l) / 2;
            
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return nums[l];
    }
}
```

### 2. Linear Search (Educational Only)
> **Note:** This solution is not allowed for this specific LeetCode problem because it does not meet the $O(\log n)$ time complexity requirement. It is included here for educational comparison only.

**Business Logic:**
- Iterate through every element of the array from index `0` to `n-1`.
- Maintain a variable to store the smallest value encountered so far, updating it whenever a smaller element is found.
- In a rotated sorted array, the first time `nums[i] < nums[i-1]`, you have found the minimum.

### Complexity
- Time Complexity: $O(n)$ — In the worst case, every element must be checked.
- Space Complexity: $O(1)$ — No extra space is used regardless of input size.

### Implementation
Python
```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        res = nums[0]
        for n in nums:
            if n < res:
                res = n
        return res
```
Java
```java
class Solution {
    public int findMin(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }
        return minVal;
    }
}
```


## Comparison
| Approach      | Time Complexity | Space Complexity | Status              |
| ------------- | --------------- | ---------------- | ------------------- |
| Binary Search | $O(\log n)$     | $O(1)$           | Accepted            |
| Linear Search | $O(n)$          | $O(1)$           | Rejected (Too Slow) |