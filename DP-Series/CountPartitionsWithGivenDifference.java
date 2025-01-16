import java.util.Arrays;

public class CountPartitionsWithGivenDifference {
    public static int CountPartitionsWithGivenDifferenceRecursion(int[] arr, int n, int d){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-d<0 || (totSum-d)%2==1) return 0;
        int tar = (totSum-d)/2;
        return recursionHelper(arr,tar, arr.length-1);
    }
    private static int recursionHelper(int[] num, int target,int ind){
        if(ind==0){
            if(target==0 && num[0]==0){
                return 2;
            }
            if(target==0 || target==num[0]){
                return 1;
            }
            return 0;
        }

        int notPick = recursionHelper(num,target,ind-1);
        int pick = 0;
        if(target>=num[ind]){
            pick = recursionHelper(num,target-num[ind],ind-1);
        }
        return pick+notPick;
    }

    public static int MOD = 1000000000+7;
    public static int CountPartitionsWithGivenDifferenceMemoization(int[] arr, int n, int d){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-d<0 || (totSum-d)%2==1) return 0;
        int tar = (totSum-d)/2;
        int[][] dp = new int[arr.length][tar+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(arr, tar, arr.length - 1, dp) % MOD;
    }
    public static int memoizationHelper(int[] num, int target, int ind, int[][] dp){
        if(ind==0){
            if(target==0 && num[0]==0){
                return 2;
            }
            if(target==0 || target==num[0]){
                return 1;
            }
            return 0;
        }
        if(dp[ind][target]!=-1) return dp[ind][target];
        int notPick = memoizationHelper(num,target,ind-1,dp);
        int pick = 0;
        if(target>=num[ind]){
            pick = memoizationHelper(num,target-num[ind],ind-1,dp);
        }
        return dp[ind][target]=(pick+notPick)%MOD;
    }
    public static int CountPartitionsWithGivenDifferenceTabulation(int[] arr, int n, int d){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-d<0 || (totSum-d)%2==1) return 0;
        int tar = (totSum-d)/2;
        int[][] dp = new int[n][totSum+1];
        if(arr[0]==0) dp[0][0] = 2;
        else dp[0][0] =1;
        if(arr[0]!=0 && arr[0]<=totSum){
            dp[0][arr[0]] = 1;
        }

        for(int ind = 1;ind<n;ind++){
            for(int sum = 0; sum<=totSum;sum++){
                int nonTake = dp[ind-1][sum];
                int take = 0;
                if(arr[ind]<=sum){
                    take = dp[ind-1][sum-arr[ind]];
                }
                dp[ind][sum] = (take+nonTake)%MOD;
                if(arr[ind]==0){
                    dp[ind][sum] = (2*dp[ind-1][sum])%MOD;
                }
            }
        }
        return dp[n-1][tar];
    }
    public static int CountPartitionsWithGivenDifferenceSpaceOptimized(int[] arr, int n, int d){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-d<0 || (totSum-d)%2==1) return 0;
        int tar = (totSum-d)/2;
        int[] prev = new int[totSum+1];
        if(arr[0]==0) prev[0] = 2;
        else prev[0] =1;
        if(arr[0]!=0 && arr[0]<=totSum){
            prev[arr[0]] = 1;
        }

        for(int ind = 1;ind<n;ind++){
            int[] curr = new int[totSum+1];
            for(int sum = 0; sum<=totSum;sum++){
                int nonTake = prev[sum];
                int take = 0;
                if(arr[ind]<=sum){
                    take = prev[sum-arr[ind]];
                }
                curr[sum] = (take+nonTake)%MOD;
            }
            prev = curr;
        }
        return prev[tar];
    }
    public static void main(String[] args){
        int[] arr = {1, 0, 8, 5, 1, 4};
        int d = 17;
        int[] arr1 = {1,1,1,1};
        int d1 = 0;
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(CountPartitionsWithGivenDifferenceRecursion(arr,arr.length,d));
        System.out.println(CountPartitionsWithGivenDifferenceRecursion(arr1,arr1.length,d1));

        // Using Memoization
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(CountPartitionsWithGivenDifferenceMemoization(arr,arr.length,d));
        System.out.println(CountPartitionsWithGivenDifferenceMemoization(arr1,arr1.length,d1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(CountPartitionsWithGivenDifferenceTabulation(arr,arr.length,d));
        System.out.println(CountPartitionsWithGivenDifferenceTabulation(arr1,arr1.length,d1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(CountPartitionsWithGivenDifferenceSpaceOptimized(arr,arr.length,d));
        System.out.println(CountPartitionsWithGivenDifferenceSpaceOptimized(arr1,arr1.length,d1));
    }
}