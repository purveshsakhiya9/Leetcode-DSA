import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SortedArrayInBST {
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
    public static TreeNode build(int[] nums){
        return calculate(nums,0,nums.length-1);
    }
    public static TreeNode calculate(int[] nums,int start,int end){
        if(start>end) return null;
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = calculate(nums,start,mid-1);
        root.right = calculate(nums,mid+1,end);
        return root;
    }
    public static boolean BalancedBinaryTree(TreeNode root){
        if(height(root) == -1) return false;
        return true;
    }
    public static int height(TreeNode root){
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if(left==-1 || right ==-1 || Math.abs(left-right)>1) return -1;
        return Math.max(left,right)+1;
    }
    public static void main(String[] args){
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = build(nums);
        System.out.println(BalancedBinaryTree(root));
    }
}