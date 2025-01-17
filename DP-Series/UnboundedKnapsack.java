import java.util.Arrays;

public class UnboundedKnapsack {
    public static int unboundedKnapsackRecursion(int[] weight,int[] value, int n, int w){
        return recursionHelper(weight,value,n-1,w);
    }
    private static int recursionHelper(int[] weight, int[] value, int ind, int maxWeight){
        if(ind==0){
            if(weight[ind]<=maxWeight){
                return (maxWeight/weight[0])*value[0];
            }else return 0;
        }
        int notTake = recursionHelper(weight,value,ind-1,maxWeight);
        int take = Integer.MIN_VALUE;
        if(weight[ind]<=maxWeight){
            take = value[ind] + recursionHelper(weight,value,ind,maxWeight-weight[ind]);
        }
        return Math.max(take,notTake);
    }
    public static int unboundedKnapsackMemoization(int[] weight, int[] value, int n,int w){
        int[][] dp = new int[n][w+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(weight,value,n-1,w,dp);
    }
    private static int memoizationHelper(int[] weight, int[] value, int ind, int maxWeight, int[][] dp){
        if(ind==0){
            if(weight[ind]<=maxWeight){
                return value[ind] + memoizationHelper(weight,value,ind,maxWeight-weight[ind],dp);
            }
            else return 0;
        }
        if(dp[ind][maxWeight]!=-1) return dp[ind][maxWeight];
        int notTake = memoizationHelper(weight,value,ind-1,maxWeight,dp);
        int take = 0;
        if(weight[ind]<=maxWeight){
            take = value[ind] + memoizationHelper(weight,value,ind,maxWeight-weight[ind],dp);
        }
        return dp[ind][maxWeight] = Math.max(take,notTake);
    }
    public static int unboundedKnapsackTabulation(int[] weight,int[] value, int n, int w){
        int[][] dp = new int[n][w+1];
        for(int wt = 0; wt<=w;wt++){
            dp[0][wt] = ((int)(wt/weight[0]))*value[0];
        }
        for(int ind = 1; ind<n;ind++){
            for(int wt = 0;wt<=w;wt++){
                int notTake = dp[ind-1][wt];
                int take = 0;
                if(weight[ind]<=wt){
                    take = value[ind]+dp[ind][wt-weight[ind]];
                }
                dp[ind][wt] = Math.max(take, notTake);
            }
        }
        return dp[n-1][w];
    }
    public static int unboundedKnapsackSpaceOptimized(int[] weight,int[] value, int n, int w){
        int[] prev = new int[w+1];
        for(int wt = 0; wt<=w;wt++){
            prev[wt] = ((int)(wt/weight[0]))*value[0];
        }
        for(int ind = 1; ind<n;ind++){
            int[] curr = new int[w+1];
            for(int wt = 0;wt<=w;wt++){
                int notTake = prev[wt];
                int take = 0;
                if(weight[ind]<=wt){
                    take = value[ind]+curr[wt-weight[ind]];
                }
                curr[wt] = Math.max(take, notTake);
            }
            prev=curr;
        }
        return prev[w];
    }
    public static void main(String[] args){
        int[] weight = {2,4,6};
        int[] value = {5,11,13};
        int n = 3;
        int maxWeight = 10;

        int[] weight1 = {7,2,4};
        int[] value1 = {5,10,20};
        int n1 = 3;
        int maxWeight1 = 15;
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(unboundedKnapsackRecursion(weight,value,n,maxWeight));
        System.out.println(unboundedKnapsackRecursion(weight1,value1,n1,maxWeight1));

        // Using Memoization
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(unboundedKnapsackMemoization(weight,value,n,maxWeight));
        System.out.println(unboundedKnapsackMemoization(weight1,value1,n1,maxWeight1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(unboundedKnapsackTabulation(weight,value,n,maxWeight));
        System.out.println(unboundedKnapsackTabulation(weight1,value1,n1,maxWeight1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(unboundedKnapsackSpaceOptimized(weight,value,n,maxWeight));
        System.out.println(unboundedKnapsackSpaceOptimized(weight1,value1,n1,maxWeight1));
    }
}