import java.util.Arrays;

public class KnapsackZeroOne   {
    public static int KnapsackZeroOneRecursion(int[] weight, int[] value, int n, int maxWeight){
        return recursionHelper(weight,value,n-1,maxWeight);
    }
    private static int recursionHelper(int[] weight, int[] value, int ind, int maxWeight){
        if(ind==0){
            if(weight[0]<=maxWeight){
                return value[0];
            }
            else return 0;
        }
        int notTake = recursionHelper(weight,value,ind-1,maxWeight);
        int take = Integer.MIN_VALUE;
        if(weight[ind]<=maxWeight){
            take = value[ind] + recursionHelper(weight,value,ind-1,maxWeight-weight[ind]);
        }
        return Math.max(notTake,take);
    }
    public static int KnapsackZeroOneMemoization(int[] weight, int[] value, int n, int maxWeight){
        int[][] dp = new int[n][maxWeight+1];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        return memoizationHelper(weight,value,n-1,maxWeight,dp);
    }
    private static int memoizationHelper(int[] weight, int[] value, int ind, int maxWeight, int[][] dp){
        if(ind==0){
            if(weight[0]<=maxWeight){
                return value[0];
            }
            else return 0;
        }
        if(dp[ind][maxWeight]!=-1) return dp[ind][maxWeight];
        int notTake = memoizationHelper(weight,value,ind-1,maxWeight,dp);
        int take = Integer.MIN_VALUE;
        if(weight[ind]<=maxWeight){
            take = value[ind] + memoizationHelper(weight,value,ind-1,maxWeight-weight[ind],dp);
        }
        return dp[ind][maxWeight]=Math.max(notTake,take);
    }
    public static int KnapsackZeroOneTabulation(int[] weight, int[] value, int n, int maxWeight){
        int[][] dp = new int[n][maxWeight+1];
        for(int w = weight[0]; w<=maxWeight;w++){
            dp[0][w] = value[0];
        }
        for(int ind = 1;ind<n;ind++){
            for(int w = 0; w<=maxWeight;w++){
                int notTake = dp[ind-1][w];
                int take = Integer.MIN_VALUE;
                if(weight[ind]<=w){
                    take = value[ind] + dp[ind-1][w-weight[ind]];
                }
                dp[ind][w]=Math.max(notTake,take);
            }
        }
        return dp[n-1][maxWeight];
    }
    public static int KnapsackZeroOneSpaceOptimized(int[] weight, int[] value, int n, int maxWeight){
        int[] prev = new int[maxWeight+1];
        for(int w = weight[0]; w<=maxWeight;w++){
            prev[w] = value[0];
        }
        for(int ind = 1;ind<n;ind++){
            for(int w = maxWeight; w>=0;w--){
                int notTake = prev[w];
                int take = Integer.MIN_VALUE;
                if(weight[ind]<=w){
                    take = value[ind] + prev[w-weight[ind]];
                }
                prev[w]=Math.max(notTake,take);
            }
        }
        return prev[maxWeight];
    }
    public static void main(String[] args){
        int[] weight = {1,2,4,5};
        int[] value = {5,4,8,6};
        int maxWeight = 5;
        int n = weight.length;

        int[] weight1 = {6, 5, 1, 5, 6, 5, 9};
        int[] value1 = {5,3,4,9,6,1,1};
        int maxWeight1 = 63;
        int n1 = weight1.length;
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(KnapsackZeroOneRecursion(weight,value,n,maxWeight));
        System.out.println(KnapsackZeroOneRecursion(weight1,value1,n1,maxWeight1));

        // Using Memoization
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(KnapsackZeroOneMemoization(weight,value,n,maxWeight));
        System.out.println(KnapsackZeroOneMemoization(weight1,value1,n1,maxWeight1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(KnapsackZeroOneTabulation(weight,value,n,maxWeight));
        System.out.println(KnapsackZeroOneTabulation(weight1,value1,n1,maxWeight1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(KnapsackZeroOneSpaceOptimized(weight,value,n,maxWeight));
        System.out.println(KnapsackZeroOneSpaceOptimized(weight1,value1,n1,maxWeight1));
    }
}