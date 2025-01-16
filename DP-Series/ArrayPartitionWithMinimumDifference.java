import java.util.Arrays;

public class ArrayPartitionWithMinimumDifference {
    public static int minSubsetSumDifferenceRecursion(int[] arr){
        int totSum = 0;
        for (int j : arr) {
            totSum += j;
        }
        return RecursionHelper(arr,arr.length-1,0,totSum);
    }
    private static int RecursionHelper(int[] arr, int ind, int sum1, int totSum){
        if(ind<0){
            return Math.abs(sum1-(totSum-sum1));
        }
        int notTake = RecursionHelper(arr,ind-1,sum1,totSum);
        int take = 0;
        if(arr[ind]<=totSum){
            take = RecursionHelper(arr,ind-1,sum1+arr[ind],totSum);
        }
        return Math.min(take,notTake);
    }
    public static int minSubsetSumDifferenceMemoization(int[] arr){
        int totSum = 0;
        for (int j : arr) {
            totSum += j;
        }
        int[][] dp = new int[arr.length][totSum+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for(int i = 0; i<arr.length;i++){
            dp[i][0] = 1;
        }
        if (arr[0] <= totSum) {
            dp[0][arr[0]] = 1;
        }
        for(int sum = 0; sum<=totSum/2;sum++){
            MemoizationHelper(arr,sum, arr.length-1,dp);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<=totSum;i++){
            if(dp[arr.length-1][i]==1) min = Math.min(min,Math.abs(i-(totSum-i)));
        }
        return min;
    }
    private static int MemoizationHelper(int[] arr,int target, int ind, int[][] dp){
        if(target==0) return 1;
        if(ind==0){
            return arr[ind] == target?1:0;
        }
        if(dp[ind][target]!=-1) return dp[ind][target];

        int notTake = MemoizationHelper(arr,target,ind-1,dp);
        int take = 0;
        if(arr[ind]<=target){
            take = MemoizationHelper(arr,target-arr[ind],ind-1,dp);
        }
        dp[ind][target]  = (take==1 || notTake==1)?1:0;
        return dp[ind][target];
    }
    public static int minSubsetSumDifferenceTabulation(int[] arr){
        int n = arr.length;
        int totSum = 0;
        for (int j : arr) {
            totSum += j;
        }
        boolean[][] dp = new boolean[n][totSum+1];
        for(int i = 0; i<n;i++){
            dp[i][0] = true;
        }
        if (arr[0] <= totSum) {
            dp[0][arr[0]] = true;
        }
        for(int ind = 1; ind<n;ind++) {
            for (int target = 1; target <= totSum; target++) {
                boolean notTake = dp[ind - 1][target];
                boolean take = false;
                if (arr[ind] <= target) {
                    take = dp[ind - 1][target - arr[ind]];
                }
                dp[ind][target] = take || notTake;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<=totSum;i++){
            if(dp[n-1][i]) min = Math.min(min,Math.abs(i-(totSum-i)));
        }
        return min;
    }
    public static int minSubsetSumDifferenceSpaceOptimized(int[] arr){
        int n = arr.length;
        int totSum = 0;
        for (int j : arr) {
            totSum += j;
        }
        boolean[] prev = new boolean[totSum+1];
        prev[0] = true;
        if (arr[0] <= totSum) {
            prev[arr[0]] = true;
        }
        for(int ind = 1; ind<n;ind++) {
            boolean[] curr = new boolean[totSum+1];
            curr[0] = true;
            for (int target = 1; target <= totSum; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                if (arr[ind] <= target) {
                    take = prev[target - arr[ind]];
                }
                curr[target] = take || notTake;
            }
            prev=curr;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<=totSum;i++){
            if(prev[i]) min = Math.min(min,Math.abs(i-(totSum-i)));
        }
        return min;
    }


    public static void main(String[] args){
        int[] arr = {3,2,7};
        int[] arr1 = {8,6,5};
        //Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Recursion: ");
        System.out.println(minSubsetSumDifferenceRecursion(arr));
        System.out.println(minSubsetSumDifferenceRecursion(arr1));

        // Using Memoization
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(minSubsetSumDifferenceMemoization(arr));
        System.out.println(minSubsetSumDifferenceMemoization(arr1));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(minSubsetSumDifferenceTabulation(arr));
        System.out.println(minSubsetSumDifferenceTabulation(arr1));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(TotalSum)
        System.out.println("Using Space Optimized: ");
        System.out.println(minSubsetSumDifferenceSpaceOptimized(arr));
        System.out.println(minSubsetSumDifferenceSpaceOptimized(arr1));
    }
}