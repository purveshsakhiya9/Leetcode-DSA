public class ReverseLinkedList {
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

    public static ListNode ReverseLinkedList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        while(curr!=null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static void main(String[] args){
        // Create a linked list with values 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i<=5; i++){
            curr.next = new ListNode(i);
            curr = curr.next;
        }

        System.out.println("Original Linked List: " + linkedListToString(head));
        // Reverse the linked list
        ListNode reversedHead = ReverseLinkedList(head);
        // Print reversed list
        System.out.println("Reversed Linked List: " + linkedListToString(reversedHead));

    }


}