import java.util.Arrays;

public class MinimumCoin {
    public static int minimumCoinRecursion(int num[], int x){
        int ans = recursionHelper(num,x,num.length-1);
        if(ans>=1000000000) return -1;
        return ans;
    }
    private static int recursionHelper(int[] num, int x, int ind){
        if(ind==0){
            if(x%num[ind]==0) return x/num[ind];
            return 1000000000;
        }
        int notTake = recursionHelper(num,x,ind-1);
        int take = Integer.MAX_VALUE;
        if(num[ind]<=x){
            take = 1 + recursionHelper(num,x-num[ind],ind);
        }
        return Math.min(take,notTake);
    }
    public static int minimumCoinMemoization(int num[], int x){
        int[][] dp = new int[num.length][x+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        int ans = memoizationHelper(num,x,num.length-1,dp);
        if(ans>=1000000000) return -1;
        return ans;
    }
    private static int memoizationHelper(int[] num, int x, int ind, int[][] dp){
        if(ind==0){
            if(x%num[ind]==0) return x/num[ind];
            return 1000000000;
        }
        if(dp[ind][x]!=-1) return dp[ind][x];
        int notTake = memoizationHelper(num,x,ind-1,dp);
        int take = Integer.MAX_VALUE;
        if(num[ind]<=x){
            take = 1 + memoizationHelper(num,x-num[ind],ind,dp);
        }
        return dp[ind][x] = Math.min(take,notTake);
    }
    public static int minimumCoinTabulation(int num[], int x){
        int[][] dp = new int[num.length][x+1];
        for(int t = 0; t<=x;t++){
            if(t%num[0]==0){
                dp[0][t] = t/num[0];
            }else{
                dp[0][t] = 1000000000;
            }
        }
        for(int ind=1;ind<num.length;ind++){
            for(int t = 0;t<=x;t++){
                int notTake = dp[ind-1][t];
                int take = Integer.MAX_VALUE;
                if(num[ind]<=t){
                    take = 1 + dp[ind][t-num[ind]];
                }
                dp[ind][t] = Math.min(take,notTake);
            }
        }
        return dp[num.length-1][x]>=1000000000?-1:dp[num.length-1][x];
    }
    public static int minimumCoinSpaceOptimized(int num[], int x){
        int[] prev = new int[x+1];
        for(int t = 0; t<=x;t++){
            if(t%num[0]==0){
                prev[t] = t/num[0];
            }else{
                prev[t] = 1000000000;
            }
        }
        for(int ind=1;ind<num.length;ind++){
            int[] curr = new int[x+1];
            for(int t = 0;t<=x;t++){
                int notTake = prev[t];
                int take = 1000000000;
                if(num[ind]<=t){
                    take = 1 + curr[t-num[ind]];
                }
                curr[t] = Math.min(take,notTake);
            }
            prev=curr;
        }
        return prev[x]>=1000000000?-1:prev[x];
    }
    public static void main(String[] args){
        int[] arr = {1,2,3};
        int target = 7;
        int[] arr1 = {1};
        int target1 = 0;
        //Using Recursion
        // Time Complexity: way greater than O(2^N) == exponential
        // Space Complexity: way greater than O(N*target) +O(N)
        System.out.println("Using Recursion: ");
        System.out.println(minimumCoinRecursion(arr,target));
        System.out.println(minimumCoinRecursion(arr1,target1));

        // Using Memoization
        // Time Complexity: O(N*target)
        // Space Complexity: O(N*target) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(minimumCoinMemoization(arr,target));
        System.out.println(minimumCoinMemoization(arr1,target1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(minimumCoinTabulation(arr,target));
        System.out.println(minimumCoinTabulation(arr1,target1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(minimumCoinSpaceOptimized(arr,target));
        System.out.println(minimumCoinSpaceOptimized(arr1,target1));
    }
}