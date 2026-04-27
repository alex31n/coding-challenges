# 152. Maximum Product Subarray

**Difficulty:** `Medium`

**Topics :** `Array` `Dynamic Programming`

**Source:** [LeetCode - Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

---

Given an integer array `nums`, find a subarray that has the largest product, and return the product. 
A subarray is a contiguous non-empty sequence of elements within an array.   
The test cases are generated so that the answer will fit in a **32-bit** integer.  
Note that the product of an array with a single element is the value of that element.  
   
**Example 1:**  
> Input: `nums = [2,3,-2,4]`  
> Output: `6`  
> Explanation: `[2,3] has the largest product 6.` 

**Example 2:**  
> Input: `nums = [-2,0,-1]`  
> Output: `0`  
> Explanation: `The result cannot be 2, because [-2,-1] is not a subarray.`  
 
**Constraints:**  
- `1 <= nums.length <= 2 * 104`
- `-10 <= nums[i] <= 10`
- The product of any subarray of `nums` is `guaranteed` to fit in a **32-bit** integer.


## Solution
The problem asks us to find a contiguous subarray within an integer array that has the largest product. Unlike the Maximum Sum problem, a product can involve negative numbers that "flip" the sign (negative $\times$ negative = positive), and zeros that reset the product.

**Goal:** Calculate the highest possible product of any contiguous subarray.

### 1. Brute Force (Iterative)
- Iterate through every possible starting index `i`.
- For each start, iterate through every possible ending index `j`.
- Calculate the product of the subarray from `i` to `j`.
- Keep track of the global maximum.

### Complexity
Time Complexity:  $O(n^2)$ – Two nested loops to check all pairs.  
Space Complexity: $O(1)$ – Only using a few variables.  

### Implementation
Python
```python
def maxProduct(nums):
    res = nums[0]
    for i in range(len(nums)):
        cur = 1
        for j in range(i, len(nums)):
            cur *= nums[j]
            res = max(res, cur)
    return res
```
Java
```java
public int maxProduct(int[] nums) {
    int res = nums[0];
    for (int i = 0; i < nums.length; i++) {
        int cur = 1;
        for (int j = i; j < nums.length; j++) {
            cur *= nums[j];
            res = Math.max(res, cur);
        }
    }
    return res;
}
```

### 2. Prefix and Suffix Product
- If there are no zeros, the maximum product is either the product of the whole array, or a prefix/suffix (up to the first or last negative number).
- We traverse from left to right to calculate the prefix product.
- We traverse from right to left to calculate the suffix product.
- If a $0$ is encountered, we reset the current product to 1 (treating the $0$ as a boundary).


### Complexity
Time Complexity: $O(n)$ – Two linear passes.  
Space Complexity: $O(1)$ – No extra data structures.  

### Implementation
Python
```python
def maxProduct(nums):
    n = len(nums)
    res = nums[0]
    prefix = 0
    suffix = 0
    
    for i in range(n):
        # Reset to 1 if we hit a zero, otherwise continue multiplying
        prefix = (prefix or 1) * nums[i]
        suffix = (suffix or 1) * nums[n - 1 - i]
        res = max(res, prefix, suffix)
    return res
```
Java
```java
public int maxProduct(int[] nums) {
    int n = nums.length;
    double res = nums[0];
    double left = 0, right = 0;
    
    for (int i = 0; i < n; i++) {
        // Reset to 1 if we hit a zero, otherwise continue multiplying
        left = (left == 0 ? 1 : left) * nums[i];
        right = (right == 0 ? 1 : right) * nums[n - 1 - i];
        res = Math.max(res, Math.max(left, right));
    }
    return (int)res;
}
```

### 3. Dynamic Programming (Min-Max Tracking)
- We maintain two variables: `cur_max` and `cur_min`.
- When we see a negative number, the `cur_max` becomes very small and `cur_min` becomes very large (negative).
- If we multiply by another negative, `cur_min * negative` becomes the new `cur_max`.
- At each step, we update:
  - `cur_max = max(num, num * cur_max, num * cur_min)`
  - `cur_min = min(num, num * cur_max, num * cur_min)`

### Complexity
Time Complexity: $O(n)$ – Single pass.  
Space Complexity: $O(1)$ – Only storing three variables.  

### Implementation
Python
```python
def maxProduct(nums):
    res = max(nums)
    cur_min, cur_max = 1, 1
    
    for n in nums:
        tmp = cur_max * n
        cur_max = max(n * cur_max, n * cur_min, n)
        cur_min = min(tmp, n * cur_min, n)
        res = max(res, cur_max)
    return res
```
Java
```java
public int maxProduct(int[] nums) {
    int res = nums[0];
    int curMax = 1, curMin = 1;
    
    for (int n : nums) {
        int tmp = curMax * n;
        curMax = Math.max(Math.max(n * curMax, n * curMin), n);
        curMin = Math.min(Math.min(tmp, n * curMin), n);
        res = Math.max(res, curMax);
    }
    return res;
}
```

## Comparison
| Approach            | Time Complexity | Space Complexity | Pros                 | Cons                                                |
| ------------------- | --------------- | ---------------- | -------------------- | --------------------------------------------------- |
| Brute Force         | $O(n^2)$        | $O(1)$           | Easy to write.       | TLE (Time Limit Exceeded), Fails on large datasets. |
| Prefix/Suffix       | $O(n)$          | $O(1)$           | Intuitive and clean. | Harder to explain the math.                         |
| Dynamic Programming | $O(n)$          | $O(1)$           | Industry standard.   | Requires careful tracking of Min/Max.               |