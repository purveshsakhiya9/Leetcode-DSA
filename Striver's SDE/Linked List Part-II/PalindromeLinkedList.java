import java.util.LinkedList;
import java.util.List;

public class PalindromeLinkedList {
    static ListNode curr;

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static boolean RecursionPalindromeLinkedList(ListNode head) {
        curr = head;
        return checkPalindrome(head);
    }

    private static boolean checkPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        boolean isPalindrome = checkPalindrome(head.next);
        if (!isPalindrome || head.val != curr.val) {
            return false;
        }
        curr = curr.next;
        return true;
    }

    public static boolean ReversePalindromeLinkedList(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode start = ReverseLinkedList(slow.next);
        while(start!=null){
            if(start.val!=head.val){
                return false;
            }
            head = head.next;
            start = start.next;
        }
        return true;

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
        // First linked list: 1 -> 2 -> 2 -> 1
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next= new ListNode(1);
        System.out.println(RecursionPalindromeLinkedList(head));
        System.out.println(ReversePalindromeLinkedList(head));
    }
}