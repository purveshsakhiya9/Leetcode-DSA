import java.util.Arrays;

public class ClimbingStairs {
    private static int climbingStairsRecursion(int n) {
        if(n<=1) return 1;
        return climbingStairsRecursion(n-1) + climbingStairsRecursion(n-2);
    }


    public static int climbingStairsMemoization(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return climbingStairsHelper(n,dp);
    }


    public static int climbingStairsHelper(int n, int[] dp){
        if(n<=1) return 1;
        if(dp[n]!=-1) return dp[n];
        return dp[n] = climbingStairsHelper(n-1,dp) + climbingStairsHelper(n-2,dp);
    }


    public static int climbingStairsTabulation(int n){
        if(n<=1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }


    public static int climbingStairsSpaceOptimized(int n){
        int prev = 1;
        int secondPrev = 1;
        for(int i = 2;i<=n;i++){
            int curr = prev + secondPrev;
            secondPrev = prev;
            prev = curr;
        }
        return prev;
    }


    public static void main(String[] args){
        // using recursion
        // Time Complexity: O(2^n)
        // Space Complexity: O(n)
        System.out.println(climbingStairsRecursion(5));

        // using memoization
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        System.out.println(climbingStairsMemoization(5));

        // using tabulation
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        System.out.println(climbingStairsTabulation(5));

        //using space optimization
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        System.out.println(climbingStairsSpaceOptimized(5));
    }
}