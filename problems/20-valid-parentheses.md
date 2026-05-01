# 20. Valid Parentheses

**Difficulty:** `Easy`

**Topics :** `String` `Stack`

**Source:** [LeetCode - Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

---

Given a string `s` containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:**  
> Input: `s = "()"`  
> Output: `true`  

**Example 2:**  
> Input: `s = "()[]{}"`  
> Output: `true`  

**Example 3:**  
> Input: `s = "(]"`  
> Output: `false`  

**Constraints:**
- <code>1 <= s.length <= 10<sup>4</sup></code>
- `s` consists of parentheses only `'()[]{}'`.

## Solution
The problem requires us to validate a string of parentheses, ensuring that every opening bracket is correctly closed in the right order. This "last in, first out" behavior is a classic sign that a stack data structure is the ideal tool for the job.

**Goal:** Determine if the input string `s` has valid parentheses.

### 1. Stack-Based Approach (Optimal)
This approach uses a stack to keep track of the opening brackets we've encountered. When we see a closing bracket, we check if it matches the most recently opened one.

**Business Logic:**
- As a quick optimization, if the string has an odd number of characters, it's impossible for all brackets to be paired, so we can return `false` immediately.
- Create a hash map to store the matching pairs, e.g., `map = {')': '(', '}': '{', ']': '['}`. This allows for an O(1) lookup.
- Initialize an empty stack to keep track of opening brackets.
- Iterate through each character `c` in the input string `s`:
  - If `c` is a closing bracket (i.e., it's a key in our hash map):
    - We must have a matching opening bracket at the top of the stack.
    - If the stack is empty, or if we pop the top element and it doesn't match the required opening bracket for `c`, the string is invalid. Return `false`.
  - If `c` is an opening bracket, push it onto the stack.
- After the loop, if the stack is empty, it means every opening bracket was correctly closed. If it's not empty, there are unclosed brackets, making the string invalid.

### Complexity
- Time Complexity: $O(n)$ — We iterate through the string just once.
- Space Complexity: $O(n)$ — In the worst-case scenario (e.g., a string like `((((...))))`), the stack could hold up to `n/2` characters. In the absolute worst case of all opening brackets `((((`, it would hold `n` characters.

### Implementation
Python
```python
class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 2 != 0:
            return False

        stack = []
        closeToOpen = {")": "(", "]": "[", "}": "{"}

        for c in s:
            if c in closeToOpen:
                if not stack or stack.pop() != closeToOpen[c]:
                    return False
            else:
                stack.append(c)
        
        return not stack
```
Java
```java
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        for (char c : s.toCharArray()) {
            if (closeToOpen.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != closeToOpen.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
```

### 2. Iterative String Replacement (Less Optimal)
This approach avoids using an explicit stack and instead relies on repeatedly removing valid pairs of parentheses from the string. If the string is eventually empty, it means all parentheses were correctly matched.

**Business Logic:**
- Start with an initial check: if the string's length is odd, it can't be valid, so return `false`.
- Enter a loop that continues as long as we can shorten the string.
- Inside the loop, use string replacement to remove all occurrences of `"()"`, `"[]"`, and `"{}"`.
- If the length of the string doesn't change after a full pass of replacements, it means no more valid pairs can be removed, so we can exit the loop.
- After the loop, if the string is empty, the original input was valid. Otherwise, it was invalid.

### Complexity
- Time Complexity: $O(n^2)$. In the worst case (e.g., `((()))`), we might have to scan the string multiple times. Each replacement operation can take $O(n)$ time.
- Space Complexity: $O(n)$. Each replacement can create a new string, using space proportional to the string's length.

### Implementation
Python
```python
class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 2 != 0:
            return False
        
        prev_len = -1
        while prev_len != len(s):
            prev_len = len(s)
            s = s.replace("()", "").replace("[]", "").replace("{}", "")
            
        return len(s) == 0
```
Java
```java
class Solution {
    public boolean isValid(String s) {
        int prevLength = -1;
        while (s.length() != prevLength) {
            prevLength = s.length();
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        }
        return s.isEmpty();
    }
}
```

## Comparison
| Approach                     | Time Complexity | Space Complexity | Notes                                                        |
| ---------------------------- | --------------- | ---------------- | ------------------------------------------------------------ |
| Stack-Based                  | $O(n)$          | $O(n)$           | Optimal and most efficient. The standard interview solution. |
| Iterative String Replacement | $O(n^2)$        | $O(n)$           | Intuitive but much slower due to repeated string operations. |
```
