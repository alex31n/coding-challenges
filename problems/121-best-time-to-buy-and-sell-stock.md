# [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

**Difficulty:** `Easy`

**Topics :** `Array` `Dynamic Programming`

**Source:** [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

---

You are given an array `prices` where `prices[i]` is the price of a given stock on the $i^{th}$ day.

You want to maximize your profit by choosing a **single day** to buy one stock and choosing a **different day in the future** to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

**Example 1:**  
Input: `prices = [7,1,5,3,6,4]`  
Output: `5`  
Explanation: `Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.`  
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

**Example 2:**
Input: `prices = [7,6,4,3,1]`  
Output: `0`  
Explanation: `In this case, no transactions are done and the max profit = 0.`  
 
**Constraints:**  
`1 <= prices.length <= 105`  
`0 <= prices[i] <= 104`  

  
## Solution

### Approach 1: One Pass (Greedy)
The idea is to maintain a variable `min_price` to keep track of the smallest value encountered so far and a `max_profit` to track the best possible gain. For each price, we update our minimum price or calculate the potential profit if we sold at the current price.

### Complexity
Time Complexity: $O(n)$  
Space Complexity: $O(1)$

### Implementation
Python
```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        min_p = prices[0]
        max_p = 0

        for price in prices:
            if price < min_p:
                min_p = price
            else:
                profit = price - min_p
                max_p = max(max_p, profit)
        return max_p

```
Java
```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                int profit = price - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }
}
```

### Approach 2: Two Pointers (Sliding Window)
We use two pointers: `l (left)` representing the buy day and `r (right)` representing the sell day.

- If the price at `l` is less than the price at `r`, we calculate the profit.
- If the price at `l` is greater than or equal to the price at `r`, we move the `l` pointer to `r` because we found a lower valley.
- We increment `r` every iteration.

### Complexity
Time Complexity: $O(n)$  
Space Complexity: $O(1)$

### Implementation
Python
```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        l, r = 0, 1
        max_p = 0

        while r < len(prices):
            if prices[l] < prices[r]:
                profit = prices[r] - prices[l]
                max_p = max(max_p, profit)
            else:
                l = r
            r += 1

        return max_p
```
Java
```java
class Solution {
    public int maxProfit(int[] prices) {
        int l = 0; // Buy day
        int r = 1; // Sell day
        int maxProfit = 0;

        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                int profit = prices[r] - prices[l];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // If we find a price lower than our current buy price,
                // move the left pointer to the new potential minimum.
                l = r;
            }
            r++;
        }

        return maxProfit;
    }
}
```