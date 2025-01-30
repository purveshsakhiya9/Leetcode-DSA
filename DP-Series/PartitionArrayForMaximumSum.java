import java.util.Arrays;

public class PartitionArrayForMaximumSum {
    public static int partitionArrayForMaximumSumRecursion(int[] num, int k){
        return recursionHelper(0,k,num);
    }
    private static int recursionHelper(int i,int k, int[] num){
        if(i==num.length) return 0;
        int len =0;
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for(int j = i; j<Math.min(num.length, i+k);j++){
            len++;
            max = Math.max(num[j],max);
            int sum = (len*max) + recursionHelper(j+1,k,num);
            ans = Math.max(ans,sum);
        }
        return ans;
    }
    public static int partitionArrayForMaximumSumMemoization(int[] num, int k){
        int[] dp = new int[num.length+1];
        Arrays.fill(dp,-1);
        return memoizationHelper(0,k,num,dp);
    }
    private static int memoizationHelper(int i,int k, int[] num, int[] dp){
        if(i==num.length) return 0;
        if(dp[i]!=-1) return dp[i];
        int len =0;
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for(int j = i; j<Math.min(num.length, i+k);j++){
            len++;
            max = Math.max(num[j],max);
            int sum = (len*max) + memoizationHelper(j+1,k,num,dp);
            ans = Math.max(ans,sum);
        }
        return dp[i] = ans;
    }
    public static int partitionArrayForMaximumSumTabulation(int[] num, int k){
        int[] dp = new int[num.length+1];
        for(int ind = num.length-1;ind>=0;ind--){
            int len =0;
            int max = Integer.MIN_VALUE;
            int ans = Integer.MIN_VALUE;
            for(int j = ind; j<Math.min(num.length, ind+k);j++){
                len++;
                max = Math.max(num[j],max);
                int sum = (len*max) + dp[j+1];
                ans = Math.max(ans,sum);
            }
            dp[ind] = ans;
        }
        return dp[0];
    }
    public static void main(String[] args){
        int[] num = {1, 20, 13, 4, 4, 1};
        int k = 3;
        int[] num1 = {1, 21, 1, 5, 4};
        int k1 = 2;
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(N)
        System.out.println("Using Recursion: ");
        System.out.println(partitionArrayForMaximumSumRecursion(num,k));
        System.out.println(partitionArrayForMaximumSumRecursion(num1,k1));

        // Using Memoization
        // Time Complexity: O(N) * O(K)
        // Space Complexity: O(N) + O(N)
        System.out.println("Using Memoization: ");
        System.out.println(partitionArrayForMaximumSumMemoization(num,k));
        System.out.println(partitionArrayForMaximumSumMemoization(num1,k1));

        // Using Tabulation
        // Time Complexity: O(N*K)
        // Space Complexity: O(N)
        System.out.println("Using Tabulation: ");
        System.out.println(partitionArrayForMaximumSumTabulation(num,k));
        System.out.println(partitionArrayForMaximumSumTabulation(num1,k1));
    }
}