import java.util.Arrays;

public class BuyTimeToBuyAndSellStocksWithTransactionFee {
    public static int buyTimeToBuyAndSellStocksWithTransactionFeeRecursion(int[] prices, int n, int fee){
        return recursionHelper(0,1, prices, n,fee);
    }
    private static int recursionHelper(int ind, int buy, int[] values, int n,int fee){
        if(ind==n) return 0;
        int profit;
        if(buy==1){
            profit = Math.max(-values[ind]+recursionHelper(ind+1,0,values,n,fee)-fee, recursionHelper(ind+1,1,values,n,fee));
        }else{
            profit = Math.max(values[ind]+recursionHelper(ind+1,1,values,n,fee),recursionHelper(ind+1,0,values,n,fee));
        }
        return profit;
    }
    public static int buyTimeToBuyAndSellStocksWithTransactionFeeMemoization(int[] prices, int n, int fee){
        int[][] dp = new int[n][2];
        for(int[] rows:dp){
            Arrays.fill(rows,-1);
        }
        return memoizationHelper(0,1, prices, n,dp,fee);
    }
    private static int memoizationHelper(int ind, int buy, int[] prices, int n,int[][] dp,int fee){
        if(ind==n) return 0;
        if(dp[ind][buy]!=-1) return dp[ind][buy];
        int profit;
        if(buy==1){
            profit = Math.max(-prices[ind]+memoizationHelper(ind+1,0,prices,n,dp,fee)-fee, memoizationHelper(ind+1,1,prices,n,dp,fee));
        }else{
            profit = Math.max(prices[ind]+memoizationHelper(ind+1,1,prices,n,dp,fee),memoizationHelper(ind+1,0,prices,n,dp,fee));
        }
        return dp[ind][buy]=profit;
    }
    public static int buyTimeToBuyAndSellStocksWithTransactionFeeTabulation(int[] prices, int n, int fee){
        int[][] dp = new int[n+1][2];
        dp[n][0] = dp[n][1] = 0;
        for(int ind = n-1;ind>=0;ind--){
            for(int buy = 0; buy<=1;buy++){
                int profit;
                if(buy==1){
                    profit = Math.max(-prices[ind]+dp[ind+1][0]-fee,dp[ind+1][1]);
                }else{
                    profit = Math.max(prices[ind]+dp[ind+1][1],dp[ind+1][0]);
                }
                dp[ind][buy]=profit;
            }
        }
        return dp[0][1];
    }
    public static int buyTimeToBuyAndSellStocksWithTransactionFeeSpaceOptimized(int[] prices, int n, int fee){
        int[] prev = new int[2];
        prev[0] = prev[1] = 0;
        for(int ind = n-1;ind>=0;ind--){
            int[] curr = new int[2];
            for(int buy = 0; buy<=1;buy++){
                int profit;
                if(buy==1){
                    profit = Math.max(-prices[ind]+prev[0]-fee,prev[1]);
                }else{
                    profit = Math.max(prices[ind]+prev[1],prev[0]);
                }
                curr[buy]=profit;
            }
            prev = curr;
        }
        return prev[1];
    }

    public static void main(String[] main){
        int[] prices = {1,2,3};
        int fee = 1;
        int[] prices1 = {1,3,5,6};
        int fee1 = 2;

        // Using Recursion
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeRecursion(prices,prices.length,fee));
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeRecursion(prices1,prices1.length,fee1));

        // Using Memoization
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Memoization: ");
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeMemoization(prices,prices.length,fee));
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeMemoization(prices1,prices1.length,fee1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeTabulation(prices,prices.length,fee));
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeTabulation(prices1,prices1.length,fee1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeSpaceOptimized(prices,prices.length,fee));
        System.out.println(buyTimeToBuyAndSellStocksWithTransactionFeeSpaceOptimized(prices1,prices1.length,fee1));

    }
}