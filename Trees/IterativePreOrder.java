package Trees;
import java.util.*;
public class IterativePreOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }

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
    public static List<Integer> PreorderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }
        result.add(root.val);
        PreorderTraversal(root.left);
        PreorderTraversal(root.right);
        return result;
    }
    public static List<Integer> IterativePreOrder(TreeNode root){
        List<Integer> preorder = new ArrayList<>();
        if(root==null) return preorder;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.empty()){
            root = st.pop();
            preorder.add(root.val);
            if(root.right!=null){
                st.push(root.right);
            }
            if(root.left!=null){
                st.push(root.left);
            }
        }
        return preorder;
    }

    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);
        System.out.println("Using Recursive Approach: ");

        List<Integer> result = PreorderTraversal(root);
        System.out.print("[");
        for(int i = 0; i<result.size()-1;i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.print(result.getLast()+"]");
        System.out.println();
        System.out.println("Using Iterative Approach: ");
        List<Integer> result1 = IterativePreOrder(root);
        System.out.print("[");
        for(int i = 0; i<result1.size()-1;i++){
            System.out.print(result1.get(i)+", ");
        }
        System.out.print(result1.getLast()+"]");
    }
}