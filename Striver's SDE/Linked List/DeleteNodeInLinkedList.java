public class DeleteNodeInLinkedList {

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

    public static void DeleteNodeInLinkedList(ListNode node){
        while (node.next.next!=null){
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
    public static void main(String[] args){
        // Create a linked list with values 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i<=6; i++){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        ListNode node = head;
        while(node.val!=3){
            node = node.next;
        }
        DeleteNodeInLinkedList(node);
        System.out.println(linkedListToString(head));
    }
}