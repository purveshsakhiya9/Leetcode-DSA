import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
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
    public static TreeNode buildtree(Integer[] preorder, Integer[] inorder){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length-1,preorder,0,preorder.length-1,map);
    }
    public static TreeNode build(Integer[] inorder, int inStart, int inEnd, Integer[] preorder,int preStart, int preEnd, HashMap<Integer,Integer> map){
        if(inStart>inEnd || preStart>preEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRootIndex = map.get(root.val);
        int leftsubtree = inRootIndex-inStart;
        root.left = build(inorder,inStart, inRootIndex-1,preorder,preStart+1,preStart+leftsubtree,map);
        root.right = build(inorder, inRootIndex+1,inEnd,preorder,preStart+leftsubtree+1,preEnd,map);
        return root;
    }
    public static List<Integer> inoder = new ArrayList<>();
    public static List<Integer> InOrderTraversal(TreeNode root) {
        if(root == null){
            return inoder;
        }
        InOrderTraversal(root.left);
        inoder.add(root.val);
        InOrderTraversal(root.right);
        return inoder;
    }
    public static List<Integer> preoder = new ArrayList<>();
    public static List<Integer> PreOrderTraversal(TreeNode root) {
        if(root == null){
            return preoder;
        }
        preoder.add(root.val);
        PreOrderTraversal(root.left);
        PreOrderTraversal(root.right);
        return preoder;
    }
    public static void main(String[] args){
        Integer[] inordervalues = {9,3,15,20,7};
        Integer[] preordervalues = {3,9,20,15,7};

        TreeNode root = buildtree(preordervalues,inordervalues);
        System.out.println(InOrderTraversal(root));
        System.out.println(PreOrderTraversal(root));

    }
}