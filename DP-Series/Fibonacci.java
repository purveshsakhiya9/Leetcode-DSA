import java.util.Arrays;
public class Fibonacci {
    private static int fibonacciRecursion(int n) {
        if(n<=1){
            return n;
        }
        return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
    }

    private static int fibonacciMemoization(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return memoizationHelper(n,dp);
    }
    public static int memoizationHelper(int n, int[] dp){
        if(n<=1){
            return n;
        }
        if(dp[n]!=-1) return dp[n];
        return dp[n] = memoizationHelper(n-1,dp) + memoizationHelper(n-2,dp);
    }

    private static int tabulationRecursion(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    private static int spaceOptimizedRecursion(int n) {
        int prev = 1;
        int secondPrev = 0;
        for(int i = 2;i<=n;i++){
            int curr = prev + secondPrev;
            secondPrev = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args){
        // using Recursion
        // Time Complexity: O(2^n)
        // Space Complexity: O(n)
        System.out.println(fibonacciRecursion(7));

        // using memoization
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        System.out.println(fibonacciMemoization(8));

        // using tabulation
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        System.out.println(tabulationRecursion(9));

        // space optimization
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        System.out.println(spaceOptimizedRecursion(10));
    }
}