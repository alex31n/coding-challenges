# 150. Evaluate Reverse Polish Notation

**Difficulty:** `Medium`

**Topics :** `Array` `Math` `Stack`

**Source:** [LeetCode - Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

---

You are given an array of strings `tokens` that represents an arithmetic expression in a [Reverse Polish Notation (RPN)](http://en.wikipedia.org/wiki/Reverse_Polish_notation).

Evaluate the expression. Return *an integer that represents the value of the expression.*

**Note** that:
- The valid operators are `'+'`, `'-'`, `'*'`, and `'/'`.
- Each operand may be an integer or another expression.
- The division between two integers always **truncates toward zero**.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in a reverse polish notation.
- The answer and all the intermediate calculations can be represented in a **32-bit** integer.

**Example 1:**  
> Input: `tokens = ["2","1","+","3","*"]`  
> Output: `9`  
> Explanation: `((2 + 1) * 3) = 9`  

**Example 2:**  
> Input: `tokens = ["4","13","5","/","+"]`  
> Output: `6`  
> Explanation: `(4 + (13 / 5)) = 6`  

**Example 3:**  
> Input: `tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]`  
> Output: `22`  
> Explanation: `((10 * (6 / ((9 + 3) * -11))) + 17) + 5`  
> `= ((10 * (6 / (12 * -11))) + 17) + 5`  
> `= ((10 * (6 / -132)) + 17) + 5`  
> `= ((10 * 0) + 17) + 5`  
> `= (0 + 17) + 5`  
> `= 17 + 5`  
> `= 22`  

**Constraints:**  
- `1 <= tokens.length <= 10^4`  
- `tokens[i]` is either an operator: `"+"`, `"-"`, `"*"`, or `"/"`, or an integer in the range `[-200, 200]`.

## Solution

The problem asks us to evaluate a mathematical expression written in Reverse Polish Notation (also known as postfix notation). In this notation, operators follow their operands. We need to evaluate the expression from left to right, applying operators to the most recently seen numbers.

**Goal:** Efficiently evaluate the RPN string array and compute the final resulting integer value using a Stack.

### 1. Stack (Optimal)
We can process the `tokens` array from left to right using a Stack. Whenever we encounter a number, we push it onto the stack. Whenever we encounter an operator, we pop the top two numbers from the stack, apply the operator to them, and push the result back onto the stack. By the end of the evaluation, th ere will be exactly one element left on the stack, which is the final answer.

**Business Logic:**
- Iterate through each token in the `tokens` array.
- If the token is a number, push it onto the stack.
- If the token is an operator (`+`, `-`, `*`, `/`):
  - Pop the top two elements from the stack. The first popped element is the right operand (the second number), and the second popped element is the left operand (the first number).
  - Perform the calculation based on the operator. Pay special attention to division, as it must truncate towards zero.
  - Push the result back onto the stack.
- After processing all tokens, the final result will be the only element remaining in the stack. Return it.

### Complexity
- Time Complexity: $O(n)$ where $n$ is the number of tokens. We iterate through the array once and each token is processed in constant time.
- Space Complexity: $O(n)$ in the worst case (e.g., all tokens are numbers), to store numbers in the stack.

### Implementation
Python
```python
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        for token in tokens:
            if token in "+-*/":
                b = stack.pop()
                a = stack.pop()
                if token == '+':
                    stack.append(a + b)
                elif token == '-':
                    stack.append(a - b)
                elif token == '*':
                    stack.append(a * b)
                elif token == '/':
                    # Division truncates toward zero
                    stack.append(int(a / b))
            else:
                stack.append(int(token))
        return stack[0]
```
Java
```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                if (token.equals("+")) {
                    stack.push(a + b);
                } else if (token.equals("-")) {
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    stack.push(a * b);
                } else if (token.equals("/")) {
                    // Java integer division natively truncates towards zero
                    stack.push(a / b); 
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
}
```

