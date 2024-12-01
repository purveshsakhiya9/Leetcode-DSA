import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
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
        int i =1;
        while(!queue.isEmpty() && i< values.length){
            TreeNode current = queue.poll();
            if(values[i]!=null){
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            if(i<values.length && values[i]!=null){
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    public static boolean SameTree(TreeNode p, TreeNode q){
        if(p==null && q== null) return true;
        if(p==null || q==null || p.val!=q.val) return false;
        return SameTree(p.left,q.left) && SameTree(p.right,q.right);
    }
    public static void main(String[] args){
        Integer[] treevalue1 = {1,2,3};
        Integer[] treevalue2 = {1,2,3};
        TreeNode p = buildtree(treevalue1);
        TreeNode q = buildtree(treevalue2);
        System.out.println(SameTree(p,q));
    }
}