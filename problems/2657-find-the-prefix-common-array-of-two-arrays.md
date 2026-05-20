# 2657. Find the Prefix Common Array of Two Arrays

**Difficulty:** `Medium`

**Topics :** `Array` `Hash Table` `Bit Manipulation`

**Source:** [LeetCode - Find the Prefix Common Array of Two Arrays](https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/)

---

You are given two **0-indexed** integer permutations `A` and `B` of length `n`.

A **prefix common array** of `A` and `B` is an array `C` such that `C[i]` is equal to the count of numbers that are present at or before the index `i` in both `A` and `B`.

Return *the **prefix common array** of* `A` *and* `B`.

A sequence of `n` integers is called a **permutation** if it contains all integers from `1` to `n` exactly once.

**Example 1:**  
> Input: `A = [1,3,2,4], B = [3,1,2,4]`  
> Output: `[0,2,3,4]`  
> Explanation: `At i = 0: no number is common, so C[0] = 0.`  
> `At i = 1: 1 and 3 are common in A and B, so C[1] = 2.`  
> `At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.`  
> `At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.`

**Example 2:**  
> Input: `A = [2,3,1], B = [3,1,2]`  
> Output: `[0,1,3]`  
> Explanation: `At i = 0: no number is common, so C[0] = 0.`  
> `At i = 1: only 3 is common in A and B, so C[1] = 1.`  
> `At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.`  

**Constraints:**  
- `1 <= A.length == B.length == n <= 50`  
- `1 <= A[i], B[i] <= n`  
- `It is guaranteed that A and B are both a permutation of n integers.`  

## Solution
We are given two permutations of the same length `n`. For every index `i`, we need to determine how many elements from `A[0...i]` and `B[0...i]` intersect (are identical).

> [!NOTE]
> A permutation of length `n` means the array contains every number from `1` to `n` exactly once. There are no missing numbers and no duplicates.
> - **Valid example ($n=3$):** `[1, 3, 2]` or `[2, 1, 3]`
> - **Invalid example:** `[1, 2, 2]` (contains duplicate `2`, missing `3`) or `[1, 4, 3]` (contains `4`, missing `2`)

**Goal:** Calculate the running count of matching elements seen in both sequences up to the current index.

### 1. Frequency Array (Optimal)
Because the arrays consist of numbers specifically bounded from `1` to `n` and each number occurs exactly once in each array, we can use a frequency tally. We track how many times we've seen each number globally. If a number's count reaches 2, it means it has appeared in both `A` and `B`.

**Business Logic:**
- Initialize an array `freq` of size `n + 1` to track occurrences of each integer.
- Use a variable `common_count` to track the running total of matches.
- Loop through the arrays `A` and `B` simultaneously from index `0` to `n-1`.
- At step `i`, increment the count for `A[i]` in `freq`. If `freq[A[i]] == 2`, increment `common_count`.
- Next, increment the count for `B[i]` in `freq`. If `freq[B[i]] == 2`, increment `common_count`.
- Assign the running `common_count` to `C[i]`.

### Complexity
- Time Complexity: $O(n)$ — We process each element of the arrays exactly once.
- Space Complexity: $O(n)$ — The `freq` array uses space proportional to $n$. Output array isn't counted as extra space.

### Implementation
Python
```python
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        n = len(A)
        freq = [0] * (n + 1)
        C = [0] * n
        common_count = 0
        
        for i in range(n):
            # Process element from A
            freq[A[i]] += 1
            if freq[A[i]] == 2:
                common_count += 1
                
            # Process element from B
            freq[B[i]] += 1
            if freq[B[i]] == 2:
                common_count += 1
                
            C[i] = common_count
            
        return C
```
Java
```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int[] C = new int[n];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
            freq[A[i]]++;
            if (freq[A[i]] == 2) commonCount++;
            
            freq[B[i]]++;
            if (freq[B[i]] == 2) commonCount++;
            
            C[i] = commonCount;
        }
        return C;
    }
}
```

### 2. Bit Manipulation (Optimized Extra Space)
Because the length of `n` is strictly $\le 50$, we can cleverly exploit 64-bit integers to represent seen elements as bits. A specific bit set to `1` indicates that the corresponding number has been seen.

**Business Logic:**
- Initialize two 64-bit integers (e.g., `long`), `seenA` and `seenB`, to `0`.
- Loop through both arrays simultaneously. 
- Shift the bit for `A[i]` and add it to `seenA` using the Bitwise OR `|` operator.
- Do the same for `B[i]` into `seenB`.
- Use a Bitwise AND `&` to evaluate overlaps between `seenA` and `seenB`.
- Use a built-in bit count function (like `Long.bitCount` in Java or `.bit_count()` in Python) to count the number of matching elements.

### Complexity
- Time Complexity: $O(n)$
- Space Complexity: $O(1)$ — Only a couple of integer variables are used regardless of the input limit up to 50.

### Implementation
Python
```python
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        seenA = seenB = 0
        C = []
        
        for a, b in zip(A, B):
            seenA |= (1 << a)
            seenB |= (1 << b)
            C.append((seenA & seenB).bit_count())
            
        return C
```
Java
```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        long seenA = 0, seenB = 0;
        int[] C = new int[n];
        
        for (int i = 0; i < n; i++) {
            seenA |= (1L << A[i]);
            seenB |= (1L << B[i]);
            C[i] = Long.bitCount(seenA & seenB);
        }
        return C;
    }
}
```

## Comparison
| Approach         | Time Complexity | Space Complexity | Notes                                                             |
| ---------------- | --------------- | ---------------- | ----------------------------------------------------------------- |
| Frequency Array  | $O(n)$          | $O(n)$           | Simple, generic solution that works for all sizes of `n`.         |
| Bit Manipulation | $O(n)$          | $O(1)$           | Extremely fast and minimal space, but only works when $n \le 64$. |