public class RotateLinkedList {
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
    public static ListNode RotateLinkedList(ListNode head,int k){
        if(head == null) return null;
        int count = 1;
        ListNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            count++;
        }
        k = k % count;
        if(k==0){
            return head;
        }else{
            temp.next = head;
            temp = head;
            for(int i = 1; i< count-k; i++){
                temp = temp.next;
            }
            head = temp.next;
            temp.next = null;
        }
        return head;
    }
    public static void main(String[] args){
        // Create a linked list with values 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i<=5; i++){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        System.out.println(linkedListToString(head));
        head = RotateLinkedList(head,2);
        System.out.println(linkedListToString(head));
    }
}