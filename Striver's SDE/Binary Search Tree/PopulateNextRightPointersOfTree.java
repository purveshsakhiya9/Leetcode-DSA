import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PopulateNextRightPointersOfTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public static Node build(int[] values){
        if(values==null || values.length==0) return null;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(values[0]);
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i< values.length){
            Node current = queue.poll();
            current.left = new Node(values[i]);
            queue.add(current.left);
            i++;
            if(i< values.length){
                current.right = new Node(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    public static Node connect(Node root) {
        if(root==null){
            return root;
        }
        if(root.left!=null){
            root.left.next=root.right;
        }
        if(root.right!=null && root.next!=null){
            root.right.next=root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
    public static void main(String[] args){
        int[] treevalues = {1,2,3,4,5,6,7};
        //build binary tree
        Node root = build(treevalues);

        //populates next right pointers in binary tree
        Node result = connect(root);

        //validate connect function by manually traversing
        Queue<Node> queue = new LinkedList<>();
        queue.add(result);
        while (result.left!=null){
            queue.add(result.left);
            result = result.left;
        }

        while (!queue.isEmpty()){
            Node currentlevel = queue.poll();
            while (currentlevel!=null){
                System.out.print(currentlevel.val+" ");
                currentlevel = currentlevel.next;
            }
            System.out.println();
        }

    }
}