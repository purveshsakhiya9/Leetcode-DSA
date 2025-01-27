import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV {
    public static int buyAndSellStocksIVRecursion(int[] prices,int k){
        return recursionHelper(0,1,k,prices);
    }
    private static int recursionHelper(int ind, int buy, int cap,int[] prices){
        if(cap==0) return 0;
        if(ind==prices.length) return 0;
        int profit;
        if(buy==1){
            profit = Math.max(-prices[ind]+recursionHelper(ind+1,0,cap,prices),
                    recursionHelper(ind+1,1,cap,prices));
        }else{
            profit = Math.max(prices[ind]+recursionHelper(ind+1,1,cap-1,prices),
                    recursionHelper(ind+1,0,cap,prices));
        }
        return profit;
    }
    public static int bestTimeToBuyAndSellStocksIVMemoization(int[] prices,int k){
        int[][][] dp = new int[prices.length][2][k+1];
        for(int[][] mat:dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return memoizationHelper(0,1,k,prices,dp);
    }
    private static int memoizationHelper(int ind, int buy, int cap,int[] prices,int[][][] dp){
        if(cap==0) return 0;
        if(ind==prices.length) return 0;
        int profit;
        if(dp[ind][buy][cap]!=-1) return dp[ind][buy][cap];
        if(buy==1){
            profit = Math.max(-prices[ind]+memoizationHelper(ind+1,0,cap,prices,dp),
                    memoizationHelper(ind+1,1,cap,prices,dp));
        }else{
            profit = Math.max(prices[ind]+memoizationHelper(ind+1,1,cap-1,prices,dp),
                    memoizationHelper(ind+1,0,cap,prices,dp));
        }
        return dp[ind][buy][cap] = profit;
    }
    public static int bestTimeToBuyAndSellStocksIVTabulation(int[] prices,int k){
        int[][][] dp = new int[prices.length+1][2][k+1];
        for(int ind = prices.length-1; ind>=0;ind--){
            for(int buy = 0;buy<=1;buy++){
                for(int cap = 1; cap<=k;cap++){
                    if(buy==1){
                        dp[ind][buy][cap] = Math.max(-prices[ind]+dp[ind+1][0][cap],
                                dp[ind+1][1][cap]);
                    }else{
                        dp[ind][buy][cap] = Math.max(prices[ind]+dp[ind+1][1][cap-1],
                                dp[ind+1][0][cap]);
                    }
                }
            }
        }
        return dp[0][1][k];
    }
    public static int bestTimeToBuyAndSellStocksIVSpaceOptimized(int[] prices,int k){
        int[][] after = new int[2][k+1];

        for(int ind = prices.length-1; ind>=0;ind--){
            int[][] curr = new int[2][k+1];
            for(int buy = 0;buy<=1;buy++){
                for(int cap = 1; cap<=k;cap++){
                    if(buy==1){
                        curr[buy][cap] = Math.max(-prices[ind]+after[0][cap],
                                after[1][cap]);
                    }else{
                        curr[buy][cap] = Math.max(prices[ind]+after[1][cap-1],
                                after[0][cap]);
                    }
                }
            }
            after=curr;
        }
        return after[1][k];
    }

    public static int buyAndSellStocksIVRecursionI(int[] prices,int k){
        return recursionIHelper(0,0,prices,k);
    }
    private static int recursionIHelper(int ind, int trans, int[] prices,int k){
        if(ind== prices.length || trans ==2*k) return 0;
        int profit;
        if(trans%2==0){
            profit = Math.max(-prices[ind]+recursionIHelper(ind+1,trans+1,prices,k),
                    recursionIHelper(ind+1,trans,prices,k));
        }else{
            profit = Math.max(prices[ind]+recursionIHelper(ind+1,trans+1,prices,k),
                    recursionIHelper(ind+1,trans,prices,k));
        }
        return profit;
    }
    public static int bestTimeToBuyAndSellStocksIVMemoizationI(int[] prices,int k){
        int[][] dp = new int[prices.length][2*k];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationIHelper(0,0,prices,dp,k);
    }
    private static int memoizationIHelper(int ind, int trans, int[] prices, int[][] dp,int k){
        if(ind== prices.length || trans==2*k) return 0;
        if(dp[ind][trans]!=-1) return dp[ind][trans];
        int profit;
        if(trans%2==0){
            profit = Math.max(-prices[ind]+memoizationIHelper(ind+1,trans+1,prices,dp,k),
                    memoizationIHelper(ind+1,trans,prices,dp,k));
        }else{
            profit = Math.max(prices[ind]+memoizationIHelper(ind+1,trans+1,prices,dp,k),
                    memoizationIHelper(ind+1,trans,prices,dp,k));
        }
        return dp[ind][trans] = profit;
    }
    public static int bestTimeToBuyAndSellStocksIVTabulationI(int[] prices,int k){
        int[][] dp = new int[prices.length+1][2*k+1];
        for(int ind = prices.length-1;ind>=0;ind--){
            for(int trans=2*k-1;trans>=0;trans--){
                if(trans%2==0){
                    dp[ind][trans] = Math.max(-prices[ind]+dp[ind+1][trans+1],
                            dp[ind+1][trans]);
                }else{
                    dp[ind][trans] = Math.max(prices[ind]+dp[ind+1][trans+1],
                            dp[ind+1][trans]);
                }
            }
        }
        return dp[0][0];
    }
    public static int bestTimeToBuyAndSellStocksIVSpaceOptimizedI(int[] prices,int k){
        int[] prev = new int[2*k+1];
        for(int ind = prices.length-1;ind>=0;ind--){
            int[] curr = new int[2*k+1];
            for(int trans=2*k-1;trans>=0;trans--){
                if(trans%2==0){
                    curr[trans] = Math.max(-prices[ind]+prev[trans+1],
                            prev[trans]);
                }else{
                    curr[trans] = Math.max(prices[ind]+prev[trans+1],
                            prev[trans]);
                }
            }
            prev = curr;
        }
        return prev[0];
    }
    public static void main(String[] args){
        int[] prices = {2, 6, 5, 2};
        int k = 0;
        int[] prices1 = {1,2,3,5};
        int k1 = 2;
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(N)-> ASS
        System.out.println("Using Recursion: ");
        System.out.println(buyAndSellStocksIVRecursion(prices,k));
        System.out.println(buyAndSellStocksIVRecursion(prices1,k1));
        System.out.println("Using another Recursion: ");
        System.out.println(buyAndSellStocksIVRecursionI(prices,k));
        System.out.println(buyAndSellStocksIVRecursionI(prices1,k1));



        // Using Memoization
        // Time Complexity: O(N*2*3)
        // Space Complexity: O(N*2*3) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(bestTimeToBuyAndSellStocksIVMemoization(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVMemoization(prices1,k1));
        System.out.println("Using another Memoization: ");
        System.out.println(bestTimeToBuyAndSellStocksIVMemoizationI(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVMemoizationI(prices1,k1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(bestTimeToBuyAndSellStocksIVTabulation(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVTabulation(prices1,k1));
        System.out.println("Using another Tabulation: ");
        System.out.println(bestTimeToBuyAndSellStocksIVTabulationI(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVTabulationI(prices1,k1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(bestTimeToBuyAndSellStocksIVSpaceOptimized(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVSpaceOptimized(prices1,k1));
        System.out.println("Using another Space Optimized: ");
        System.out.println(bestTimeToBuyAndSellStocksIVSpaceOptimizedI(prices,k));
        System.out.println(bestTimeToBuyAndSellStocksIVSpaceOptimizedI(prices1,k1));


    }
}