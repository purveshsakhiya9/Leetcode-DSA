import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
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
    public static List<List<Integer>> LevelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> ans = new ArrayList<>();
            for(int i = 0; i<size; i++){
                TreeNode node = queue.poll();
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                ans.add(node.val);
            }
            result.add(ans);
        }
        return result;
    }

    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,6,null,null,null,7,null,null,8,null,null};
        TreeNode root = buildTree(treeValues);
        List<List<Integer>> result = LevelOrderTraversal(root);
        System.out.print("[");
        for(int i = 0; i< result.size();i++){
            System.out.print(result.get(i));
            if (i!= result.size()-1){
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}