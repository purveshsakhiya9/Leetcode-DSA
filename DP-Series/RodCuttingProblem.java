import java.util.Arrays;

public class RodCuttingProblem {
    public static int rodCuttingProblemRecursion(int[] price, int n){
        return recursionHelper(price,n-1,n);
    }
    private static int recursionHelper(int[] price, int ind, int n){
        if(ind==0){
            return n*price[0];
        }
        int notTake = recursionHelper(price,ind-1,n);
        int take = Integer.MIN_VALUE;
        int rodLength = ind+1;
        if(rodLength<=n){
            take = price[ind] + recursionHelper(price,ind,n-rodLength);
        }
        return Math.max(take,notTake);
    }

    public static int rodCuttingProblemMemoization(int[] price, int n){
        int[][] dp = new int[n][n+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(price,n-1,n,dp);
    }
    private static int memoizationHelper(int[] price, int ind, int n, int[][] dp){
        if(ind==0){
            return n*price[0];
        }
        if(dp[ind][n]!=-1) return dp[ind][n];
        int notTake = memoizationHelper(price,ind-1,n,dp);
        int take = Integer.MIN_VALUE;
        int rodLength = ind+1;
        if(rodLength<=n){
            take = price[ind] + memoizationHelper(price,ind,n-rodLength,dp);
        }
        return dp[ind][n] = Math.max(take,notTake);
    }
    public static int rodCuttingProblemTabulation(int[] price, int n){
        int[][] dp = new int[n][n+1];
        for(int i = 0;i<=n;i++){
            dp[0][i]=i*price[0];
        }
        for(int ind = 1;ind<n;ind++){
            for(int N =0;N<=n;N++){
                int notTake = dp[ind-1][N];
                int take = Integer.MIN_VALUE;
                int rod = ind+1;
                if(rod<=N){
                    take = price[ind] + dp[ind][N-rod];
                }
                dp[ind][N] = Math.max(take,notTake);
            }
        }
        return dp[n-1][n];
    }
    public static int rodCuttingProblemSpaceOptimized(int[] price, int n){
        int[] prev = new int[n+1];
        for(int i = 0;i<=n;i++){
            prev[i]=i*price[0];
        }
        for(int ind = 1;ind<n;ind++){
            for(int N =0;N<=n;N++){
                int notTake = prev[N];
                int take = Integer.MIN_VALUE;
                int rod = ind+1;
                if(rod<=N){
                    take = price[ind] + prev[N-rod];
                }
                prev[N] = Math.max(take,notTake);
            }
        }
        return prev[n];
    }
    public static void main(String[] args){
         int[] arr = {2, 5, 7, 8, 10};
         int n = 5;
         int[] arr1 = {3, 5, 8, 9, 10, 17, 17, 20};
         int n1 = 8;
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(rodCuttingProblemRecursion(arr,n));
        System.out.println(rodCuttingProblemRecursion(arr1,n1));

        // Using Memoization
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(rodCuttingProblemMemoization(arr,n));
        System.out.println(rodCuttingProblemMemoization(arr1,n1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(rodCuttingProblemTabulation(arr,n));
        System.out.println(rodCuttingProblemTabulation(arr1,n1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(rodCuttingProblemSpaceOptimized(arr,n));
        System.out.println(rodCuttingProblemSpaceOptimized(arr1,n1));
    }
}