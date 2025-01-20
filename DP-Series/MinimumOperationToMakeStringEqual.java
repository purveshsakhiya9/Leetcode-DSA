import java.util.Arrays;

public class MinimumOperationToMakeStringEqual {
    public static int minimumOperationToMakeStringEqualRecursion(String s1, String s2){
        return s1.length()+s2.length()-2*recursionHelper(s1,s2,s1.length()-1,s2.length()-1);
    }
    private static int recursionHelper(String s1, String s2, int m, int n){
        if(m<0 || n<0) return 0;
        if(s1.charAt(m)==s2.charAt(n)){
            return 1+recursionHelper(s1,s2,m-1,n-1);
        }
        return Math.max(recursionHelper(s1,s2,m,n-1),recursionHelper(s1,s2,m-1,n));
    }
    public static int minimumOperationToMakeStringEqualMemoization(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return s1.length()+s2.length()-2*memoizationHelper(s1,s2,m-1,n-1,dp);
    }
    private static int memoizationHelper(String s1, String s2, int m, int n, int[][] dp){
        if(m<0 || n<0){
            return 0;
        }
        if(dp[m][n]!=-1) return dp[m][n];
        if(s1.charAt(m)==s2.charAt(n)){
            return dp[m][n]=1+memoizationHelper(s1,s2,m-1,n-1,dp);
        }
        return dp[m][n]=Math.max(memoizationHelper(s1,s2,m,n-1,dp),memoizationHelper(s1,s2,m-1,n,dp));
    }
    public static int minimumOperationToMakeStringEqualTabulation(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i<=m;i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i<=n;i++){
            dp[0][i] = 0;
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return s1.length()+s2.length()-2*dp[m][n];
    }
    public static int minimumOperationToMakeStringEqualSpaceOptimized(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n+1];
        for(int i = 0;i<=m;i++){
            prev[0] = 0;
        }
        for(int i = 1;i<=m;i++){
            int[] curr = new int[n+1];
            for(int j = 1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }else{
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
            }
            prev=curr;
        }
        return s1.length()+s2.length()-2*prev[n];
    }
    public static void main(String[] args){
        String s1 = "abcd";
        String s2 = "anc";

        String a1 = "aaa";
        String a2 = "aa";
        //Using Recursion
        // Time Complexity: O(2^N+2^N)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Recursion: ");
        System.out.println(minimumOperationToMakeStringEqualRecursion(s1,s2));
        System.out.println(minimumOperationToMakeStringEqualRecursion(a1,a2));


        // Using Memoization
        // Time Complexity: O(N*M)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Memoization: ");
        System.out.println(minimumOperationToMakeStringEqualMemoization(s1,s2));
        System.out.println(minimumOperationToMakeStringEqualMemoization(a1,a2));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(N*M)
        System.out.println("Using Tabulation: ");
        System.out.println(minimumOperationToMakeStringEqualTabulation(s1,s2));
        System.out.println(minimumOperationToMakeStringEqualTabulation(a1,a2));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(M)
        System.out.println("Using Space Optimized: ");
        System.out.println(minimumOperationToMakeStringEqualSpaceOptimized(s1,s2));
        System.out.println(minimumOperationToMakeStringEqualSpaceOptimized(a1,a2));
    }
}