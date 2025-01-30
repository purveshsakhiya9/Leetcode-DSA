import java.util.Arrays;

public class BurstBaloons {
    public static int burstBaloonsRecursion(int[] arr){
        int[] newArr = new int[arr.length+2];
        newArr[0] = 1;
        newArr[newArr.length-1] = 1;
        System.arraycopy(arr, 0, newArr, 1, newArr.length - 2);
        return recursionHelper(1,arr.length,newArr);
    }
    private static int recursionHelper(int i, int j, int[] arr){
        if(i>j) return 0;
        int max = Integer.MIN_VALUE;
        for(int ind = i;ind<=j;ind++){
            int cost = arr[i-1]*arr[ind]*arr[j+1] + recursionHelper(i,ind-1,arr) + recursionHelper(ind+1,j,arr);
            max = Math.max(max,cost);
        }
        return max;
    }
    public static int burstBaloonsMemoization(int[] arr){
        int[][] dp = new int[arr.length+1][arr.length+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        int[] newArr = new int[arr.length+2];
        newArr[0] = 1;
        newArr[newArr.length-1] = 1;
        System.arraycopy(arr, 0, newArr, 1, newArr.length - 2);
        return memoizationHelper(1,arr.length,newArr,dp);
    }
    private static int memoizationHelper(int i, int j, int[] arr,int[][] dp){
        if(i>j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int max = Integer.MIN_VALUE;
        for(int ind = i;ind<=j;ind++){
            int cost = arr[i-1]*arr[ind]*arr[j+1] + memoizationHelper(i,ind-1,arr,dp) + memoizationHelper(ind+1,j,arr,dp);
            max = Math.max(max,cost);
        }
        return dp[i][j]=max;
    }
    public static int burstBaloonsTabulation(int[] arr){
        int[] newArr = new int[arr.length+2];
        newArr[0] = 1;
        newArr[newArr.length-1] = 1;
        System.arraycopy(arr, 0, newArr, 1, newArr.length - 2);
        int n = arr.length;
        int[][] dp = new int[n+2][n+2];
        for(int i = n;i>=1;i--){
            for(int j = 1;j<=n;j++){
                if(i>j) continue;
                int max = Integer.MIN_VALUE;
                for(int ind = i;ind<=j;ind++){
                    int cost = newArr[i-1]*newArr[ind]*newArr[j+1] + dp[i][ind-1] + dp[ind+1][j];
                    max = Math.max(max,cost);
                }
                dp[i][j]=max;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args){
        int[] arr = {7,1,8};
        int[] arr1 = {9,1};
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(N)
        System.out.println("Using Recursion: ");
        System.out.println(burstBaloonsRecursion(arr));
        System.out.println(burstBaloonsRecursion(arr1));

        // Using Memoization
        // Time Complexity: O(N*N*N)
        // Space Complexity: O(N*N) +O(N)
        System.out.println("Using Memoization: ");
        System.out.println(burstBaloonsMemoization(arr));
        System.out.println(burstBaloonsMemoization(arr1));

        // Using Tabulation
        // Time Complexity: O(N*N*N)
        // Space Complexity: O(N*N)
        System.out.println("Using Tabulation: ");
        System.out.println(burstBaloonsTabulation(arr));
        System.out.println(burstBaloonsTabulation(arr1));


    }
}