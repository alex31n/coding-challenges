# 70. Climbing Stairs

**Difficulty:** `Easy`

**Topics :** `Math` `Dynamic Programming` `Memoization`

**Source:** [LeetCode - Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

---

You are climbing a staircase. It takes `n` steps to reach the top.

Each time you can either climb `1` or `2` steps. In how many distinct ways can you climb to the top?

**Example 1:**
> Input: `n = 2`  
> Output: `2`  
> Explanation: `There are two ways to climb to the top.`  
> `1. 1 step + 1 step`  
> `2. 2 steps`  

**Example 2:**
> Input: `n = 3`  
> Output: `3`  
> Explanation: `There are three ways to climb to the top.`  
> `1. 1 step + 1 step + 1 step`  
> `2. 1 step + 2 steps`  
> `3. 2 steps + 1 step`  

**Constraints:**
- `1 <= n <= 45`

## Solution

The problem asks for the number of distinct ways to climb a staircase of `n` steps, where on each move we can take either `1` step or `2` steps.

To reach the $i^{\text{th}}$ step, you can only arrive from:
1. The $(i - 1)^{\text{th}}$ step (by taking a 1-step leap)
2. The $(i - 2)^{\text{th}}$ step (by taking a 2-step leap)

Thus, the total number of distinct ways to reach step $i$ is the sum of the ways to reach step $i - 1$ and step $i - 2$:
$$f(i) = f(i - 1) + f(i - 2)$$

This is fundamentally identical to the Fibonacci sequence, with base cases:
- $f(1) = 1$ (only `[1]`)
- $f(2) = 2$ (either `[1, 1]` or `[2]`)

**Goal:** Calculate the total number of distinct ways to reach the $n^{\text{th}}$ step efficiently.

---

### 1. Space-Optimized Dynamic Programming (Optimal)

Since calculating the current step only requires knowing the results of the immediate previous two steps ($i - 1$ and $i - 2$), we do not need to keep an entire array of size $n + 1$. We can maintain just two variables (`prev2` and `prev1`) and update them iteratively as we move upward.

**Business Logic:**
- If $n \le 2$, return $n$ directly.
- Initialize `prev2 = 1` (ways to reach step 1) and `prev1 = 2` (ways to reach step 2).
- Loop from step $3$ up to $n$:
  - Calculate `curr = prev1 + prev2`.
  - Shift variables: `prev2 = prev1`, `prev1 = curr`.
- Return `prev1`.

### Complexity
- Time Complexity: $O(n)$ — A single linear scan from 3 to $n$.
- Space Complexity: $O(1)$ — Only two scalar variables to store the previous states.

### Implementation
Python
```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        prev2, prev1 = 1, 2

        for _ in range(3, n + 1):
            curr = prev1 + prev2
            prev2 = prev1
            prev1 = curr

        return prev1
```

Java
```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;

        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
```

---

### 2. Tabulation (Bottom-Up 1D DP)

In this approach, we create a 1D DP table where `dp[i]` represents the number of distinct ways to reach step `i`. We fill the table iteratively starting from our base cases up to `n`.

**Business Logic:**
- If $n \le 2$, return $n$.
- Allocate an array `dp` of size $n + 1$.
- Set base values: `dp[1] = 1`, `dp[2] = 2`.
- Iterate from $i = 3$ to $n$, filling `dp[i] = dp[i - 1] + dp[i - 2]`.
- Return `dp[n]`.

### Complexity
- Time Complexity: $O(n)$ — Single pass to populate the array.
- Space Complexity: $O(n)$ — An array of size $n + 1$ is allocated.

### Implementation
Python
```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        dp = [0] * (n + 1)
        dp[1] = 1
        dp[2] = 2

        for i in range(3, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]

        return dp[n]
```

Java
```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
```

---

### 3. Memoization (Top-Down DP)

Top-down DP defines the solution recursively using the state transition $\text{climbStairs}(n) = \text{climbStairs}(n - 1) + \text{climbStairs}(n - 2)$. To avoid exponential recomputation of identical subproblems, we cache the result of each step in a hash map or array.

**Business Logic:**
- If $n \le 2$, return $n$.
- Check if step $n$ is already calculated in `memo`. If yes, return the cached value.
- Otherwise, compute `memo[n] = climbStairs(n - 1) + climbStairs(n - 2)` and return it.

### Complexity
- Time Complexity: $O(n)$ — Each state from $1$ to $n$ is solved and cached exactly once.
- Space Complexity: $O(n)$ — For the recursion call stack and the memoization storage.

### Implementation
Python
```python
class Solution:
    def climbStairs(self, n: int) -> int:
        memo = {}

        def helper(i: int) -> int:
            if i <= 2:
                return i
            if i in memo:
                return memo[i]

            memo[i] = helper(i - 1) + helper(i - 2)
            return memo[i]

        return helper(n)
```

Java
```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);
        return result;
    }
}
```

---

### 4. Brute Force Recursion (Educational Only)

A naive recursive approach branches out into two recursive calls for every step: taking 1 step and taking 2 steps. Without caching, this generates a binary recursion tree with exponential redundant calculations.

**Business Logic:**
- Base case: If $n \le 2$, return $n$.
- Recurse: Return `climbStairs(n - 1) + climbStairs(n - 2)`.

### Complexity
- Time Complexity: $O(2^n)$ — Size of the recursion tree doubles at each level; will result in Time Limit Exceeded (TLE) for large $n$.
- Space Complexity: $O(n)$ — Maximum depth of the recursion tree is $n$.

### Implementation
Python
```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n
        return self.climbStairs(n - 1) + self.climbStairs(n - 2)
```

Java
```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

---

## Comparison

| Approach | Time Complexity | Space Complexity | Notes / Best For |
| :--- | :--- | :--- | :--- |
| **Space-Optimized DP (Optimal)** | $O(n)$ | $O(1)$ | Best for interviews & production; constant memory overhead. |
| **Tabulation (Bottom-Up 1D DP)** | $O(n)$ | $O(n)$ | Excellent for visualizing full DP table state progression. |
| **Memoization (Top-Down DP)** | $O(n)$ | $O(n)$ | Intuitive recursive translation with state caching. |
| **Brute Force Recursion** | $O(2^n)$ | $O(n)$ | Educational only; leads to TLE for large inputs. |
