import java.util.Arrays;

public class MatrixChainMultiplication {
    public static int matrixChainMultiplicationRecursion(int[] arr,int n){
        return recursionHelper(1,n-1, arr);
    }
    private static int recursionHelper(int i, int j, int[] arr){
        if(i==j) return 0;
        int min = Integer.MAX_VALUE;
        for(int k = i;k<j;k++){
            int steps = arr[i-1]*arr[k]*arr[j] + recursionHelper(i,k,arr) + recursionHelper(k+1,j,arr);
            min = Math.min(steps,min);
        }
        return min;
    }
    public static int matrixChainMultiplicationMemoization(int[] arr,int n){
        int[][] dp = new int[n][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(1,n-1,arr,dp);
    }
    private static int memoizationHelper(int i, int j, int[] arr, int[][] dp){
        if(i==j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i;k<j;k++){
            int steps = arr[i-1]*arr[k]*arr[j] + memoizationHelper(i,k,arr,dp) + memoizationHelper(k+1,j,arr,dp);
            min = Math.min(steps,min);
        }
        return dp[i][j] = min;
    }
    public static int matrixChainMultiplicationTabulation(int[] arr, int n){
        int[][] dp = new int[n][n];
        for(int i = 1;i<n;i++) dp[i][i] =0;
        for(int i = n-1;i>=1;i--){
            for(int j = i+1;j<n;j++){
                int min = Integer.MAX_VALUE;
                for(int k = i;k<j;k++){
                    int steps = arr[i-1]*arr[k]*arr[j] + dp[i][k] + dp[k+1][j];
                    min = Math.min(min,steps);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n-1];
    }
    public static void main(String[] args){
        int[] arr = {4, 5, 3, 2};
        int[] arr1 = {10, 15, 20, 25};
        // Using Recursion
        // Time Complexity: O(N*N*N)
        // Space Complexity: O(N*N) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(matrixChainMultiplicationRecursion(arr,arr.length));
        System.out.println(matrixChainMultiplicationRecursion(arr1, arr1.length));

        // Using Memoization
        // Time Complexity: O(N*N*N)
        // Space Complexity: O(N*N) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(matrixChainMultiplicationMemoization(arr,arr.length));
        System.out.println(matrixChainMultiplicationMemoization(arr1, arr1.length));

        // Using Tabulation
        // Time Complexity: O(N*N*N)
        // Space Complexity: O(N*N)
        System.out.println("Using Tabulation: ");
        System.out.println(matrixChainMultiplicationTabulation(arr,arr.length));
        System.out.println(matrixChainMultiplicationTabulation(arr1,arr1.length));

    }
}