import java.util.LinkedList;
import java.util.Queue;

public class ValidBinarySearchTree {
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
    public static boolean ValidBinarySearchTree(TreeNode root){
        return check(root,null,null);
    }
    public static boolean check(TreeNode node, Integer left, Integer right){
        if(node==null) return true;
        if(left!=null && node.val<=left) return false;
        if(right!=null && node.val>=right) return false;
        return check(node.left,left, node.val) && check(node.right, node.val, right);

    }
    public static void main(String[] args){
         Integer[] treevalues = {5,1,4,null,null,3,6};
         TreeNode root = buildTree(treevalues);
         System.out.println(ValidBinarySearchTree(root));

        Integer[] treevalues1 = {2,1,3};
        TreeNode root1 = buildTree(treevalues1);
        System.out.println(ValidBinarySearchTree(root1));
    }
}