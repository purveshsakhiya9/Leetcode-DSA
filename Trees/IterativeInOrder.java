package Trees;
import java.util.*;

public class IterativeInOrder {
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
    public static List<Integer> InorderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }
        InorderTraversal(root.left);
        result.add(root.val);
        InorderTraversal(root.right);
        return result;
    }
    public static List<Integer> IterativeInOrder(TreeNode root){
        List<Integer> inorder = new ArrayList<>();
        if(root==null) return inorder;
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;
        while(true){
            if(node!=null){
                st.push(node);
                node = node.left;
            }else{
                if(st.empty()){
                    break;
                }
                node = st.pop();
                inorder.add(node.val);
                node = node.right;

            }
        }
        return inorder;
    }

    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);

        System.out.println("Using Recursive Approach: ");
        List<Integer> result = InorderTraversal(root);
        System.out.print("[");
        for(int i = 0; i<result.size()-1;i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.print(result.getLast()+"]");
        System.out.println();
        System.out.println("Using Iterative Approach: ");
        List<Integer> result1 = IterativeInOrder(root);
        System.out.print("[");
        for(int i = 0; i<result1.size()-1;i++){
            System.out.print(result1.get(i)+", ");
        }
        System.out.print(result1.getLast()+"]");
    }
}