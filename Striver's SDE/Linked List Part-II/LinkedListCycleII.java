public class LinkedListCycleII {
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

    public static ListNode LinkedLIstCycle(ListNode head){
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
    public static void main(String[] args){
        ListNode a = new ListNode(3);
        a.next = new ListNode(2);
        a.next.next = new ListNode(0);
        a.next.next.next = new ListNode(4,a.next);
        ListNode res = LinkedLIstCycle(a);
        if(res!=null){
            System.out.println(res.val);
        }else {
            System.out.println(res);
        }
    }
}