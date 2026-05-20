# 71. Simplify Path

**Difficulty:** `Medium`

**Topics :** `String` `Stack`

**Source:** [LeetCode - Simplify Path](https://leetcode.com/problems/simplify-path)

---

You are given an *absolute path* for a Unix-style file system, which always begins with a slash `'/'`. Your task is to transform this absolute path into its simplified **canonical path**.

The rules of a Unix-style file system are as follows:
- A single period `'.'` represents the current directory.
- A double period `'..'` represents the previous/parent directory.
- Multiple consecutive slashes such as `'//'` and `'///'` are treated as a single slash `'/'`.
- Any sequence of periods that does **not match** the rules above should be treated as a **valid directory or file name**. For example, `'...'` and `'....'` are valid directory or file names.

The simplified canonical path should follow these rules:
- The path must start with a single slash `'/'`.
- Directories within the path must be separated by exactly one slash `'/'`.
- The path must not end with a slash `'/'`, unless it is the root directory.
- The path must not have any single or double periods (`'.'` and `'..'`) used to denote current or parent directories.

Return the simplified canonical path.

**Example 1:**  
> Input: `path = "/home/"`  
> Output: `"/home"`  
> Explanation: `The trailing slash should be removed.`

**Example 2:**  
> Input: `path = "/home//foo/"`  
> Output: `"/home/foo"`  
> Explanation: `Multiple consecutive slashes are replaced by a single one.`

**Example 3:**  
> Input: `path = "/home/user/Documents/../Pictures"`  
> Output: `"/home/user/Pictures"`  
> Explanation: `A double period ".." refers to the directory up a level (the parent directory).`

**Example 4:**  
> Input: `path = "/../"`  
> Output: `"/"`  
> Explanation: `Going one level up from the root directory is not possible.`

**Example 5:**  
> Input: `path = "/.../a/../b/c/../d/./"`  
> Output: `"/.../b/d"`  
> Explanation: `"..." is a valid name for a directory in this problem.`

**Constraints:**
- `1 <= path.length <= 3000`
- `path` consists of English letters, digits, period `'.'`, slash `'/'` or `'_'`.
- `path` is a valid absolute Unix path.

## Solution
The problem asks us to evaluate a Unix-style file path and resolve all current directory `.` and parent directory `..` references to produce the shortest, absolute (canonical) path. 

**Goal:** Process the directory instructions left-to-right to compute the final absolute path.

### 1. Stack (Optimal)
Because paths are processed hierarchically and `..` commands us to go "back" or "up" one level, the Last-In-First-Out (LIFO) property of a Stack perfectly matches our needs.

**Business Logic:**
- Split the input `path` by the slash `/` character. This automatically handles multiple consecutive slashes (which become empty strings in the resulting array).
- Initialize an empty `stack`.
- Iterate through each directory/component in the split path:
  - If the component is `..`, it means go up one directory. We pop the top element from the stack (if the stack is not empty).
  - If the component is `.` or an empty string `""`, it means stay in the current directory or it's a redundant slash. We ignore it.
  - Otherwise, it is a valid directory name. We push it onto the stack.
- Finally, join the elements in the stack with a `/` and prepend a `/` to form the valid absolute root path.

### Complexity
- Time Complexity: $O(n)$ — Splitting the string takes $O(n)$ time. Iterating through the parts and pushing/popping from the stack takes $O(n)$ time. Joining the result takes $O(n)$. Total time scales linearly with the input size.
- Space Complexity: $O(n)$ — The array created by splitting the string and the stack can take up to $O(n)$ space in the worst case (e.g., a path with no `.` or `..`).

### Implementation
Python
```python
class Solution:
    def simplifyPath(self, path: str) -> str:
        stack = []
        
        # Split by '/' handles multiple slashes naturally
        for part in path.split('/'):
            if part == "..":
                if stack:
                    stack.pop()
            elif part and part != ".":
                # 'part' checks for non-empty strings (created by '//')
                stack.append(part)
                
        return "/" + "/".join(stack)
```
Java
```java
import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        
        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.push(part);
            }
        }
        
        // Java's String.join is an elegant way to combine stack elements
        return "/" + String.join("/", stack);
    }
}
```
