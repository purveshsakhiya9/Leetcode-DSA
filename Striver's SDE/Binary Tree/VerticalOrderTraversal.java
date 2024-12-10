import java.util.*;

public class VerticalOrderTraversal {
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
    private static List<List<Integer>> VerticalOrderTraversal(TreeNode root){
        List<List<Integer>> answer = new ArrayList<>();
        TreeMap<Integer,Map<Integer,List<Integer>>> treemap = new TreeMap<>();
        inorderTraversal(root,treemap,0,0);

        for(var vertical: treemap.entrySet()){
            List<Integer> ans = new ArrayList<>();
            for(var level: vertical.getValue().values()){
                Collections.sort(level);
                ans.addAll(level);
            }
            answer.add(ans);
        }
        return answer;
    }
    private static void inorderTraversal(TreeNode node, TreeMap<Integer,Map<Integer,List<Integer>>> treemap,int column, int row){
        if(node==null) return;
        treemap.computeIfAbsent(column,k->new TreeMap<>()).computeIfAbsent(row,k-> new ArrayList<>()).add(node.val);
        inorderTraversal(node.left,treemap,column-1,row+1);
        inorderTraversal(node.right,treemap,column+1,row+1);
    }
    public static void main(String[] args){
        Integer[] treevalues = {3,9,20,null,null,15,7};
        TreeNode root = buildTree(treevalues);
        List<List<Integer>> result = VerticalOrderTraversal(root);
        System.out.print("[");
        for(int i =0; i<result.size()-1;i++){
            System.out.print(result.get(i)+", ");
        }
        System.out.print(result.get(result.size()-1)+"]");
        System.out.println();
        Integer[] treevalues1 = {1,2,3,4,5,6,7};
        TreeNode root1 = buildTree(treevalues1);
        List<List<Integer>> result1 = VerticalOrderTraversal(root1);
        System.out.print("[");
        for(int i =0; i<result1.size()-1;i++){
            System.out.print(result1.get(i)+", ");
        }
        System.out.print(result1.get(result1.size()-1)+"]");
    }
}