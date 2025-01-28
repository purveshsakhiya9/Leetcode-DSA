import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubsequence {
    public static int longestIncreasingSubsequenceRecursion(int[] arr){
        return recursionHelper(0,-1,arr);
    }
    private static int recursionHelper(int ind, int prevInd, int[] arr){
        if(ind==arr.length) return 0;
        int len = recursionHelper(ind+1,prevInd,arr);
        if(prevInd==-1 || arr[ind]>arr[prevInd]){
            len = Math.max(len,1+recursionHelper(ind+1,ind,arr));
        }
        return len;
    }
    public static int longestIncreasingSubsequenceMemoization(int[] arr){
        int[][] dp = new int[arr.length][arr.length+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(0,-1,arr,dp);
    }
    private static int memoizationHelper(int ind, int prevInd, int[] arr,int[][] dp){
        if(ind==arr.length) return 0;
        if(dp[ind][prevInd+1]!= -1 ) return dp[ind][prevInd+1];
        int len = memoizationHelper(ind+1,prevInd,arr,dp);
        if(prevInd==-1 || arr[ind]>arr[prevInd]){
            len = Math.max(len,1+memoizationHelper(ind+1,ind,arr,dp));
        }
        return dp[ind][prevInd+1] = len;
    }
    public static int longestIncreasingSubsequenceTabulation(int[] arr){
        int[][] dp = new int[arr.length+1][arr.length+1];
        for(int ind = arr.length-1;ind>=-1;ind--){
            for(int prevInd=ind-1;prevInd>=-1;prevInd--){
                int len = dp[ind+1][prevInd+1];
                if(prevInd==-1 || arr[ind]>arr[prevInd]){
                    len = Math.max(len,1+dp[ind+1][ind+1]);
                }
                dp[ind][prevInd+1] = len;
            }
        }
        return dp[0][0];
    }
    public static int longestIncreasingSubsequenceSpaceOptimized(int[] arr){
        int[] after = new int[arr.length+1];
        int[] curr = new int[arr.length+1];
        for(int ind = arr.length-1;ind>=-1;ind--){
            for(int prevInd=ind-1;prevInd>=-1;prevInd--){
                int len = after[prevInd+1];
                if(prevInd==-1 || arr[ind]>arr[prevInd]){
                    len = Math.max(len,1+after[ind+1]);
                }
                curr[prevInd+1] = len;
            }
            after = curr;
        }
        return after[0];
    }

    public static int longestIncreasingSubsequenceAlgorithm(int n, int[] values){
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int[] hash=new int[n];
        for(int i = 0; i<n;i++){
            hash[i] = i;
            for(int prev = 0;prev<i;prev++){
                if(values[prev]<values[i] && 1+dp[prev]>dp[i]){
                    dp[i] = 1+dp[prev];
                    hash[i] = prev;
                }
            }
        }

        int ans = dp[0];
        int lastIndex = 0;
        for(int i = 1; i<n;i++){
            if(dp[i]>ans) {
                ans = dp[i];
                lastIndex = i;
            }
        }
        int[] print = new int[ans];
        int a = ans-1;
        print[a--] = values[lastIndex];
        while(hash[lastIndex]!=lastIndex){
            lastIndex = hash[lastIndex];
            print[a] = values[lastIndex];
            a--;
        }
        System.out.print("Subsequence is: ");
        for(int num:print){
            System.out.print(num+" ");
        }
        System.out.println();
        return ans;
    }

    public static int longestIncreasingSubsequenceBinarySearch(int n, int[] values){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(values[0]);
        int len = 1;
        for(int i = 1;i<n;i++){
            if(values[i]>temp.getLast()){
                temp.add(values[i]);
                len++;
            }else{
                int ind = Collections.binarySearch(temp,values[i]);
                if(ind<0) ind = -ind - 1;
                temp.set(ind,values[i]);
            }
        }
        return len;
    }

    public static void main(String[] args){
        int[] arr = {5,4,11,1,16,8};
        int[] arr1 = {4,3,2,1};
        // Using Recursion
        // Time Complexity: O(2^N)
        // Space Complexity: O(N)->ASS
        System.out.println("Using Recursion: ");
        System.out.println(longestIncreasingSubsequenceRecursion(arr));
        System.out.println(longestIncreasingSubsequenceRecursion(arr1));

        // Using Memoization
        // Time Complexity: O(N*(N+1))
        // Space Complexity:O(N*N) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(longestIncreasingSubsequenceMemoization(arr));
        System.out.println(longestIncreasingSubsequenceMemoization(arr1));

        // Using Tabulation
        // Time Complexity: O(N^2)
        // Space Complexity: O(N^2)
        System.out.println("Using Tabulation: ");
        System.out.println(longestIncreasingSubsequenceTabulation(arr));
        System.out.println(longestIncreasingSubsequenceTabulation(arr1));

        // Using Space Optimized
        // Time Complexity: O(N^2)
        // Space Complexity: O(N)
        System.out.println("Using Space Optimized: ");
        System.out.println(longestIncreasingSubsequenceSpaceOptimized(arr));
        System.out.println(longestIncreasingSubsequenceSpaceOptimized(arr1));

        // Using algorithmic code:
        // Time Complexity: O(N^2)
        // Space Complexity: O(N)
        System.out.println("Printing Using Algorithmic code: ");
        System.out.println(longestIncreasingSubsequenceAlgorithm(arr.length,arr));
        System.out.println(longestIncreasingSubsequenceAlgorithm(arr1.length,arr1));

        // Using Binary Search:
        // Time Complexity: O(N*logN)
        // Space Complexity: O(N)
        System.out.println("Using Binary search: ");
        System.out.println(longestIncreasingSubsequenceBinarySearch(arr.length,arr));
        System.out.println(longestIncreasingSubsequenceBinarySearch(arr1.length,arr1));

    }
}