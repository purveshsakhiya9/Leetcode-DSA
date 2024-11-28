import java.util.LinkedList;
import java.util.Queue;

public class BalancedBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static TreeNode buildtree(Integer[] values) {
        if(values==null || values.length==0){
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        int i = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && i< values.length){
            TreeNode current = queue.poll();
            if(values[i]!=null){
                 current.left = new TreeNode(values[i]);
                 queue.add(current.left);
            }
            i++;
            if(i< values.length && values[i]!=null){
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    public static boolean BalancedBinaryTree(TreeNode root){
        if(height(root) == -1) return false;
        return true;
    }
    public static int height(TreeNode root){
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if(left==-1 || right ==-1 || Math.abs(left-right)>1) return -1;
        return Math.max(left,right)+1;
    }
    public static void main(String[] args){
        Integer[] treeValues = {1,2,2,3,3,null,null,4,4};
        TreeNode root = buildtree(treeValues);
        System.out.println(BalancedBinaryTree(root));
    }


}