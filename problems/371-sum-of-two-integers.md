# 371. Sum of Two Integers

**Difficulty:** `Medium`

**Topics :** `Math` `Bit Manipulation`

**Source:** [LeetCode - Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers)

---

Given two integers `a` and `b`, return the sum of the two integers without using the operators `+` and `-`.

**Example 1:**  
> Input: `a = 1, b = 2`  
> Output: `3`  
> Explanation: `1 + 2 = 3`


**Example 2:**  
> Input: `a = 2, b = 3`  
> Output: `5`

**Constraints:**  
- `-1000 <= a, b <= 1000`

## Solution
The problem asks us to calculate the sum of two integers without using the standard addition `+` or subtraction `-` operators. This requires us to simulate the addition process using bitwise operations.

**Goal:** Simulate addition using bitwise XOR (`^`) and bitwise AND (`&`) operations.

### 1. Bit Manipulation (Optimal)
We can break down addition into two parts: the sum without carrying, and the carry itself.
- **XOR (`^`)** operation acts as addition without carry. For example, `1 ^ 1 = 0`, `1 ^ 0 = 1`, `0 ^ 0 = 0`.
- **AND (`&`)** operation finds the carry. A carry only occurs when both bits are `1` (`1 & 1 = 1`). We then left-shift (`<< 1`) the carry because it needs to be added to the next higher bit position.
- We repeat this process until there is no carry left.

**Business Logic:**
- Loop until the carry `b` becomes 0.
- Inside the loop, calculate the carry by performing `(a & b) << 1`.
- Calculate the sum without carry by performing `a ^ b`.
- Update `a` to the sum without carry, and `b` to the new carry.
- Note: In languages like Python where integers have arbitrary precision (not strictly 32-bit), we need to use a mask (`0xFFFFFFFF`) to simulate 32-bit integer overflow and handle negative numbers properly at the end. In Java, 32-bit integer overflow is handled naturally.

### Complexity
- Time Complexity: $O(1)$ — The loop runs at most 32 times because we are dealing with 32-bit integers.
- Space Complexity: $O(1)$ — Only a few variables are used.

### Implementation
Python
```python
class Solution:
    def getSum(self, a: int, b: int) -> int:
        # 32-bit bitmask
        mask = 0xFFFFFFFF
        
        # Max positive 32-bit integer (01111111 11111111 11111111 11111111)
        max_int = 0x7FFFFFFF
        
        # Mask b to ensure the loop processes negative values correctly from the start
        b &= mask

        while b != 0:
            # Apply the mask to BOTH a and b (carry) at each step
            carry = ((a & b) << 1) & mask
            a = (a ^ b) & mask
            b = carry
            
        # If 'a' is negative in 32-bit representation (starts with 1)
        # we need to convert it back to Python's arbitrary precision negative
        return a if a <= max_int else ~(a ^ mask)
```
Java
```java
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
```
