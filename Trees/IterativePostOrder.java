package Trees;

import com.sun.source.tree.Tree;

import java. util.*;
public class IterativePostOrder {
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
    public static List<Integer> PostorderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }
        PostorderTraversal(root.left);
        PostorderTraversal(root.right);
        result.add(root.val);
        return result;
    }
    public static List<Integer> IterativePostorderTraversal(TreeNode root){
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        List<Integer> postOrder = new ArrayList<>();
        if(root==null) return postOrder;
        st1.push(root);
        while(!st1.isEmpty()){
            root = st1.pop();
            st2.add(root);
            if(root.left!=null) st1.push(root.left);
            if(root.right!=null) st1.push(root.right);
        }
        while(!st2.isEmpty()){
            postOrder.add(st2.pop().val);
        }
        return postOrder;
    }

    public static List<Integer> OptimizedApproach(TreeNode root){
        List<Integer> postOrder = new ArrayList<>();
        if(root==null) return postOrder;
        Stack<TreeNode> st1 = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;
        while(curr!=null || !st1.isEmpty()){
            while(curr!=null){
                st1.push(curr);
                curr = curr.left;
            }
            curr = st1.peek();
            if(curr.right==null || curr.right == prev){
                postOrder.add(curr.val);
                st1.pop();
                prev = curr;
                curr = null;
            }else{
                curr = curr.right;
            }
        }
        return postOrder;
    }

    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);
        System.out.println("Using Recursive Approach: ");
        List<Integer> result = PostorderTraversal(root);
        System.out.print("[");
        for(int i = 0; i<result.size()-1;i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.println(result.getLast()+"]");

        System.out.println("Using Iterative Approach: ");
        List<Integer> result1 = IterativePostorderTraversal(root);
        System.out.print("[");
        for(int i = 0; i<result1.size()-1;i++){
            System.out.print(result1.get(i)+", ");
        }
        System.out.println(result1.getLast()+"]");

        System.out.println("Using Optimized Approach: ");
        List<Integer> result2 = OptimizedApproach(root);
        System.out.print("[");
        for(int i = 0; i<result2.size()-1;i++){
            System.out.print(result2.get(i)+", ");
        }
        System.out.print(result2.getLast()+"]");
    }
}