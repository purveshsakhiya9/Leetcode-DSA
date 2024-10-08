public class AddTwoNumbers {
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

    public static ListNode AddTwoNumbers(ListNode head1, ListNode head2){
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (head1 != null || head2 != null || carry != 0) {
            int sum = carry;

            if (head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            }

            if (head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args){
        // First linked list: 1 -> 2 -> 3
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(5);

        // Second linked list: 2 -> 4 -> 6
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        System.out.println(linkedListToString(head1));
        System.out.println(linkedListToString(head2));
        ListNode result = AddTwoNumbers(head1,head2);
        System.out.println(linkedListToString(result));
    }
}