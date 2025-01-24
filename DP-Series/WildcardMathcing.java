import java.util.Arrays;

public class WildcardMathcing {
    public static boolean wildcardMatchingRecursion(String s1,String s2){
        return recursionHelper(s1,s2,s1.length()-1,s2.length()-1);
    }
    private static boolean recursionHelper(String s1, String s2, int m, int n){
        if(m<0 && n<0) return true;
        if(m<0 && n>=0) return false;
        if(m>=0 && n<0){
            for(int i = 0; i<=m;i++){
                if(s1.charAt(i)!='*') return false;
            }
            return true;
        }
        if(s1.charAt(m)==s2.charAt(n) || s1.charAt(m)=='?'){
            return recursionHelper(s1,s2,m-1,n-1);
        }else if(s1.charAt(m)=='*'){
            return recursionHelper(s1,s2,m-1,n) || recursionHelper(s1,s2,m,n-1);
        }
        return false;
    }
    public static boolean wildcarMatchingMemoization(String s1, String s2){
        int[][] dp  = new int[s1.length()+1][s2.length()+1];
        for(int[] row: dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(s1,s2,s1.length(),s2.length(),dp)==1?true:false;
    }
    private static int memoizationHelper(String s1, String s2, int m, int n,int[][] dp){
        if(m==0 && n==0) return 1;
        if(m==0 && n>0) return 0;
        if(m>0 && n==0){
            for(int i = 1; i<=m; i++){
                if(s1.charAt(i-1)!='*') return 0;
            }
            return 1;
        }
        if(dp[m][n]!=-1) return dp[m][n];
        if(s1.charAt(m-1)==s2.charAt(n-1) || s1.charAt(m-1)=='?'){
            return dp[m][n] = memoizationHelper(s1,s2,m-1,n-1,dp);
        }else if(s1.charAt(m-1)=='*'){
            return dp[m][n] = (memoizationHelper(s1,s2,m-1,n,dp)==1 || memoizationHelper(s1,s2,m,n-1,dp)==1)?1:0;
        }else{
            return 0;
        }
    }
    public static boolean wildcardMatchingTabulation(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0]=true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = false;
        }
        for(int i =1;i<=m;i++){
            boolean flag = true;
            for(int ii = 1; ii<=i; ii++){
                if(s1.charAt(ii-1)!='*'){
                    flag = false;
                    break;
                }
            }
            dp[i][0]=flag;
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1; j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1) || s1.charAt(i-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(s1.charAt(i-1)=='*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else{
                    dp[i][j]=false;
                }
            }
        }
        return dp[m][n];
    }
    public static boolean wildcardMatchingSpaceOptimized(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        boolean[] prev = new boolean[n+1];

        prev[0]=true;
        for(int i = 1;i<=m;i++){
            boolean[] curr = new boolean[n+1];
            boolean flag = true;
            for(int ii = 1; ii<=i; ii++){
                if(s1.charAt(ii-1)!='*'){
                    flag = false;
                    break;
                }
            }
            curr[0]=flag;
            for(int j = 1; j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1) || s1.charAt(i-1)=='?'){
                    curr[j] = prev[j-1];
                }else if(s1.charAt(i-1)=='*'){
                    curr[j] = prev[j] || curr[j-1];
                }else{
                    curr[j]=false;
                }
            }
            prev=curr;
        }
        return prev[n];
    }
    public static void main(String[] args){
        String s1 = "*a*b";
        String s2 = "adceb";
        String a1 = "ab*cd";
        String a2 = "abdefcd";

        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(M*N)+O(M+N)
        System.out.println("Using Recursion: ");
        System.out.println(wildcardMatchingRecursion(s1,s2));
        System.out.println(wildcardMatchingRecursion(a1,a2));

        // Using Memoization
        // Time Complexity: O(M*N)
        // Space Complexity: O(M*N) + O(M+N)
        System.out.println("Using Memoization: ");
        System.out.println(wildcarMatchingMemoization(s1,s2));
        System.out.println(wildcarMatchingMemoization(a1,a2));

        // Using Tabulation
        // Time Complexity: O(M*N)
        // Space Complexity: O(M*N)
        System.out.println("Using Tabulation: ");
        System.out.println(wildcardMatchingTabulation(s1,s2));
        System.out.println(wildcardMatchingTabulation(a1,a2));

        // Using Space Optimized
        // Time Complexity: O(M*N)
        // Space Complexity: O(N)
        System.out.println("Using Space Optimized: ");
        System.out.println(wildcardMatchingSpaceOptimized(s1,s2));
        System.out.println(wildcardMatchingSpaceOptimized(a1,a2));

        //
    }
}