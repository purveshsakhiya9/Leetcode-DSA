import java.util.LinkedList;
import java.util.Queue;

public class FlattenBinaryTreeToLinkedList {
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
    public static void FlattenBinaryTreeToLinkedList(TreeNode root){
        if(root==null) return;
        FlattenBinaryTreeToLinkedList(root.left);
        FlattenBinaryTreeToLinkedList(root.right);
        TreeNode tempright = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode current = root;
        while(current.right!=null){
            current=current.right;
        }
        current.right = tempright;

    }
    public static void main(String[] args){
        Integer[] treevalues = {1,2,5,3,4,null,6};
        TreeNode root = buildtree(treevalues);
        FlattenBinaryTreeToLinkedList(root);
        while (root!=null){
            System.out.println(root.val);
            root = root.right;
        }
    }
}