import java.util.LinkedList;
import java.util.Queue;

public class SearchInBinaryTree{
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
    public static TreeNode SearchInBinaryTree(TreeNode root, int val){
        if(root == null) return null;
        if(root.val == val) return root;
        if(val< root.val) return SearchInBinaryTree(root.left,val);
        else return SearchInBinaryTree(root.right,val);
    }
    public static void main(String[] args){
        Integer[] treevalues = {4,2,7,1,3};
        TreeNode root = buildtree(treevalues);
        int val = 2;
        TreeNode result = SearchInBinaryTree(root,val);
        if(result==null) System.out.println("null");
        else System.out.println(result.val);
    }
}