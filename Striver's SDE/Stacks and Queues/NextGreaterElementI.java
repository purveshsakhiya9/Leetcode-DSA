import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementI {
    public static int[] NextGreaterElementI(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            nextGreaterMap.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums1 ={4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] result = NextGreaterElementI(nums1,nums2);
        System.out.print("[");
        for(int i=0;i< result.length-1;i++){
            System.out.print(result[i]+", ");
        }
        System.out.println(result[result.length-1]+"]");
    }
}