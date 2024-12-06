import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;

public class SymmetricTree {
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
    public static TreeNode buildtree(Integer[] values){
        if(values==null || values.length==0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(values[0]);
        queue.add(root);
        int i = 1;
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
    public static boolean SymmetricTree(TreeNode root){
        if(root == null) return true;
        return check(root.left,root.right);
    }
    public static boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left ==null || right==null) return false;
        if(left.val !=right.val){
            return false;
        }
        return check(left.left,right.right) && check(left.right,right.left);
    }
    public static void main(String[] args){
        Integer[] treevalues = {1,2,2,3,4,4,3};
        TreeNode root = buildtree(treevalues);
        System.out.println((SymmetricTree(root)));
    }
}