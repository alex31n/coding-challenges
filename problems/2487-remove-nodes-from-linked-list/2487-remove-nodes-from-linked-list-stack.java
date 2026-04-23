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