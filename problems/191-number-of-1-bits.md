# 191. Number of 1 Bits

**Difficulty:** `Easy`

**Topics :** `Divide and Conquer` `Bit Manipulation`

**Source:** [LeetCode - Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)

---

Given a positive integer n, write a function that returns the number of `set bits` in its binary representation (also known as the [Hamming weight](http://en.wikipedia.org/wiki/Hamming_weight)).

> [!NOTE]
> A set bit refers to a bit in the binary representation of a number that has a value of `1`.

**Example 1:**  
> Input: `n = 11`  
> Output: `3`  
> Explanation: `The input binary string 1011 has a total of three set bits.`

**Example 2:**
> Input: `n = 128`  
> Output: `1`  
> Explanation: `The input binary string 10000000 has a total of one set bit.`

**Example 3:**
> Input: `n = 2147483645`  
> Output: `30`  
> Explanation: `The input binary string 11111111111111111111111111111101 has a total of thirty set bits.`

**Constraints:**
- `1 <= n <= 2^31 - 1`

## Solution
The problem asks us to count the number of `1` bits (set bits) in the binary representation of a given integer.

**Goal:** Calculate the [Hamming weight](http://en.wikipedia.org/wiki/Hamming_weight) of the integer `n`.

### 1. Brian Kernighan's Algorithm (Optimal)
This is an elegant bit manipulation trick that allows us to count the set bits by skipping over the `0` bits entirely.

**Business Logic:**
- When you subtract `1` from an integer `n`, all the bits starting from the rightmost `1` to the end are flipped.
- If you perform a bitwise AND between `n` and `n - 1` (`n & (n - 1)`), the rightmost `1` bit in `n` gets turned into a `0`.
- We can repeatedly apply `n = n & (n - 1)` and increment a counter until `n` becomes `0`.
- This approach is highly efficient because the loop executes exactly as many times as there are `1` bits.

### Complexity
- Time Complexity: $O(1)$ — In the worst case (all 1s), the loop runs 32 times for a 32-bit integer. The runtime is strictly proportional to the number of `1` bits.
- Space Complexity: $O(1)$ — Only a single counter variable is needed.

### Implementation
Python
```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        count = 0
        while n:
            n &= (n - 1)
            count += 1
        return count
```
Java
```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
```

### 2. Bit Shift and Mask (Alternative)
A more straightforward approach is to inspect every bit of the number one by one.

**Business Logic:**
- We check the least significant bit (the rightmost bit) of `n` by performing a bitwise AND with `1` (`n & 1`). If the result is `1`, we increment our counter.
- We then shift `n` to the right by one position using the unsigned right shift operator. (In Java, this is `>>>` to avoid infinite loops with negative numbers; in Python `>>` works naturally for positive integers).
- We repeat this process exactly 32 times or until `n` becomes `0`.

### Complexity
- Time Complexity: $O(1)$ — The loop runs at most 32 times.
- Space Complexity: $O(1)$ — Constant space used.

### Implementation
Python
```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        count = 0
        while n:
            count += n & 1
            n >>= 1
        return count
```
Java
```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            // Unsigned right shift is critical in Java for negative numbers
            n >>>= 1;
        }
        return count;
    }
}
```

### 3. Arithmetic Approach (Modulo and Division)
A highly intuitive approach that avoids bitwise operators entirely by using standard division and modulo arithmetic.

**Business Logic:**
- In the binary (base-2) system, the last digit is `1` if the number is odd, and `0` if the number is even.
- We check if the last digit is `1` by checking the remainder of division by 2 (`n % 2 == 1`). If it is, we increment the count.
- To "shift" the number to the right and inspect the next digit, we perform integer division by 2 (`n / 2`).
- We repeat this loop until the number becomes `0`.

> [!NOTE]
> This method is the easiest to grasp for beginners because it directly mirrors the standard manual process of converting a decimal number to binary using successive division.
> **Note on Constraints:** This approach **will not work for negative numbers** (where `n < 0` or negative values in signed representation) because modulo and division behave differently for negative integers, and the loop checks for `n > 0`. However, under the problem constraints (`1 <= n <= 2^31 - 1`), the input `n` is guaranteed to always be positive.

### Complexity
- Time Complexity: $O(\log n)$ — The loop runs at most 31 times for a positive 32-bit integer.
- Space Complexity: $O(1)$ — Only a single counter variable is used.

### Implementation
Python
```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        count = 0
        while n > 0:
            if n % 2 == 1:
                count += 1
            n //= 2
        return count
```
Java
```java
class Solution {
    public int hammingWeight(int n) {

        int count = 0;

        while (n > 0) {
            if (n % 2 == 1)
                count++;

            n = n / 2;
        }

        return count;

    }
}
```

## Comparison
| Approach                    | Time Complexity | Space Complexity | Notes                                                                                                      |
| --------------------------- | --------------- | ---------------- | ---------------------------------------------------------------------------------------------------------- |
| Brian Kernighan's Algorithm | $O(1)$          | $O(1)$           | Optimal. Only loops for the number of `1` bits. Shows deep understanding of bit manipulation.              |
| Bit Shift and Mask          | $O(1)$          | $O(1)$           | Simple and intuitive. Loops over trailing zeros. Java requires `>>>` for unsigned shift to avoid timeouts. |
| Arithmetic (Modulo & Div)   | $O(\log n)$     | $O(1)$           | Easiest to understand. Avoids bitwise operators. Only works for positive numbers (safe under constraints). |