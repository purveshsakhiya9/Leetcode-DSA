import java.util.LinkedList;
import java.util.Queue;

public class LowestCommonAncestor {
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
        if(values==null || values.length==0){
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i<values.length){
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
    public static TreeNode LowestCommonAncestor(TreeNode root,TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = LowestCommonAncestor(root.left,p,q);
        TreeNode right = LowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        else{
            return right;
        }
    }
    public static void main(String[] args){
        Integer[] treeValues = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = buildtree(treeValues);
        TreeNode p = root.left;
        TreeNode q = root.right;
        TreeNode result = LowestCommonAncestor(root,p,q);
        System.out.println(result.val);
    }
}