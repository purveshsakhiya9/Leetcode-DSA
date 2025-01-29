import java.util.Arrays;

public class MinimumCostToCutTheStick {
    public static int minimumCostToCutTheStickRecursion(int[] arr, int n, int c){
        int[] newArr = new int[arr.length+2];
        newArr[0] = 0;
        newArr[newArr.length-1] = n;
        System.arraycopy(arr, 0, newArr, 1, newArr.length - 2);
        Arrays.sort(newArr);
        return recursionHelper(1,c,newArr);
    }
    private static int recursionHelper(int i, int j, int[] arr){
        if(i>j) return 0;
        int min = Integer.MAX_VALUE;
        for(int ind = i;ind<=j;ind++){
            int cost = arr[j+1]-arr[i-1]+ recursionHelper(i,ind-1,arr) + recursionHelper(ind+1,j,arr);
            min = Math.min(min,cost);
        }
        return min;
    }
    public static int minimumCostToCutTheStickMemoization(int[] arr, int n, int c){
        int[][] dp = new int[c+1][c+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        int[] newArr = new int[arr.length+2];
        newArr[0] = 0;
        newArr[newArr.length-1] = n;
        for(int i = 1;i<=newArr.length-2;i++){
            newArr[i] = arr[i-1];
        }
        Arrays.sort(newArr);
        return memoizationHelper(1,c,newArr,dp);
    }
    public static int memoizationHelper(int i, int j, int[] arr, int[][] dp){
        if(i>j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int ind = i;ind<=j;ind++){
            int cost = arr[j+1]-arr[i-1]+ memoizationHelper(i,ind-1,arr,dp) + memoizationHelper(ind+1,j,arr,dp);
            min = Math.min(min,cost);
        }
        return dp[i][j] = min;
    }
    public static int minimumCostToCutTheStickTabulation(int[] arr, int n, int c){
        int[] newArr = new int[arr.length+2];
        newArr[0] = 0;
        newArr[newArr.length-1] = n;
        System.arraycopy(arr, 0, newArr, 1, newArr.length - 2);
        Arrays.sort(newArr);
        int[][] dp = new int[c+2][c+2];
        for(int i = c;i>=1;i--){
            for(int j = 1;j<=c;j++){
                if(i>j) continue;
                int min = Integer.MAX_VALUE;
                for(int ind = i;ind<=j;ind++){
                    int cost = newArr[j+1]-newArr[i-1]+ dp[i][ind-1] + dp[ind+1][j];
                    min = Math.min(min,cost);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][c];
    }
    public static void main(String [] args){
        int[] arr = {1,3};
        int n = 4;
        int[] arr1 = {1,3,4};
        int n1 = 5;
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(C)
        System.out.println("Using Recursion: ");
        System.out.println(minimumCostToCutTheStickRecursion(arr,n, arr.length));
        System.out.println(minimumCostToCutTheStickRecursion(arr1,n1, arr1.length));

        // Using Memoization
        // Time Complexity: O(C*C*C)
        // Space Complexity: O(C*C) + O(C)
        System.out.println("Using Memoization: ");
        System.out.println(minimumCostToCutTheStickMemoization(arr,n, arr.length));
        System.out.println(minimumCostToCutTheStickMemoization(arr1,n1, arr1.length));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(minimumCostToCutTheStickTabulation(arr,n, arr.length));
        System.out.println(minimumCostToCutTheStickTabulation(arr1,n1, arr1.length));
    }
}