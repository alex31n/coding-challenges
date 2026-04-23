# [2487. Remove Nodes From Linked List](https://leetcode.com/problems/remove-nodes-from-linked-list/)

**Difficulty:** `Medium`

**Topics :** `Linked List` `Stack` `Recursion` `Monotonic Stack`

---

You are given the `head` of a linked list.

Remove every node which has a node with a greater value anywhere to the right side of it.

Return the `head` of the modified linked list.

**Example 1:**

![](https://assets.leetcode.com/uploads/2022/10/02/drawio.png)

> **Input:** head = [5,2,13,3,8]  
> **Output:** [13,8]  
> **Explanation:** The nodes that should be removed are 5, 2 and 3.  
> - Node 13 is to the right of node 5.  
> - Node 13 is to the right of node 2.  
> - Node 8 is to the right of node 3.  


**Example 2:**
> **Input:** head = [1,1,1,1]  
> **Output:** [1,1,1,1]  
> **Explanation:** Every node has value 1, so no nodes are removed.  

**Constraints:**
- The number of the nodes in the given list is in the range  `[1, 105]`.  
- `1 <= Node.val <= 105`  


# Method 1: Recursion

## Approach

- Check if the given head is None. If it is, return None.
- Recursively call the removeNodes function on the next node.
- If the next node exists and its value is greater than the current node's value, skip the current node by returning the
  next node.
- Otherwise, return the current node.
- Repeat this process until the end of the linked list is reached.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(n)`

## Code

Python

```python []
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:

        if not head:
            return None

        head.next = self.removeNodes(head.next)

        if head.next and head.val < head.next.val:
            return head.next

        return head

```

Java

```java []
class Solution {
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        head.next = removeNodes(head.next);

        if (head.next == null) {
            return head;
        }

        return head.val < head.next.val ? head.next : head;
    }
}

```

# Method 2: Stack

## Approach

- Create a stack to store the nodes of the linked list in reverse order.
- Traverse the original linked list and push each node onto the stack.
- Pop the top node from the stack to initialize the current node.
- Iterate through the stack:
    - Pop the next node from the stack.
    - If the next node's value is greater than or equal to the current node's value, connect it to the current node and
      update the current node to be the next node.
- Return the final current node, which represents the head of the modified linked list.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(n)`

## Code

Python

```python []
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:

        stack = []

        while head:
            stack.append(head)
            head = head.next

        current_node = stack.pop()

        while stack:

            node = stack.pop()

            if node.val >= current_node.val:
                node.next = current_node
                current_node = node

        return current_node

```

Java

```java []
class Solution {
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode currentNode = stack.pop();

        while (!stack.isEmpty()) {

            ListNode node = stack.pop();

            if (node.val >= currentNode.val) {
                node.next = currentNode;
                currentNode = node;
            }
        }

        return currentNode;
    }
}

```