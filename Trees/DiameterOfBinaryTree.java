package Trees;
import java.util.*;

public class DiameterOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
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

    public static int maxi = Integer.MIN_VALUE;
    public static int diameterOfBinaryTree(TreeNode root){
        calculateHeight(root);
        return maxi;
    }
    public static int calculateHeight(TreeNode root){
        if(root==null) return 0;
        int left = calculateHeight(root.left);
        int right = calculateHeight(root.right);
        maxi = Math.max(maxi,left+right);
        return 1+Math.max(left,right);
    }
    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);
        System.out.println(diameterOfBinaryTree(root));
    }
}