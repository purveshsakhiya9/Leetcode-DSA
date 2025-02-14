import java.util.Arrays;
import java.util.Iterator;

public class FrogJump {
    public static int frogJumpRecursion(int n, int[] heights){
        return recursionHelper(n-1,heights);
    }
    private static int recursionHelper(int ind, int[] heights){
        if(ind==0) return 0;
        int left = recursionHelper(ind-1,heights) + Math.abs(heights[ind]-heights[ind-1]);
        int right = Integer.MAX_VALUE;
        if(ind>1){
            right = recursionHelper(ind-2,heights) + Math.abs(heights[ind]-heights[ind-2]);
        }
        return Math.min(left,right);
    }
    public static int frogJumpMemoization(int n, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return memoizationHelper(n-1,heights,dp);
    }
    private static int memoizationHelper(int ind, int[] heights, int[] dp){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int left = memoizationHelper(ind-1,heights,dp) + Math.abs(heights[ind]-heights[ind-1]);
        int right = Integer.MAX_VALUE;
        if(ind>1){
            right = memoizationHelper(ind-2,heights,dp) + Math.abs(heights[ind]-heights[ind-2]);
        }
        return dp[ind]=Math.min(left,right);
    }
    public static int frogJumpTabulation(int n, int[] heights){
        int[] dp = new int[n+1];
        for(int i = 2;i<=n;i++){
            int oneStep = dp[i-1] + Math.abs(heights[i-1]-heights[i-2]);
            int secondStep = Integer.MAX_VALUE;
            if(i>2){
                secondStep = dp[i-2] + Math.abs(heights[i-1]-heights[i-3]);
            }
            dp[i] = Math.min(oneStep,secondStep);
        }
        return dp[n];
    }
    public static int frogJumpSpaceOptimized(int n, int[] heights){
        int prev = 0;
        int  prev2 = 0;
        for(int i = 2;i<=n;i++){
            int oneStep = prev + Math.abs(heights[i-1]-heights[i-2]);
            int secondStep = Integer.MAX_VALUE;
            if(i>2){
                secondStep = prev2 + Math.abs(heights[i-1]-heights[i-3]);
            }
            int curr = Math.min(oneStep,secondStep);
            prev2 = prev;
            prev= curr;
        }
        return prev;
    }
    public static void main(String[] args){
        int n = 4;
        int[] heights = {10,20,30,10};

        int n1= 3;
        int[] height1 = {10,50,10};
        // Using Recursion
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(frogJumpRecursion(n,heights));
        System.out.println(frogJumpRecursion(n1,height1));

        // Using Memoization
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Memoization: ");
        System.out.println(frogJumpMemoization(n,heights));
        System.out.println(frogJumpMemoization(n1,height1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(frogJumpTabulation(n,heights));
        System.out.println(frogJumpTabulation(n1,height1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(frogJumpSpaceOptimized(n,heights));
        System.out.println(frogJumpSpaceOptimized(n1,height1));

    }
}