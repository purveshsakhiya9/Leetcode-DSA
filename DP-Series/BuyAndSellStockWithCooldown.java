import java.util.Arrays;

public class BuyAndSellStockWithCooldown {
    public static int buyAndSellStockWithCooldownRecursion(int[] prices){
        return recursionHelper(0,1, prices, prices.length);
    }
    private static int recursionHelper(int ind, int buy, int[] values, int n){
        if(ind>=n) return 0;
        int profit;
        if(buy==1){
            profit = Math.max(-values[ind]+recursionHelper(ind+1,0,values,n), recursionHelper(ind+1,1,values,n));
        }else{
            profit = Math.max(values[ind]+recursionHelper(ind+2,1,values,n),recursionHelper(ind+1,0,values,n));
        }
        return profit;
    }
    public static int buyAndSellStockWithCooldownMemoization(int[] prices){
        int[][] dp = new int[prices.length][2];
        for(int[] rows:dp){
            Arrays.fill(rows,-1);
        }
        return memoizationHelper(0,1, prices,dp);
    }
    private static int memoizationHelper(int ind, int buy, int[] prices,int[][] dp){
        if(ind>=prices.length) return 0;
        if(dp[ind][buy]!=-1) return dp[ind][buy];
        int profit;
        if(buy==1){
            profit = Math.max(-prices[ind]+memoizationHelper(ind+1,0,prices,dp), memoizationHelper(ind+1,1,prices,dp));
        }else{
            profit = Math.max(prices[ind]+memoizationHelper(ind+2,1,prices,dp),memoizationHelper(ind+1,0,prices,dp));
        }
        return dp[ind][buy]=profit;
    }
    public static int buyAndSellStockWithCooldownTabulation(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+2][2];
        for(int ind = n-1;ind>=0;ind--){
            for(int buy = 0; buy<=1;buy++){
                int profit;
                if(buy==1){
                    profit = Math.max(-prices[ind]+dp[ind+1][0],dp[ind+1][1]);
                }else{
                    profit = Math.max(prices[ind]+dp[ind+2][1],dp[ind+1][0]);
                }
                dp[ind][buy]=profit;
            }
        }
        return dp[0][1];
    }
    public static int buyAndSellStockWithCooldownSpaceOptimized(int[] prices){
        int n = prices.length;
        int[] front1 = new int[2];
        int[] front2 = new int[2];
        for(int ind = n-1;ind>=0;ind--){
            int[] curr = new int[2];
            for(int buy = 0; buy<=1;buy++){
                int profit;
                if(buy==1){
                    profit = Math.max(-prices[ind]+front1[0],front1[1]);
                }else{
                    profit = Math.max(prices[ind]+front2[1],front1[0]);
                }
                curr[buy]=profit;
            }
            front2 = front1;
            front1 = curr;
        }
        return front1[1];
    }
    public static void main(String[] args){
        int[] prices = {1,2,3,4};
        int[] prices1 = {5,4,3};
        // Using Recursion:
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(buyAndSellStockWithCooldownRecursion(prices));
        System.out.println(buyAndSellStockWithCooldownRecursion(prices1));

        // Using Memoization:
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Memoization: ");
        System.out.println(buyAndSellStockWithCooldownMemoization(prices));
        System.out.println(buyAndSellStockWithCooldownMemoization(prices1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(buyAndSellStockWithCooldownTabulation(prices));
        System.out.println(buyAndSellStockWithCooldownTabulation(prices1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(buyAndSellStockWithCooldownSpaceOptimized(prices));
        System.out.println(buyAndSellStockWithCooldownSpaceOptimized(prices1));

    }
}