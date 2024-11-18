import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PreOrderTraversal {
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

    public static List<Integer> result = new ArrayList<>();
    public static List<Integer> PreOrderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }
        result.add(root.val);
        PreOrderTraversal(root.left);
        PreOrderTraversal(root.right);
        return result;
    }

    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);
        List<Integer> result = PreOrderTraversal(root);
        System.out.print("[");
        for(int i = 0; i<result.size()-1;i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.print(result.get(result.size()-1)+"]");
    }

}