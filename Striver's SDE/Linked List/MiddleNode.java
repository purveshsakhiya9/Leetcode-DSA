public class MiddleNode {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static String linkedListToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    public static ListNode MiddleNode(ListNode head){
        ListNode slow=head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args){
        // Create a linked list with values 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i<=5; i++){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        ListNode middleNode = MiddleNode(head);
        System.out.println(linkedListToString(middleNode));
    }
}