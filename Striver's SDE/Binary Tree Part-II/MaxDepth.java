import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode buildTree(Integer[] values) {
        if(values == null || values.length==0){
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
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

    public static int MaxDepth(TreeNode root){
        if(root==null) return 0;
        return Math.max(MaxDepth(root.left),MaxDepth(root.right))+1;
    }

    public static void main(String[] args){
        Integer[] treeValues = {3,9,20,null,null,15,7};
        TreeNode root = buildTree(treeValues);
        System.out.println(MaxDepth(root));
    }


}