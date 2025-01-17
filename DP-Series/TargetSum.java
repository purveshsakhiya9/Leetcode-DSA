import java.util.Arrays;

public class TargetSum{
    public static int targetSumRecursion(int n, int target, int[] arr){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-target<0 || (totSum-target)%2==1) return 0;
        int tar = (totSum-target)/2;
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

    public static int targetSumMemoization(int n, int target, int[] arr){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-target<0 || (totSum-target)%2==1) return 0;
        int tar = (totSum-target)/2;
        int[][] dp = new int[arr.length][tar+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(arr, tar, arr.length - 1, dp);
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
        return dp[ind][target]=(pick+notPick);
    }
    public static int targetSumTabulation(int n, int target, int[] arr){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-target<0 || (totSum-target)%2==1) return 0;
        int tar = (totSum-target)/2;
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
                dp[ind][sum] = (take+nonTake);
                if(arr[ind]==0){
                    dp[ind][sum] = (2*dp[ind-1][sum]);
                }
            }
        }
        return dp[n-1][tar];
    }
    public static int targetSumSpaceOptimized(int n, int target, int[] arr){
        int totSum = 0;
        for(int num:arr) totSum+=num;
        if(totSum-target<0 || (totSum-target)%2==1) return 0;
        int tar = (totSum-target)/2;
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
                curr[sum] = (take+nonTake);
            }
            prev = curr;
        }
        return prev[tar];
    }

    public static void main(String[] args){
        int[] arr = {1,1,1,1,1};
        int res = 3;
        int[] arr1 = {1,2,3,1};
        int res1 = 3;
        //Using Recursion
        // Time Complexity: way greater than O(2^N) == exponential
        // Space Complexity: way greater than O(N*target) +O(N)
        System.out.println("Using Recursion: ");
        System.out.println(targetSumRecursion(arr.length, res, arr));
        System.out.println(targetSumRecursion(arr1.length, res1,arr1));

        // Using Memoization
        // Time Complexity: O(N*target)
        // Space Complexity: O(N*target) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(targetSumMemoization(arr.length, res, arr));
        System.out.println(targetSumMemoization(arr1.length, res1, arr1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(targetSumTabulation(arr.length, res, arr));
        System.out.println(targetSumTabulation(arr1.length, res1, arr1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(targetSumSpaceOptimized(arr.length, res, arr));
        System.out.println(targetSumSpaceOptimized(arr1.length,  res1, arr1));
    }
}