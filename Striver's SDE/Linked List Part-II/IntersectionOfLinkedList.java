public class IntersectionOfLinkedList {
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

    public static ListNode IntersectionOfLinkedList(ListNode a, ListNode b){
        ListNode head1 = a;
        ListNode head2 = b;
        while(head1 != head2){
            if(head1==null){
                head1 = b;
            }else{
                head1 = head1.next;
            }
            if(head2==null){
                head2 = a;
            }else{
                head2 = head2.next;
            }
        }
        return head1;
    }
    public static void main(String[] args){
        //create linked list a1 -> a2
        ListNode a = new ListNode(4);
        a.next = new ListNode(1);

        //create linked list b1 -> b2 -> b3
        ListNode c = new ListNode(8);
        c.next = new ListNode(4);
        c.next.next = new ListNode(5);

        //create linked list c1 -> c2 -> c3
        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(1);

        // connect a and b to c so that first node of c is intersection
        a.next.next = c;
        b.next.next.next = c;

        ListNode res = IntersectionOfLinkedList(a,b);
        System.out.println("Intersection at "+res.val);
    }
}