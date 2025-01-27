import java.util.Arrays;

public class BuyAndSellStocksIII {
    public static int buyAndSellStocksIIIRecursion(int[] prices){
        return recursionHelper(0,1,2,prices);
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
    public static int bestTimeToBuyAndSellStocksIIIMemoization(int[] prices){
        int[][][] dp = new int[prices.length][2][3];
        for(int[][] mat:dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return memoizationHelper(0,1,2,prices,dp);
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
    public static int bestTimeToBuyAndSellStocksIIITabulation(int[] prices){
        int[][][] dp = new int[prices.length+1][2][3];
        for(int ind = prices.length-1; ind>=0;ind--){
            for(int buy = 0;buy<=1;buy++){
                for(int cap = 1; cap<=2;cap++){
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
        return dp[0][1][2];
    }
    public static int bestTimeToBuyAndSellStocksIIISpaceOptimized(int[] prices){
        int[][] after = new int[2][3];

        for(int ind = prices.length-1; ind>=0;ind--){
            int[][] curr = new int[2][3];
            for(int buy = 0;buy<=1;buy++){
                for(int cap = 1; cap<=2;cap++){
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
        return after[1][2];
    }

    public static int buyAndSellStocksIIIRecursionI(int[] prices){
        return recursionIHelper(0,0,prices);
    }
    private static int recursionIHelper(int ind, int trans, int[] prices){
        if(ind== prices.length || trans ==4) return 0;
        int profit;
        if(trans%2==0){
            profit = Math.max(-prices[ind]+recursionIHelper(ind+1,trans+1,prices),
                    recursionIHelper(ind+1,trans,prices));
        }else{
            profit = Math.max(prices[ind]+recursionIHelper(ind+1,trans+1,prices),
                    recursionIHelper(ind+1,trans,prices));
        }
        return profit;
    }
    public static int bestTimeToBuyAndSellStocksIIIMemoizationI(int[] prices){
        int[][] dp = new int[prices.length][4];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationIHelper(0,0,prices,dp);
    }
    private static int memoizationIHelper(int ind, int trans, int[] prices, int[][] dp){
        if(ind== prices.length || trans==4) return 0;
        if(dp[ind][trans]!=-1) return dp[ind][trans];
        int profit;
        if(trans%2==0){
            profit = Math.max(-prices[ind]+memoizationIHelper(ind+1,trans+1,prices,dp),
                    memoizationIHelper(ind+1,trans,prices,dp));
        }else{
            profit = Math.max(prices[ind]+memoizationIHelper(ind+1,trans+1,prices,dp),
                    memoizationIHelper(ind+1,trans,prices,dp));
        }
        return dp[ind][trans] = profit;
    }
    public static int bestTimeToBuyAndSellStocksIIITabulationI(int[] prices){
        int[][] dp = new int[prices.length+1][5];
        for(int ind = prices.length-1;ind>=0;ind--){
            for(int trans=3;trans>=0;trans--){
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
    public static int bestTimeToBuyAndSellStocksIIISpaceOptimizedI(int[] prices){
        int[] prev = new int[5];
        for(int ind = prices.length-1;ind>=0;ind--){
            int[] curr = new int[5];
            for(int trans=3;trans>=0;trans--){
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
        int[] prices = {1 ,7 ,20, 23, 21, 11, 3, 15};
        int[] prices1 = {1,3,1,2,4,8};
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(N)-> ASS
        System.out.println("Using Recursion: ");
        System.out.println(buyAndSellStocksIIIRecursion(prices));
        System.out.println(buyAndSellStocksIIIRecursion(prices1));
        System.out.println("Using another Recursion: ");
        System.out.println(buyAndSellStocksIIIRecursionI(prices));
        System.out.println(buyAndSellStocksIIIRecursionI(prices1));



        // Using Memoization
        // Time Complexity: O(N*2*3)
        // Space Complexity: O(N*2*3) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(bestTimeToBuyAndSellStocksIIIMemoization(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIIMemoization(prices1));
        System.out.println("Using another Memoization: ");
        System.out.println(bestTimeToBuyAndSellStocksIIIMemoizationI(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIIMemoizationI(prices1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(bestTimeToBuyAndSellStocksIIITabulation(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIITabulation(prices1));
        System.out.println("Using another Tabulation: ");
        System.out.println(bestTimeToBuyAndSellStocksIIITabulationI(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIITabulationI(prices1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println(bestTimeToBuyAndSellStocksIIISpaceOptimized(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIISpaceOptimized(prices1));
        System.out.println("Using another Space Optimized: ");
        System.out.println(bestTimeToBuyAndSellStocksIIISpaceOptimizedI(prices));
        System.out.println(bestTimeToBuyAndSellStocksIIISpaceOptimizedI(prices1));


    }
}