import java.util.LinkedList;
import java.util.Queue;

public class MaximumSumBSTinBT {
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
    private static class Tree{
        boolean bst;
        Integer max;
        Integer min;
        Integer sum;
        Tree(){
            this.bst = false;
            this.max = null;
            this.min = null;
            this.sum = null;
        }
        Tree(boolean bst, int max, int min, int sum){
            this.bst = bst;
            this.max = max;
            this.min = min;
            this.sum = sum;
        }
    }
    private static int maxsum=0;
    public static int MaximumSumBSTinBt(TreeNode root) {
        traverse(root);
        return maxsum;
    }
    private static Tree traverse(TreeNode root){
        if(root==null){
            return new Tree(true, Integer.MIN_VALUE, Integer.MAX_VALUE,0);
        }
        Tree left = traverse(root.left);
        Tree right = traverse(root.right);
        if(!left.bst || !right.bst){
            return new Tree();
        }
        if(root.val<=left.max || root.val>=right.min){
            return new Tree();
        }
        int sum = root.val+left.sum+right.sum;
        maxsum = Math.max(maxsum,sum);
        return new Tree(true, Math.max(root.val,right.max),Math.min(root.val,left.min),sum);
    }
    public static void main(String[] args){
        Integer[] treevalues = {1,4,3,2,4,2,5,null,null,null,null,null,null,4,6};
        TreeNode root = buildTree(treevalues);
        System.out.println(MaximumSumBSTinBt(root));

        maxsum=0;
        Integer[] treevalues1 = {4,3,null,1,2};
        TreeNode root1 = buildTree(treevalues1);
        System.out.println(MaximumSumBSTinBt(root1));

        maxsum=0;
        Integer[] treevalues2 = {-4,-2,-5};
        TreeNode root2 = buildTree(treevalues2);
        System.out.println(MaximumSumBSTinBt(root2));
    }
}