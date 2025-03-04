package Trees;

import java.util.*;

public class traversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
    private static class Pair {
        TreeNode node;
        int position;

        Pair(TreeNode node, int position) {
            this.node = node;
            this.position = position;
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

    private static void allTraversal(TreeNode root) {
        Stack<Pair> st = new Stack<Pair>();
        st.push(new Pair(root,1));
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        if(root==null){
            return;
        }
        while(!st.isEmpty()){
            Pair it = st.pop();

            if(it.position==1){
                pre.add(it.node.val);
                it.position++;
                st.push(it);
                if(it.node.left!=null){
                    st.push(new Pair(it.node.left,1));
                }
            }else if(it.position==2){
                in.add(it.node.val);
                it.position++;
                st.push(it);
                if(it.node.right!=null){
                    st.push(new Pair(it.node.right,1));
                }
            }else{
                post.add(it.node.val);
            }
        }
        System.out.println("PreOrder Traversal: ");
        System.out.println(pre);
        System.out.println("InOrder Traversal: ");
        System.out.println(in);
        System.out.println("PostOrder Traversal: ");
        System.out.println(post);
    }
    public static void main(String[] args){
        Integer[] treeValues = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = buildTree(treeValues);
        allTraversal(root);
    }
}