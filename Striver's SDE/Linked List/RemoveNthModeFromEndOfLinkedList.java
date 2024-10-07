public class RemoveNthModeFromEndOfLinkedList {
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

    public static ListNode RemoveNthModeFromEndOfLinkedList(ListNode head,int n){
        ListNode temp = new ListNode(0,head);
        ListNode slow = temp;
        ListNode fast = head;
        for(int i = 0; i< n; i++){
            fast = fast.next;
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return temp.next;
    }

    public static void main(String[] args){
        // Create a linked list with values 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i<=6; i++){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        int n = 2 ;
        ListNode res = RemoveNthModeFromEndOfLinkedList(head,n);
        System.out.println(linkedListToString(res));
    }
}