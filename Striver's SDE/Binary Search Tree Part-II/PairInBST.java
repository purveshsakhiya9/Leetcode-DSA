import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PairInBST {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode (int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static TreeNode buildTree(Integer[] values){
        if(values==null || values.length==0){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(values[0]);
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<values.length){
            TreeNode current = queue.poll();
            if(values[i]!=null){
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            if(i<values.length && values!=null){
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    private static HashSet<Integer> set = new HashSet<Integer>();
    public static boolean PairInBST(TreeNode root, int k){
        if(root==null) return false;
        if(set.contains(k-root.val)){
            return true;
        }
        set.add(root.val);
        return PairInBST(root.left,k) || PairInBST(root.right, k);
    }
    public static void main(String[] args){
        Integer[] treevalues = {5,3,6,2,4,null,7};
        TreeNode root = buildTree(treevalues);
        System.out.println(PairInBST(root,9));
        System.out.println(PairInBST(root,28));

    }
}