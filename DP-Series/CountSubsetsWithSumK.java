import java.util.Arrays;

public class CountSubsetsWithSumK {
    public static int CountSubsetsWithSumKRecursion(int[] num, int tar){
        return recursionHelper(num,tar,num.length-1);
    }
    private static int recursionHelper(int[] num, int target,int ind){
        if(target==0) return 1;
        if(ind==0){
            if(num[ind]==target) return 1;
            else return 0;
        }

        int notPick = recursionHelper(num,target,ind-1);
        int pick = 0;
        if(target>=num[ind]){
            pick = recursionHelper(num,target-num[ind],ind-1);
        }
        return pick+notPick;
    }

    public static int MOD = 1000000000+7;
    public static int CountSubsetsWithSumKMemoization(int[] num, int tar){
        int[][] dp = new int[num.length][tar+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(num, tar, num.length - 1, dp) % MOD;
    }
    private static int memoizationHelper(int[] num, int target,int ind,int[][] dp){
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

    public static int CountSubsetsWithSumKTabulation(int[] num, int tar){
        int totSum = 0;
        for(int n:num) totSum+=n;
        int n = num.length;
        int[][] dp = new int[n][totSum+1];
        if(num[0]==0) dp[0][0] = 2;
        else dp[0][0] =1;
        if(num[0]!=0 && num[0]<=totSum){
            dp[0][num[0]] = 1;
        }
        for(int ind = 1;ind<n;ind++){
            for(int sum = 0; sum<=totSum;sum++){
                int nonTake = dp[ind-1][sum];
                int take = 0;
                if(num[ind]<=sum){
                    take = dp[ind-1][sum-num[ind]];
                }
                dp[ind][sum] = (take+nonTake)%MOD;
            }
        }
        return dp[n-1][tar];
    }
    public static int CountSubsetsWithSumKSpaceOptimized(int[] num, int tar){
        int totSum = 0;
        for(int n:num) totSum+=n;
        int n = num.length;
        int[] prev = new int[totSum+1];
        if(num[0]==0) prev[0] = 2;
        else prev[0] =1;
        if(num[0]!=0 && num[0]<=totSum){
            prev[num[0]] = 1;
        }

        for(int ind = 1;ind<n;ind++){
            int[] curr = new int[totSum+1];
            for(int sum = 0; sum<=totSum;sum++){
                int nonTake = prev[sum];
                int take = 0;
                if(num[ind]<=sum){
                    take = prev[sum-num[ind]];
                }
                curr[sum] = (take+nonTake)%MOD;
            }
            prev=curr;
        }
        return prev[tar];
    }


    public static void main(String[] args){
        int[] arr = {1,4,4,5};
        int target = 5;
        int[] arr1 = {2, 2, 1, 7, 3, 3, 1, 7, 9, 6, 7, 4, 2, 7, 5};
        int target1 = 7;
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(CountSubsetsWithSumKRecursion(arr,target));
        System.out.println(CountSubsetsWithSumKRecursion(arr1,target1));

        // Using Memoization
        // Time Complexity: O(N*totSum)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(CountSubsetsWithSumKMemoization(arr,target));
        System.out.println(CountSubsetsWithSumKMemoization(arr1,target1));

        //Using Tabulation
        // Time Complexity:O(N*totSum)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(CountSubsetsWithSumKTabulation(arr,target));
        System.out.println(CountSubsetsWithSumKTabulation(arr1,target1));

        //Using Space Optimized
        // Time Complexity: O(N*totSum)
        // Space Complexity: O(totSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(CountSubsetsWithSumKSpaceOptimized(arr,target));
        System.out.println(CountSubsetsWithSumKSpaceOptimized(arr1,target1));
    }
}