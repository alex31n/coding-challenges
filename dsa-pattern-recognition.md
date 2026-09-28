# DSA Pattern Recognition: The Right Approach Guide

A structured guide to recognizing data structure and algorithm (DSA) patterns from problem descriptions, constraints, and clues.

---

## Quick Reference Matrix

| # | Pattern | Core Idea | Spot It From (Signals & Clues) | Classic Problems |
|---|---|---|---|---|
| **1** | **Two Pointers** | Traverse a linear data structure using two index pointers moving towards each other or in the same direction. | • Sorted arrays or lists<br>• Finding pairs/triplets with a target sum<br>• Reversing / in-place swapping<br>• Removing duplicates | • Two Sum II<br>• 3Sum<br>• Container With Most Water<br>• Remove Duplicates from Sorted Array |
| **2** | **Sliding Window** | Maintain a contiguous sub-range (window) of elements and slide/expand/shrink it to find optimal answers. | • Contiguous subarrays or substrings<br>• Substring with at most/exact $K$ distinct elements<br>• Finding min/max/target sum of subarray of size $K$ | • Longest Substring Without Repeating Characters<br>• Minimum Size Subarray Sum<br>• Max Consecutive Ones III |
| **3** | **Fast & Slow Pointers** | Move two pointers at different speeds (e.g. $1\times$ and $2\times$) through sequences or linked structures. | • Cyclic paths or loop detection in linked lists/arrays<br>• Finding midpoint of a linked list<br>• Cycle start node or meeting point | • Linked List Cycle (I & II)<br>• Middle of the Linked List<br>• Happy Number<br>• Find the Duplicate Number |
| **4** | **Merge Intervals** | Sort intervals by start time and process overlapping intervals sequentially. | • Intervals given as `[start, end]` pairs<br>• Schedule overlaps, room bookings, time conflicts<br>• Merging or inserting ranges | • Merge Intervals<br>• Insert Interval<br>• Meeting Rooms I & II<br>• Non-overlapping Intervals |
| **5** | **Binary Search** | Repeatedly divide a monotonic search space in half and discard the unviable half. | • Sorted arrays or rotated sorted arrays<br>• Finding position/boundary (first/last true)<br>• "Minimax" or "Maximize the minimum" answer search | • Binary Search<br>• Search in Rotated Sorted Array<br>• Find Minimum in Rotated Sorted Array<br>• Koko Eating Bananas |
| **6** | **DFS (Depth-First Search)** | Traverse as deep as possible down a branch before backtracking. | • Trees, graphs, matrices (grids)<br>• Path existence, connected components, islands<br>• Topological sorting, cycle detection in DAGs | • Number of Islands<br>• Clone Graph<br>• Course Schedule<br>• Max Area of Island |
| **7** | **BFS (Breadth-First Search)** | Explore nodes level-by-level using a First-In-First-Out (FIFO) queue. | • Shortest path in unweighted graphs or grids<br>• Level-order traversal of trees<br>• Minimum steps/turns to reach a state | • Binary Tree Level Order Traversal<br>• Shortest Path in Binary Matrix<br>• Word Ladder<br>• Rotting Oranges |
| **8** | **Dynamic Programming** | Break problems down into overlapping subproblems, solving each subproblem once and caching results. | • Optimization ("max/min profit/cost")<br>• Counting ("number of ways to...")<br>• Decision choice at each step depends on prior optimal choices | • Climbing Stairs<br>• Longest Increasing Subsequence<br>• Coin Change<br>• 0/1 Knapsack / Partition Equal Subset |
| **9** | **Backtracking** | Build candidate solutions step-by-step incrementally and abandon ("backtrack") when constraints are violated. | • Finding all permutations, combinations, or partitions<br>• Puzzle solvers (Sudoku, N-Queens)<br>• Exhaustive search with pruning | • Subsets<br>• Permutations<br>• Combination Sum<br>• N-Queens<br>• Sudoku Solver |
| **10** | **Greedy** | Make the locally optimal choice at each step with the assumption it leads to the global optimum. | • Optimization problems with greedy-choice property<br>• Interval scheduling / activity selection<br>• Jump game, gas stations, minimum coins (canonical systems) | • Jump Game I & II<br>• Gas Station<br>• Non-overlapping Intervals<br>• Task Scheduler |
| **11** | **Heap (Priority Queue)** | Maintain a binary heap to access the running minimum or maximum element in $O(1)$ time and $O(\log k)$ insertions. | • Finding the $K$-th largest or smallest element<br>• Top $K$ frequent items<br>• Merging $K$ sorted streams or intervals | • Kth Largest Element in an Array<br>• Top K Frequent Elements<br>• Find Median from Data Stream<br>• Merge K Sorted Lists |
| **12** | **Hashing** | Use hash maps and hash sets for constant time $O(1)$ lookup, insertions, and frequency counting. | • Frequency counting / anagram checks<br>• Instant lookups: checking if $target - x$ exists<br>• Deduplication and tracking seen elements | • Two Sum<br>• Group Anagrams<br>• Longest Consecutive Sequence<br>• Subarray Sum Equals K |

---

## Detailed Pattern Breakdowns

```
                                  HOW TO CHOOSE A PATTERN
                                             │
      ┌──────────────────┬───────────────────┼───────────────────┬──────────────────┐
      ▼                  ▼                   ▼                   ▼                  ▼
 Linear / Array     Search Space       Graph / Tree         Combinations        Optima / Counting
   Problems           Problems           Problems             / Paths              Problems
      │                  │                   │                   │                  │
 ├─ Sorted?         ├─ Sorted/Searchable?  ├─ Shortest path?    ├─ All paths?      ├─ Overlapping
 │  ↳ Two Pointers  │  ↳ Binary Search     │  ↳ BFS (Unweighted)│  ↳ Backtracking  │  subproblems?
 ├─ Contiguous?     └─ Top/Kth element?    ├─ Full explore?     └─ Path existence? │  ↳ DP
 │  ↳ Sliding Win      ↳ Heap (PQ)         │  ↳ DFS                ↳ DFS           └─ Local optimal
 ├─ Cycles/Midpoint?                       └─ Level order?                            works?
 │  ↳ Fast & Slow                             ↳ BFS                                   ↳ Greedy
 └─ Intervals?
    ↳ Merge Intervals
```

---

### 1. Two Pointers

#### Core Idea
Use two indices to traverse an array or list. Pointers may move towards each other (opposite ends), move in the same direction at varying rates, or iterate across two separate arrays.

#### Spot It From (Problem Clues)
- The input is a **sorted array or string** (or can be sorted without violating requirements).
- You need to find **pairs or triplets** that sum to a target value.
- Reversing elements in-place (e.g. palindrome checks).
- In-place element filtering/partitioning (e.g. remove duplicates, move zeroes).

#### When NOT to Use
- When the array is unsorted and sorting it takes $O(n \log n)$ while a hash map can solve it in $O(n)$ space and time (e.g. standard Two Sum with unsorted array).
- When searching for arbitrary non-contiguous subsets.

#### Common Problems
- [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)
- [15. 3Sum](https://leetcode.com/problems/3sum/)
- [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
- [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

#### Code Skeleton
```python
def two_pointers(arr: list[int], target: int) -> list[int]:
    left, right = 0, len(arr) - 1
    
    while left < right:
        current_sum = arr[left] + arr[right]
        if current_sum == target:
            return [left, right]
        elif current_sum < target:
            left += 1  # Need larger value
        else:
            right -= 1 # Need smaller value
            
    return []
```

---

### 2. Sliding Window

#### Core Idea
Maintain a contiguous range of elements `[left, right]` that expands by moving `right` and shrinks by moving `left` when a condition or constraint is broken.

#### Spot It From (Problem Clues)
- The problem asks about **contiguous subarrays** or **substrings**.
- Phrases like:
  - "Longest substring with at most $k$ distinct characters"
  - "Minimum window containing all characters of pattern"
  - "Maximum sum subarray of length $k$"
- Monotonic condition: expanding the right bound never shrinks the valid window scope, and shrinking from the left eventually restores validity.

#### When NOT to Use
- The elements are not contiguous (subsequences or subsets instead of subarrays).
- The array contains negative numbers and asks for a target sum (use Prefix Sums + Hash Map instead).

#### Common Problems
- [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)
- [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)
- [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

#### Code Skeleton
```python
def sliding_window(s: str) -> int:
    left = 0
    state = {} # e.g. frequency map or sum
    max_len = 0
    
    for right in range(len(s)):
        # 1. Expand window with s[right]
        state[s[right]] = state.get(s[right], 0) + 1
        
        # 2. Shrink window while invalid
        while not is_valid(state):
            state[s[left]] -= 1
            if state[s[left]] == 0:
                del state[s[left]]
            left += 1
            
        # 3. Update answer
        max_len = max(max_len, right - left + 1)
        
    return max_len
```

---

### 3. Fast & Slow Pointers (Tortoise & Hare)

#### Core Idea
Use two pointers moving at different speeds (usually slow advances 1 step, fast advances 2 steps). If there is a cycle, the fast pointer will lap the slow pointer and they will collide.

#### Spot It From (Problem Clues)
- **Linked list cycle detection** or finding the start of a cycle.
- Finding the **middle element** of a linked list in a single pass.
- Finding cycles in a sequence generated by a deterministic function (e.g., sum of squares of digits in Happy Number).
- Array values represent "next pointers" within a fixed range $[1, n]$ (e.g., Find the Duplicate Number).

#### When NOT to Use
- When direct random access is available and extra space is permitted (a simple hash set could do it if $O(n)$ space is fine, but fast & slow gives $O(1)$ space).

#### Common Problems
- [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
- [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
- [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)
- [202. Happy Number](https://leetcode.com/problems/happy-number/)
- [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)

#### Code Skeleton
```python
def has_cycle(head: ListNode) -> bool:
    slow = fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True
            
    return False
```

---

### 4. Merge Intervals

#### Core Idea
Intervals are defined as `[start, end]`. Sort the intervals by their `start` time (or occasionally `end` time). Once sorted, any two overlapping intervals must appear consecutively.

#### Spot It From (Problem Clues)
- Inputs are sets of ranges: `[start_i, end_i]`.
- Problem keywords: "overlapping", "schedule conflict", "time slots", "meeting rooms", "calendar bookings".
- Questions asking to merge colliding ranges or find maximum simultaneous events.

#### When NOT to Use
- Points are isolated values without a range / duration.
- Higher dimensional bounding boxes (use specialized range trees / sweep-line).

#### Common Problems
- [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- [57. Insert Interval](https://leetcode.com/problems/insert-interval/)
- [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)
- [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
- [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

#### Code Skeleton
```python
def merge_intervals(intervals: list[list[int]]) -> list[list[int]]:
    if not intervals:
        return []
        
    intervals.sort(key=lambda x: x[0])
    merged = [intervals[0]]
    
    for start, end in intervals[1:]:
        prev_start, prev_end = merged[-1]
        if start <= prev_end:
            # Overlap: extend end of previous interval
            merged[-1][1] = max(prev_end, end)
        else:
            merged.append([start, end])
            
    return merged
```

---

### 5. Binary Search

#### Core Idea
Discard half of the candidate solution space in $O(1)$ decisions by testing the middle element, achieving $O(\log n)$ time.

#### Spot It From (Problem Clues)
- The input is **sorted** or **partially sorted / rotated**.
- Looking for a target element or insertion index with strict $O(\log n)$ runtime constraint.
- **Binary Search on Answer**: When the answer space is monotonic (e.g. "if speed $v$ is valid, any speed $> v$ is also valid").
  - Phrasing: "Find the minimum capacity to...", "Maximize the minimum distance", "K-th smallest in matrix".

#### When NOT to Use
- The search space has no monotonic property (increasing/decreasing order of validity).
- Linear scans are unavoidable (e.g. unsorted data with no answer monotonicity).

#### Common Problems
- [704. Binary Search](https://leetcode.com/problems/binary-search/)
- [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)
- [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)

#### Code Skeleton
```python
# Binary Search on Answer
def binary_search(low: int, high: int) -> int:
    ans = high
    while low <= high:
        mid = (low + high) // 2
        if condition_met(mid):
            ans = mid
            high = mid - 1 # Try to find smaller valid answer
        else:
            low = mid + 1  # Must increase mid
    return ans
```

---

### 6. Depth-First Search (DFS)

#### Core Idea
Explore as far as possible down each branch of a tree, graph, or matrix before backtracking. Typically implemented via recursion or an explicit stack.

#### Spot It From (Problem Clues)
- Problems on **Trees** (traversals, maximum depth, path sum, LCA).
- **Connected components / regions** in a 2D matrix/grid (e.g., islands, flood fill).
- Checking if **any valid path** exists from source to destination.
- Topological sort / cycle detection in directed graphs (detecting back-edges).

#### When NOT to Use
- Finding the **shortest path in an unweighted graph** (DFS might find a valid path, but not necessarily the shortest path without exploring all paths). Use BFS instead.
- Very deep recursive graphs where stack overflow is a risk without tail-call optimization.

#### Common Problems
- [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- [133. Clone Graph](https://leetcode.com/problems/clone-graph/)
- [207. Course Schedule](https://leetcode.com/problems/course-schedule/)
- [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)
- [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

#### Code Skeleton
```python
def dfs_grid(grid: list[list[str]], r: int, c: int, visited: set) -> None:
    if (r < 0 or r >= len(grid) or 
        c < 0 or c >= len(grid[0]) or 
        grid[r][c] == '0' or (r, c) in visited):
        return
        
    visited.add((r, c))
    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        dfs_grid(grid, r + dr, c + dc, visited)
```

---

### 7. Breadth-First Search (BFS)

#### Core Idea
Explore neighbor nodes level-by-level using a FIFO queue. In an unweighted graph, the first time a node is visited is guaranteed to be along the shortest path.

#### Spot It From (Problem Clues)
- **Shortest path** or **minimum steps / transformations** in an unweighted graph or grid.
- **Level-order traversal** of a tree.
- Multi-source spreading problems (e.g., rotten oranges spreading day-by-day).
- Finding all nodes within $K$ distance.

#### When NOT to Use
- Weighted graphs where edge weights vary (use Dijkstra's Algorithm or Bellman-Ford).
- Exhaustive tree exploration where recursion (DFS) uses significantly less memory than storing an entire level in a queue ($O(V)$ queue memory).

#### Common Problems
- [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
- [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)
- [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
- [127. Word Ladder](https://leetcode.com/problems/word-ladder/)
- [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)

#### Code Skeleton
```python
from collections import deque

def bfs_shortest_path(grid: list[list[int]]) -> int:
    if grid[0][0] == 1:
        return -1
    
    R, C = len(grid), len(grid[0])
    queue = deque([(0, 0, 1)]) # (row, col, distance)
    visited = {(0, 0)}
    
    while queue:
        r, c, dist = queue.popleft()
        if r == R - 1 and c == C - 1:
            return dist
            
        for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < R and 0 <= nc < C and grid[nr][nc] == 0 and (nr, nc) not in visited:
                visited.add((nr, nc))
                queue.append((nr, nc, dist + 1))
                
    return -1
```

---

### 8. Dynamic Programming (DP)

#### Core Idea
Solve complex optimization or counting problems by breaking them into overlapping subproblems with optimal substructure. Cache subproblem results (memoization or tabulation) to prevent re-computation.

#### Spot It From (Problem Clues)
- The problem asks for:
  - **Optimal value**: Minimum cost, maximum profit, longest subsequence.
  - **Counting**: Number of distinct ways to achieve a goal.
  - **Possibility**: Is it possible to partition/reach a target sum?
- The current decision depends on the outcome of previous subproblems.
- Naive recursive solution shows overlapping subproblems (exponential tree with repeating states).

#### When NOT to Use
- The problem does not have overlapping subproblems (divide and conquer like Merge Sort).
- A Greedy choice guarantees the optimal outcome without looking back (Greedy is faster $O(n)$ or $O(n \log n)$).
- Problem asks for *all* actual combinations or arrangements (use Backtracking).

#### Common Problems
- [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
- [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
- [322. Coin Change](https://leetcode.com/problems/coin-change/)
- [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)
- [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)

#### Code Skeleton (1D DP)
```python
def coin_change(coins: list[int], amount: int) -> int:
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    
    for a in range(1, amount + 1):
        for c in coins:
            if a - c >= 0:
                dp[a] = min(dp[a], dp[a - c] + 1)
                
    return dp[amount] if dp[amount] != float('inf') else -1
```

---

### 9. Backtracking

#### Core Idea
Build candidate solutions incrementally. As soon as a candidate fails to satisfy constraints, discard it ("backtrack" to the parent state) and try alternative choices.

#### Spot It From (Problem Clues)
- The problem asks for **all possible** solutions:
  - "Generate all permutations of..."
  - "Find all subsets / power set"
  - "Return all valid combinations that sum to target"
- Constraint satisfaction / puzzle boards: N-Queens, Sudoku, Word Search.
- Constraints are small (e.g. $N \le 15$ or $N \le 20$).

#### When NOT to Use
- Only the **count** or **best value** is requested (DP or Greedy is often exponentially faster).
- $N$ is large ($N > 30$), which indicates an exponential $O(2^N)$ or $O(N!)$ backtracking approach will Time Out (TLE).

#### Common Problems
- [78. Subsets](https://leetcode.com/problems/subsets/)
- [46. Permutations](https://leetcode.com/problems/permutations/)
- [39. Combination Sum](https://leetcode.com/problems/combination-sum/)
- [51. N-Queens](https://leetcode.com/problems/n-queens/)
- [79. Word Search](https://leetcode.com/problems/word-search/)

#### Code Skeleton
```python
def subsets(nums: list[int]) -> list[list[int]]:
    result = []
    
    def backtrack(start_index: int, current_path: list[int]):
        result.append(list(current_path))
        
        for i in range(start_index, len(nums)):
            # 1. Make choice
            current_path.append(nums[i])
            # 2. Recurse
            backtrack(i + 1, current_path)
            # 3. Undo choice (Backtrack)
            current_path.pop()
            
    backtrack(0, [])
    return result
```

---

### 10. Greedy

#### Core Idea
Make the locally optimal choice at each step without reconsidering prior choices, trusting that the sequence of local choices yields a global optimum.

#### Spot It From (Problem Clues)
- Optimization problem (minimize/maximize).
- Sorting by an attribute (e.g., finish times, values, weights) allows clear local decisions.
- Problem satisfies:
  1. **Greedy Choice Property**: A globally optimal solution can be reached by a series of locally optimal selections.
  2. **Optimal Substructure**: The optimal solution to the subproblem after the choice remains optimal.

#### When NOT to Use
- When local choices cut off future better outcomes (e.g., standard 0/1 Knapsack, Coin Change with non-canonical denominations like `[1, 3, 4]` for target `6`). DP must be used instead.

#### Common Problems
- [55. Jump Game](https://leetcode.com/problems/jump-game/)
- [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/)
- [134. Gas Station](https://leetcode.com/problems/gas-station/)
- [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/)
- [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

#### Code Skeleton
```python
def can_jump(nums: list[int]) -> bool:
    farthest = 0
    for i, jump in enumerate(nums):
        if i > farthest:
            return False
        farthest = max(farthest, i + jump)
    return True
```

---

### 11. Heap / Priority Queue

#### Core Idea
A binary tree data structure that allows fast retrieval of the minimum (Min-Heap) or maximum (Max-Heap) element in $O(1)$ time, with insertions and deletions in $O(\log k)$.

#### Spot It From (Problem Clues)
- Finding the **$K$-th largest** or **$K$-th smallest** element in an array/stream.
- Selecting the **Top $K$ frequent** elements.
- Dynamically finding the **median** of an incoming stream of numbers (dual Min-Heap & Max-Heap).
- Merging $K$ sorted arrays, lists, or streams.

#### When NOT to Use
- When $K = 1$ (just find the min/max in a single $O(n)$ pass without a heap).
- When looking up arbitrary elements by key or value (heaps do not support efficient search; use a Hash Map or Balanced BST).

#### Common Problems
- [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
- [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
- [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)
- [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
- [373. Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/)

#### Code Skeleton
```python
import heapq

def find_kth_largest(nums: list[int], k: int) -> int:
    min_heap = []
    for num in nums:
        heapq.heappush(min_heap, num)
        if len(min_heap) > k:
            heapq.heappop(min_heap) # Discard smallest, keep k largest
            
    return min_heap[0]
```

---

### 12. Hashing (Hash Map & Hash Set)

#### Core Idea
Map arbitrary keys to memory buckets using a hash function, providing average $O(1)$ time complexity for insertions, deletions, and lookups.

#### Spot It From (Problem Clues)
- Need instant lookup of previously seen items: "Has this number been seen before?", "Find pair where $b = target - a$".
- Frequency counting, anagram grouping, or identifying duplicate elements.
- Tracking cumulative prefix states: `prefix_sum[j] - prefix_sum[i] = k`.
- Maintaining sequences where immediate lookups for `num + 1` or `num - 1` are needed.

#### When NOT to Use
- When $O(1)$ auxiliary space is strictly required by the problem constraints.
- When ordered traversal (predecessor/successor or sorted order) is needed (use Balanced BSTs or Two Pointers).

#### Common Problems
- [1. Two Sum](https://leetcode.com/problems/two-sum/)
- [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)
- [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)
- [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
- [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

#### Code Skeleton
```python
def two_sum(nums: list[int], target: int) -> list[int]:
    seen = {} # val -> index
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    return []
```

---

## Pattern Identification Decision Matrix

When faced with a new coding question, ask yourself these 5 questions in order:

```
1. Is the input array sorted or can it be easily sorted?
   ├─ YES ──> Two Pointers or Binary Search
   └─ NO ───> Continue

2. Does the problem ask about contiguous segments (subarrays/substrings)?
   ├─ YES ──> Sliding Window or Prefix Sum
   └─ NO ───> Continue

3. Is the input a Tree, Graph, or 2D Matrix?
   ├─ Shortest path / minimum steps ──> BFS
   ├─ Level-order traversal ─────────> BFS
   └─ Paths / components / cycle ─────> DFS

4. Does it ask for "all combinations / permutations" or a search board?
   ├─ YES ──> Backtracking
   └─ NO ───> Continue

5. Does it ask for "maximum / minimum / count of ways"?
   ├─ Can a greedy choice be made safely? ──> Greedy
   ├─ Are there overlapping subproblems? ──> Dynamic Programming
   └─ Do you need running top K / median? ──> Heap (Priority Queue)
```
