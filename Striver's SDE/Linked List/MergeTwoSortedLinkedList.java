public class MergeTwoSortedLinkedList {
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

    public static ListNode MergeTwoSortedLinkedList(ListNode head1, ListNode head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }else{
            while(head1!=null || head2!=null){
                if(head1.val< head2.val){
                    head1.next = MergeTwoSortedLinkedList(head1.next,head2);
                    return head1;
                }else{
                    head2.next = MergeTwoSortedLinkedList(head1,head2.next);
                    return head2;
                }
            }
        }
        return null;
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

        ListNode mergedlinkedlist = MergeTwoSortedLinkedList(head1,head2);
        System.out.println(linkedListToString(mergedlinkedlist));
    }
}