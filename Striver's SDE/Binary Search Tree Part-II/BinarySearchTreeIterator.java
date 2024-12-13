import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTreeIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }
    public static void main(String[] args){
        Integer[] treevalues = {7, 3, 15, null, null, 9, 20};
        TreeNode root = buildTree(treevalues);
        BinarySearchTreeIterator bSTIterator = new BinarySearchTreeIterator(root);
        System.out.println(bSTIterator.next());    // return 3
        System.out.println(bSTIterator.next());;    // return 7
        System.out.println(bSTIterator.hasNext());; // return True
        System.out.println(bSTIterator.next());;    // return 9
        System.out.println(bSTIterator.hasNext());; // return True
        System.out.println(bSTIterator.next());;    // return 15
        System.out.println(bSTIterator.hasNext());; // return True
        System.out.println(bSTIterator.next());;    // return 20
        System.out.println(bSTIterator.hasNext());; // return False

    }
    private Stack<TreeNode> stack;
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null){
            stack.push(curr);
            if(curr.left!=null){
                curr = curr.left;
            }else{
                break;
            }
        }
    }
    private boolean hasNext() {
        return !stack.isEmpty();
    }

    private int next() {
        TreeNode node = stack.pop();
        TreeNode curr = node;
        if(curr.right!=null){
            curr = curr.right;
            while(curr!=null){
                stack.push(curr);
                if(curr.left!=null){
                    curr = curr.left;
                }else{
                    break;
                }
            }
        }
        return node.val;
    }
}