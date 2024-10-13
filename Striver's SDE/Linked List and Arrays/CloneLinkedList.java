import java.util.HashMap;

public class CloneLinkedList {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // Helper function to print the linked list with random pointers
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print("Node value: " + temp.val + ", Random points to: ");
            if (temp.random != null) {
                System.out.println(temp.random.val);
            } else {
                System.out.println("null");
            }
            temp = temp.next;
        }
    }
    public static Node createList(){
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        // Step 2: Link nodes through the 'next' pointers
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Step 3: Set up 'random' pointers based on the image
        node1.random = null;       // node 7's random points to null
        node2.random = node1;      // node 13's random points to node 7
        node3.random = node5;      // node 11's random points to node 1
        node4.random = node3;      // node 10's random points to node 11
        node5.random = node1;      // node 1's random points to node 7

        return node1;
    }

    public static Node CloneLinkedlist(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node temp = head;
        while(temp!=null){
            map.put(temp,new Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while(temp!=null){
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }

        return map.get(head);
    }
    public static void main(String[] args){
        Node head = createList();
        printList(head);
        Node res =CloneLinkedlist(head);
        System.out.println();
        printList(res);
    }
}