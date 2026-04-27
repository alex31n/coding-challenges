# [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)

**Difficulty:** `Medium`

**Topics :** `Array` `Prefix Sum`

**Source:** [LeetCode](https://leetcode.com/problems/product-of-array-except-self/)

---

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of nums except `nums[i]`.

The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in $O(n)$ time and without using the division operation.

**Example 1:**  
> Input: `nums = [1,2,3,4]`  
> Output: `[24,12,8,6]`  

**Example 2:**  
> Input: `nums = [-1,1,0,-3,3]`  
> Output: `[0,0,9,0,0]`  
 
**Constraints:**  
- `2 <= nums.length <= 105`   
- `-30 <= nums[i] <= 30`  
- The input is generated such that `answer[i]` is **guaranteed** to fit in a 32-bit integer.
 

**Follow up:** Can you solve the problem in $O(1)$ extra space complexity? (The output array **does not** count as extra space for space complexity analysis.)

## Solution
The core objective is to transform an array such that each element at index `i` becomes the product of every other number in the array except itself.
- **Core Rule:** You cannot use the division operator.
- **Efficiency:** The solution must be linear, meaning it runs in $O(n)$ time.
- **Edge Case:** The logic must account for zeros. If there is one zero, only that index will have a non-zero product. If there are two or more zeros, every product in the result will be zero.


### 1. Division (Conceptual, Not Allowed by Constraints)
- Calculate the product of every number in the array (the "Grand Product").
- Iterate through the array a second time.
- For each index, divide the Grand Product by the current number to find the product of all other numbers.
- **Constraint Note:** This is prohibited by the problem but serves as a baseline. It also requires special handling to avoid "Division by Zero" errors.

### Complexity
Time Complexity: $O(n)$  
Space Complexity: $O(1)$ (excluding output array)  

### Implementation
Python
```python
def productExceptSelf(nums):
    total_prod = 1
    zeros = nums.count(0)
    
    if zeros > 1: return [0] * len(nums)
    
    for x in nums:
        if x != 0: total_prod *= x
        
    res = []
    for x in nums:
        if zeros == 1:
            res.append(total_prod if x == 0 else 0)
        else:
            res.append(total_prod // x)
    return res
```
Java
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    int totalProd = 1;
    int zeroCount = 0;

    for (int x : nums) {
        if (x == 0) zeroCount++;
        else totalProd *= x;
    }

    if (zeroCount > 1) return res; // All elements will be 0

    for (int i = 0; i < n; i++) {
        if (zeroCount == 1) {
            res[i] = (nums[i] == 0) ? totalProd : 0;
        } else {
            res[i] = totalProd / nums[i];
        }
    }
    return res;
}
```

### 2. Brute Force
- Use an outer loop to iterate through the array, selecting a **target** index `i` for which the product is being calculated.
- Use an another inner loop to iterate through every index `j` of the same array.
- Inside the inner loop, check if `i != j`. If the indices are different, include `nums[j]` in the running product.
- Once the inner loop finishes for a specific `i`, append the final product to the result list and move to the next `i`.

### Complexity
Time Complexity: $O(n^2)$  
Space Complexity: $O(1)$

### Implementation
Python
```python
def productExceptSelf(nums):
    res = []
    for i in range(len(nums)):
        curr_prod = 1
        for j in range(len(nums)):
            if i != j:
                curr_prod *= nums[j]
        res.append(curr_prod)
    return res
```
Java
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];

    for (int i = 0; i < n; i++) {
        int currProd = 1;
        for (int j = 0; j < n; j++) {
            if (i != j) {
                currProd *= nums[j];
            }
        }
        res[i] = currProd;
    }
    return res;
}
```

### 3. Prefix and Suffix Arrays
- Initialize two arrays: `prefix` and `suffix`.
- **Prefix Pass:** Move from left to right. Each position `i` stores the product of all elements to the left of `i`.
- **Suffix Pass:** Move from right to left. Each position `i` stores the product of all elements to the right of `i`.
- **Final Result:** Multiply `prefix[i]` by `suffix[i]` to get the product of everything except the current element.

### Complexity
Time Complexity: $O(n)$  
Space Complexity: $O(n)$

### Implementation
Python
```python
def productExceptSelf(nums):
    n = len(nums)
    prefix = [1] * n
    suffix = [1] * n
    
    for i in range(1, n):
        prefix[i] = prefix[i-1] * nums[i-1]
        
    for i in range(n-2, -1, -1):
        suffix[i] = suffix[i+1] * nums[i+1]
        
    return [prefix[i] * suffix[i] for i in range(n)]
```
Java
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n];
    int[] suffix = new int[n];
    int[] res = new int[n];

    prefix[0] = 1;
    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i - 1] * nums[i - 1];
    }

    suffix[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
        suffix[i] = suffix[i + 1] * nums[i + 1];
    }

    for (int i = 0; i < n; i++) {
        res[i] = prefix[i] * suffix[i];
    }
    return res;
}
```

### 4. Optimized Space (Single Pass)
- Use the final result array to first store the prefix products.
- Use a single variable (e.g., right_product) to keep track of the running suffix product as you iterate backward.
- During the backward pass, multiply the existing value in the result array (the prefix) by the current right_product (the suffix).
- This effectively combines the two auxiliary arrays into one without requiring extra memory.

### Complexity:
Time Complexity: $O(n)$  
Space Complexity: $O(1)$ (Note: The output array is generally not counted toward space complexity in competitive programming).  

### Implementation
Python
```python
def productExceptSelf(nums):
    n = len(nums)
    res = [1] * n
    
    # Prefix calculation
    for i in range(1, n):
        res[i] = res[i-1] * nums[i-1]
        
    # Suffix calculation on the fly
    right_product = 1
    for i in range(n-1, -1, -1):
        res[i] *= right_product
        right_product *= nums[i]
        
    return res
```
Java
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];

    // Prefix products
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }

    // Suffix products on the fly
    int rightProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= rightProduct;
        rightProduct *= nums[i];
    }

    return res;
}
```
## Comparison
| Method                 | Time Complexity | Space Complexity | Pros                                                       | Cons                                                             |
| ---------------------- | --------------- | ---------------- | ---------------------------------------------------------- | ---------------------------------------------------------------- |
| Division               | O(n)            | O(1)             | Mathematically simple and intuitive.                       | Prohibited by LeetCode; fails on zeros without extra logic.      |
| Brute Force            | O(n2)           | O(1)             | No extra logic or arrays needed; easy to implement.        | Extremely slow; will time out for large arrays (n>10,000).     |
| Prefix & Suffix Arrays | O(n)            | O(n)             | Very clear logic; easy to debug and explain.               | Uses additional memory for two auxiliary arrays.                 |
| Optimized Space        | O(n)            | O(1)             | Most efficient; meets all interview follow-up constraints. | Slightly more abstract logic; harder to visualize during coding. |