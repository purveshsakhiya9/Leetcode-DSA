import java.util.Arrays;

public class MinimumInsertionsToMakeStringPalindrome {
    public static int minimumInsertionsToMakeStringPalindromeRecursion(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
        return s1.length()-recursionHelper(s1,s2,s1.length()-1,s2.length()-1);
    }
    private static int recursionHelper(String s1, String s2, int m, int n){
        if(m<0 || n<0) return 0;
        if(s1.charAt(m)==s2.charAt(n)){
            return 1+recursionHelper(s1,s2,m-1,n-1);
        }
        return Math.max(recursionHelper(s1,s2,m,n-1),recursionHelper(s1,s2,m-1,n));
    }
    public static int minimumInsertionsToMakeStringPalindromeMemoization(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return s1.length()-memoizationHelper(s1,s2,m-1,n-1,dp);
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
    public static int minimumInsertionsToMakeStringPalindromeTabulation(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
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
        return s1.length()-dp[m][n];
    }
    public static int minimumInsertionsToMakeStringPalindromeSpaceOptimized(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
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
        return s1.length()-prev[n];
    }
    public static void main(String[] args){
        String s1 = "abca";
        String s2 = "abcdefg";
        //Using Recursion
        // Time Complexity: O(2^N+2^N)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Recursion: ");
        System.out.println(minimumInsertionsToMakeStringPalindromeRecursion(s1));
        System.out.println(minimumInsertionsToMakeStringPalindromeRecursion(s2));


        // Using Memoization
        // Time Complexity: O(N*M)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Memoization: ");
        System.out.println(minimumInsertionsToMakeStringPalindromeMemoization(s1));
        System.out.println(minimumInsertionsToMakeStringPalindromeMemoization(s2));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(N*M)
        System.out.println("Using Tabulation: ");
        System.out.println(minimumInsertionsToMakeStringPalindromeTabulation(s1));
        System.out.println(minimumInsertionsToMakeStringPalindromeTabulation(s2));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(M)
        System.out.println("Using Space Optimized: ");
        System.out.println(minimumInsertionsToMakeStringPalindromeSpaceOptimized(s1));
        System.out.println(minimumInsertionsToMakeStringPalindromeSpaceOptimized(s2));
    }
}