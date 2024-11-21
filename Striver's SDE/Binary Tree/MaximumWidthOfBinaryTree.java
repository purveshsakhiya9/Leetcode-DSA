import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Pair {
        TreeNode node;
        int position;

        Pair(TreeNode node, int position) {
            this.node = node;
            this.position = position;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = queue.peek().position;
            int end = queue.peek().position;

            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int position = current.position;
                end = position;
                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * position));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * position + 1));
                }
            }
            int width = end - start + 1;
            maxWidth = Math.max(maxWidth, width);
        }
        return maxWidth;
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
    public static void main(String[] args){
        Integer[] treeValues1 = {1,3,2,5,3,null,9};
        TreeNode root1 = buildTree(treeValues1);
        System.out.println(widthOfBinaryTree(root1));

        Integer[] treeValues2 = {1,3,2,5,null,null,9,6,null,7};
        TreeNode root2 = buildTree(treeValues2);
        System.out.println(widthOfBinaryTree(root2));

        Integer[] treeValues3 = {1,3,2,5};
        TreeNode root3 = buildTree(treeValues3);
        System.out.println(widthOfBinaryTree(root3));
    }
}