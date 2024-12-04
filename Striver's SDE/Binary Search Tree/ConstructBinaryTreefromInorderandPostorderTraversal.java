import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstructBinaryTreefromInorderandPostorderTraversal {
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
    public static TreeNode buildtree(Integer[] inorder, Integer[] postorder){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length-1, postorder,0,postorder.length-1,map);
    }
    public static TreeNode build(Integer[] inorder,int inStart, int inEnd, Integer[] postorder, int postStart, int postEnd, HashMap<Integer,Integer> map){
        if(inStart>inEnd || postEnd<postStart){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRootIndex = map.get(root.val);
        int leftsubtree = inRootIndex-inStart;
        root.left = build(inorder,inStart,inRootIndex-1,postorder,postStart,postStart+leftsubtree-1,map);
        root.right = build(inorder,inRootIndex+1,inEnd,postorder,postStart+leftsubtree,postEnd-1,map);
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
    public static List<Integer> postorder = new ArrayList<>();
    public static List<Integer> PostOrderTraversal(TreeNode root) {
        if(root == null){
            return postorder;
        }
        PostOrderTraversal(root.left);
        PostOrderTraversal(root.right);
        postorder.add(root.val);
        return postorder;
    }


    public static void main(String[] args){
        Integer[] inorderValues = {9,3,15,20,7};
        Integer[] postordervalues = {9,15,7,20,3};
        TreeNode root = buildtree(inorderValues,postordervalues);
        System.out.println(InOrderTraversal(root));
        System.out.println(PostOrderTraversal(root));
    }


}