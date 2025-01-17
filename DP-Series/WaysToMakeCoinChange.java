import java.util.Arrays;

public class WaysToMakeCoinChange {
    public static long waysToMakeCoinChangeRecursion(int denominations[], int value){
        return recursionHelper(denominations,value,denominations.length-1);
    }
    private static long recursionHelper(int[] num, int x, int ind){
        if(ind==0){
            if(x%num[ind]==0) return 1;
            return 0;
        }
        long notTake = recursionHelper(num,x,ind-1);
        long take = 0;
        if(num[ind]<=x){
            take = recursionHelper(num,x-num[ind],ind);
        }
        return take+notTake;
    }
    public static long waysToMakeCoinChangeMemoization(int denominations[], int value){
        long[][] dp = new long[denominations.length][value+1];
        for(long[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(denominations,value,denominations.length-1,dp);
    }
    private static long memoizationHelper(int[] num, int x, int ind, long[][] dp){
        if(ind==0){
            if(x%num[ind]==0) return 1;
            return 0;
        }
        if(dp[ind][x]!=-1) return dp[ind][x];
        long notTake = memoizationHelper(num,x,ind-1,dp);
        long take = 0;
        if(num[ind]<=x){
            take =memoizationHelper(num,x-num[ind],ind,dp);
        }
        return dp[ind][x] = take+notTake;
    }
    public static long waysToMakeCoinChangeTabulation(int denominations[], int value){
        long[][] dp = new long[denominations.length][value+1];
        for(int t = 0; t<=value;t++){
            if(t%denominations[0]==0){
                dp[0][t] = 1;
            }else{
                dp[0][t] = 0;
            }
        }
        for(int ind=1;ind<denominations.length;ind++){
            for(int t = 0;t<=value;t++){
                long notTake = dp[ind-1][t];
                long take = 0;
                if(denominations[ind]<=t){
                    take = dp[ind][t-denominations[ind]];
                }
                dp[ind][t] = take+notTake;
            }
        }
        return dp[dp.length-1][value];
    }
    public static long waysToMakeCoinChangeSpaceOptimized(int denominations[], int value){
        long[] prev = new long[value+1];
        for(int t = 0; t<=value;t++){
            if(t%denominations[0]==0){
                prev[t] = 1;
            }else{
                prev[t] = 0;
            }
        }
        for(int ind=1;ind<denominations.length;ind++){
            long[] curr = new long[value+1];
            for(int t = 0;t<=value;t++){
                long notTake = prev[t];
                long take = 0;
                if(denominations[ind]<=t){
                    take = curr[t-denominations[ind]];
                }
                curr[t] = take+notTake;
            }
            prev=curr;
        }
        return prev[value];
    }
    public static void main(String[] args){
        int[] arr = {1,2,3};
        int target = 4;
        int[] arr1 = {5,3,2};
        int target1 = 1;
        //Using Recursion
        // Time Complexity: way greater than O(2^N) == exponential
        // Space Complexity: way greater than O(N*target) +O(N)
        System.out.println("Using Recursion: ");
        System.out.println(waysToMakeCoinChangeRecursion(arr,target));
        System.out.println(waysToMakeCoinChangeRecursion(arr1,target1));

        // Using Memoization
        // Time Complexity: O(N*target)
        // Space Complexity: O(N*target) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(waysToMakeCoinChangeMemoization(arr,target));
        System.out.println(waysToMakeCoinChangeMemoization(arr1,target1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(waysToMakeCoinChangeTabulation(arr,target));
        System.out.println(waysToMakeCoinChangeTabulation(arr1,target1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(waysToMakeCoinChangeSpaceOptimized(arr,target));
        System.out.println(waysToMakeCoinChangeSpaceOptimized(arr1,target1));

    }
}