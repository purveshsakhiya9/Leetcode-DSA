import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class KthSmallestElementInBST {
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
    public static int KthSmallestElementInBST(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            count++;
            if (count == k) return current.val;
            current = current.right;
        }

        return -1;
    }
    public static void main(String[] args){
        Integer[] treevalues = {3,1,4,null,2};
        TreeNode root = buildTree(treevalues);
        int k = 1;
        System.out.println(KthSmallestElementInBST(root,k));

        Integer[] treevalues1 = {5,3,6,2,4,null,null,1};
        TreeNode root1 = buildTree(treevalues1);
        int a = 3;
        System.out.println(KthSmallestElementInBST(root1,a));
    }
}