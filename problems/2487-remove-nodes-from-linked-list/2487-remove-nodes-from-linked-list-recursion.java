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