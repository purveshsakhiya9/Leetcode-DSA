import java.util.LinkedList;
import java.util.Queue;

public class LowestCommonAncestorBST {
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
    public static TreeNode LowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q){
        while(root!=null){
            if(root.val>p.val && root.val>q.val){
                root = root.left;
            }
            else if(root.val<p.val && root.val<q.val){
                root = root.right;
            }else{
                break;
            }
        }
        return root;
    }
    public static void main(String[] args){
        Integer[] treevalues = {6,2,8,0,4,7,9,null,null,3,5};
        TreeNode root = buildTree(treevalues);
        TreeNode p = root.left;
        TreeNode q = root.right;
        TreeNode result = LowestCommonAncestorBST(root,p,q);
        System.out.println(result.val);

        Integer[] treevalues1 = {6,2,8,0,4,7,9,null,null,3,5};
        TreeNode root1 = buildTree(treevalues1);
        TreeNode p1 = root.left;
        TreeNode q1 = root.left.right;
        TreeNode result1 = LowestCommonAncestorBST(root,p1,q1);
        System.out.println(result1.val);
    }
}