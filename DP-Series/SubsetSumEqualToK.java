import java.util.Arrays;

public class SubsetSumEqualToK {

    public static boolean SubsetSumEqualToKRecursion(int[] arr, int k, int n){
        return RecursionHelper(arr,n-1,k);
    }
    private static boolean RecursionHelper(int[] arr, int ind, int target){
        if(target==0) return true;
        if(ind==0){
            return arr[ind] == target;
        }

        boolean notTake = RecursionHelper(arr,ind-1,target);
        boolean take = false;
        if(arr[ind]<=target){
            take = RecursionHelper(arr,ind-1,target-arr[ind]);
        }

        return take || notTake;
    }
    public static boolean SubsetSumEqualToKMemoization(int[] arr, int target,int n){
        int[][] dp = new int[n][target+1];
        for(int[] num: dp){
            Arrays.fill(num,-1);
        }
        return MemoizationHelper(arr,target,n-1,dp);
    }
    private static Boolean MemoizationHelper(int[] arr, int target, int ind, int[][] dp){
        if(target==0) return true;
        if(ind==0){
            return arr[ind] == target;
        }
        if(dp[ind][target]!=-1) return dp[ind][target]==1;

        boolean notTake = MemoizationHelper(arr,target,ind-1,dp);
        boolean take = false;
        if(arr[ind]<=target){
            take = MemoizationHelper(arr,target-arr[ind],ind-1,dp);
        }
        boolean ans = take || notTake;
        if(ans) dp[ind][target] = 1;
        else dp[ind][target]=0;
        return ans;
    }
    public static boolean SubsetSumEqualToKTabulation(int[] arr, int k, int n){
        boolean[][] dp = new boolean[n][k+1];
        for(int i = 0; i<n;i++){
            dp[i][0] = true;
        }
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for(int ind = 1; ind<n;ind++){
            for(int target = 1; target<=k;target++){
                boolean notTake = dp[ind-1][target];
                boolean take = false;
                if(arr[ind]<=target){
                    take = dp[ind-1][target-arr[ind]];
                }
                dp[ind][target] = take||notTake;
            }
        }
        return dp[n-1][k];
    }
    public static boolean SubsetSumEqualToKSpaceOptimized(int[] arr, int k,int n){
        boolean[] prev = new boolean[k+1];
        prev[0] = true;
        if(arr[0]<=k){
            prev[arr[0]]=true;
        }

        for(int ind=1;ind<n;ind++){
            boolean[] curr = new boolean[k+1];
            curr[0] = true;
            for(int target = 1; target<=k;target++){
                boolean notTake = prev[target];
                boolean take = false;
                if(arr[ind]<=target){
                    take = prev[target-arr[ind]];
                }
                curr[target] = take||notTake;
            }
            prev=curr;
        }
        return prev[k];
    }


    public static void main(String[] args){
        int[] arr = {4,3,2,1};
        int target = 5;

        int[] arr1 = {2,5,1,6,7};
        int target1 = 4;

        //using recursion
        // Time Complexity: O(2^n)
        // Space Complexity: O(n)
        System.out.println("Using Recursion: ");
        System.out.println(SubsetSumEqualToKRecursion(arr,target,arr.length));
        System.out.println(SubsetSumEqualToKRecursion(arr1,target1,arr1.length));

        //using memoization
        // Time Complexity: O(N*target)
        // Space Complexity: O(N*target)
        System.out.println("Using Memoization: ");
        System.out.println(SubsetSumEqualToKMemoization(arr,target,arr.length));
        System.out.println(SubsetSumEqualToKMemoization(arr1,target1, arr1.length));

        //using Tabulation
        // Time Complexity: O(N*target)
        // Space Complexity: O(N*target)
        System.out.println("Using Tabulation: ");
        System.out.println(SubsetSumEqualToKTabulation(arr,target,arr.length));
        System.out.println(SubsetSumEqualToKTabulation(arr1,target1, arr1.length));

        // using Space Optimization
        // Time Complexity: O(N*target)
        // Space Complexity: O(Target)
        System.out.println("Using Space Optimized:");
        System.out.println(SubsetSumEqualToKSpaceOptimized(arr,target,arr.length));
        System.out.println(SubsetSumEqualToKSpaceOptimized(arr1,target1,arr1.length));
    }
}