public class ConstructBinarySearchTreeFromPreorderTraversal {
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
    public static TreeNode insert(TreeNode root,int val){
        if(root==null) return new TreeNode(val);
        if(root.val<val){
            root.right = insert(root.right,val);
        }else{
            root.left = insert(root.left,val);
        }
        return root;
    }
    public static TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for(int i = 1; i<preorder.length;i++){
            insert(root,preorder[i]);
        }
        return root;
    }
    public static void main(String[] args){
        int[] preordervalues = {8,5,1,7,10,12};
        TreeNode root = bstFromPreorder(preordervalues);
    }
}