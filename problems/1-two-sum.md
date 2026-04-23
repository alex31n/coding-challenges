# [1. Two Sum](https://leetcode.com/problems/two-sum/)

**Difficulty:** `Easy`

**Topics :** `Junior` `Array` `Hash Table`

**Source:** [LeetCode](https://leetcode.com/problems/two-sum/)

---

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

You can return the answer in any order.

**Example 1:**. 
> Input: `nums = [2,7,11,15], target = 9`  
> Output: `[0,1]`  
> Explanation: `Because nums[0] + nums[1] == 9, we return [0, 1].`  

**Example 2:**  
> Input: `nums = [3,2,4], target = 6`  
> Output: `[1,2]`  

**Example 3:**  
> Input: `nums = [3,3], target = 6`  
> Output: `[0,1]`  
 

**Constraints:**  
`2 <= nums.length <= 104`  
`-109 <= nums[i] <= 109`  
`-109 <= target <= 109`  
Only one valid answer exists.
 

**Follow-up:** Can you come up with an algorithm that is less than $O(n^2)$ time complexity?


## Solution
The Two Sum problem is the classic introduction to array manipulation and hash maps. The goal is to find the indices of two numbers in an array that add up to a specific target value.

### Method 1: The Brute Force Approach
The most intuitive way to solve this is by checking every possible pair of numbers in the array.  

- Use a nested loop. The outer loop picks an element $i$.
- The inner loop iterates through the remaining elements $j$.
- If nums[i] + nums[j] equals the target, you return the indices $[i, j]$.

### Complexity
- Time Complexity: $O(n^2)$. We compare each element with every other element.
- Space Complexity: $O(1)$. No extra data structures are used.

### Implementation

Python
```python
def twoSum(nums, target):
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] + nums[j] == target:
                return [i, j]
```

Java
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        // Return null or throw exception if no solution is found
        return null; 
    }
}
```

### Method 2: Hash Map (Optimal)
This approach reduces the time complexity significantly by using a hash map to remember the numbers we have already visited.

- We iterate through the array only once.
- For each number n, we calculate the complement ($target - n$).
- The Check: We look into our hash map to see if that complement exists.
- If it exists: We have found the pair! The answer is the index stored in the map and our current index.
- If it doesn't: We store the current number n and its index in the map and move to the next element.

### Complexity:
- Time: $O(n)$ — We only traverse the list once, and hash map lookups take $O(1)$ on average.
- Space: $O(n)$ — In the worst case, we might store $n - 1$ elements in the map before finding a match.

### Code

Python
```python
def twoSum(nums, target):
    prevMap = {} # val : index

    for i, n in enumerate(nums):
        complement = target - n
        if complement in prevMap:
            return [prevMap[complement], i]
        prevMap[n] = i
```

Java
```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if the map already contains the required number
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Store the current number and its index
            map.put(nums[i], i);
        }
        
        return null;
    }
}
```

## Comparison
| Metric           | Brute Force              | Hash Map                   |
| ---------------- | ------------------------ | -------------------------- |
| Efficiency       | Slow (Quadratic)         | Fast (Fast (Linear)        |
| Memory           | Minimal                  | Higher (scales with input) |
| Use Case         | Educational / Small data | Production / Interviews    |
| Time Complexity  | $O(n^2)$                 | $O(n)$                     |
| Space Complexity | $O(1)$                   | $O(n)$                     |

