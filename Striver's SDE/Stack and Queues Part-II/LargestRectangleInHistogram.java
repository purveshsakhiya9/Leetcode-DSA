import java.util.Stack;

public class LargestRectangleInHistogram {
    public int LargestRectangleInHistogram(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // When i == n, set current height to 0 to process any remaining bars in the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            // While the stack is not empty and the current height is less than the height of the bar at stack top
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("The largest rectangle area is: " + solution.LargestRectangleInHistogram(heights));  // Output should be 10
    }
}
