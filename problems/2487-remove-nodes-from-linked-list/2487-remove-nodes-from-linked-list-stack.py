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
