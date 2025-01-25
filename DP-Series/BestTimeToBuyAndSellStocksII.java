import java.util.Arrays;

public class BestTimeToBuyAndSellStocksII {
    public static long bestTimeToBuyAndSellStocksIIRecursion(int n, long[] arr){
        return recursionHelper(0,1, arr, n);
    }
    private static long recursionHelper(int ind, int buy, long[] values, int n){
        if(ind==n) return 0;
        long profit;
        if(buy==1){
            profit = Math.max(-values[ind]+recursionHelper(ind+1,0,values,n), recursionHelper(ind+1,1,values,n));
        }else{
            profit = Math.max(values[ind]+recursionHelper(ind+1,1,values,n),recursionHelper(ind+1,0,values,n));
        }
        return profit;
    }
    public static long bestTimeToBuyAndSellStocksIIMemoization (int n, long[] values) {
        long[][] dp = new long[n][2];
        for(long[] rows:dp){
            Arrays.fill(rows,-1);
        }
        return memoizationHelper(0,1, values, n,dp);
    }
    private static long memoizationHelper(int ind, int buy, long[] values, int n,long[][] dp){
        if(ind==n) return 0;
        if(dp[ind][buy]!=-1) return dp[ind][buy];
        long profit;
        if(buy==1){
            profit = Math.max(-values[ind]+memoizationHelper(ind+1,0,values,n,dp), memoizationHelper(ind+1,1,values,n,dp));
        }else{
            profit = Math.max(values[ind]+memoizationHelper(ind+1,1,values,n,dp),memoizationHelper(ind+1,0,values,n,dp));
        }
        return dp[ind][buy]=profit;
    }
    public static long bestTimeToBuyAndSellStocksIITabulation(int n, long[] values){
        long[][] dp = new long[n+1][2];
        dp[n][0] = dp[n][1] = 0;
        for(int ind = n-1;ind>=0;ind--){
            for(int buy = 0; buy<=1;buy++){
                long profit;
                if(buy==1){
                    profit = Math.max(-values[ind]+dp[ind+1][0],dp[ind+1][1]);
                }else{
                    profit = Math.max(values[ind]+dp[ind+1][1],dp[ind+1][0]);
                }
                dp[ind][buy]=profit;
            }
        }
        return dp[0][1];
    }
    public static long bestTimeToBuyAndSellStocksIISpaceOptimized(int n, long[] values){
        long[] prev = new long[2];
        prev[0] = prev[1] = 0;
        for(int ind = n-1;ind>=0;ind--){
            long[] curr = new long[2];
            for(int buy = 0; buy<=1;buy++){
                long profit;
                if(buy==1){
                    profit = Math.max(-values[ind]+prev[0],prev[1]);
                }else{
                    profit = Math.max(values[ind]+prev[1],prev[0]);
                }
                curr[buy]=profit;
            }
            prev = curr;
        }
        return prev[1];
    }

    public static void main(String[] args){
        long[] arr = {1,2,3,4,5,6,7};
        long[] arr1 = {7,6,5,4,3,2,1};
        // Using Recursion:
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(bestTimeToBuyAndSellStocksIIRecursion(arr.length, arr));
        System.out.println(bestTimeToBuyAndSellStocksIIRecursion(arr1.length,arr1));

        // Using Memoization:
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Memoization: ");
        System.out.println(bestTimeToBuyAndSellStocksIIMemoization(arr.length, arr));
        System.out.println(bestTimeToBuyAndSellStocksIIMemoization(arr1.length,arr1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(bestTimeToBuyAndSellStocksIITabulation(arr.length,arr));
        System.out.println(bestTimeToBuyAndSellStocksIITabulation(arr1.length,arr1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(bestTimeToBuyAndSellStocksIISpaceOptimized(arr.length,arr));
        System.out.println(bestTimeToBuyAndSellStocksIISpaceOptimized(arr1.length,arr1));
    }
}