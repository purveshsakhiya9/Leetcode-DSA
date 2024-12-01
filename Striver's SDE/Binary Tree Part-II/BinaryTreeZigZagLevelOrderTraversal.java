import com.sun.source.tree.Tree;

import java.util.*;

public class BinaryTreeZigZagLevelOrderTraversal {
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
        int i =1;
        while (!queue.isEmpty() && i<values.length){
            TreeNode current = queue.poll();
            if(values[i]!=null){
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;
            if(i<values.length && values[i]!=null){
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    public static List<List<Integer>> BinaryTreeZigZagLevelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Deque<TreeNode> queue = new LinkedList<>();
        boolean lefttoright = true;
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currentlevel = new ArrayList<>();
            for(int i = 0; i<size; i++){
                if(lefttoright){
                    TreeNode curent = queue.pollFirst();
                    currentlevel.add(curent.val);
                    if(curent.left!=null){
                        queue.addLast(curent.left);
                    }
                    if(curent.right!=null){
                        queue.addLast(curent.right);
                    }
                }else{
                    TreeNode current = queue.pollLast();
                    currentlevel.add(current.val);
                    if(current.right!=null){
                        queue.addFirst(current.right);
                    }
                    if(current.left!=null){
                        queue.addFirst(current.left);
                    }
                }
            }
            lefttoright=!lefttoright;
            result.add(currentlevel);
        }
        return result;
    }
    public static void main(String[] args){
        Integer[] treevalues = {3,9,20,null,null,15,7};
        TreeNode root = buildtree(treevalues);
        List<List<Integer>> result = BinaryTreeZigZagLevelOrderTraversal(root);
        System.out.print("[");
        for(int i=0; i< result.size()-1; i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.print(result.get(result.size()-1)+"]");
    }
}