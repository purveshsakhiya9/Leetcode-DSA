import java.util.Arrays;

public class ShortestSuperSequence {
    public static String shortestSuperSequenceRecursion(String s1, String s2){
        return recursionHelper(s1, s2, s1.length(), s2.length());
    }

    private static String recursionHelper(String s1, String s2, int m, int n) {
        if (m == 0) {
            return s2.substring(0, n);
        }
        if (n == 0) {
            return s1.substring(0, m);
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return recursionHelper(s1, s2, m - 1, n - 1) + s1.charAt(m - 1);
        } else {
            String option1 = recursionHelper(s1, s2, m - 1, n) + s1.charAt(m - 1);
            String option2 = recursionHelper(s1, s2, m, n - 1) + s2.charAt(n - 1);
            return option1.length() < option2.length() ? option1 : option2;
        }
    }
    public static String shortestSuperSequenceMemoization(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        memoizationHelper(s1,s2,m,n,dp);
        String ans = "";
        int i = m,j=n;
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                ans+=s1.charAt(i-1);
                i--;
                j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                ans+=s1.charAt(i-1);
                i--;
            }else{
                ans+=s2.charAt(j-1);
                j--;
            }
        }
        while(i>0){
            ans+=s1.charAt(i-1);
            i--;
        }
        while(j>0){
            ans+=s2.charAt(j-1);
            j--;
        }
        return new StringBuilder(ans).reverse().toString();
    }
    private static int memoizationHelper(String s1, String s2, int m, int n, int[][] dp){
        if(m==0 || n==0){
            return 0;
        }
        if(dp[m][n]!=-1) return dp[m][n];
        if(s1.charAt(m-1)==s2.charAt(n-1)){
            return dp[m][n]=1+memoizationHelper(s1,s2,m-1,n-1,dp);
        }
        return dp[m][n]=Math.max(memoizationHelper(s1,s2,m,n-1,dp),memoizationHelper(s1,s2,m-1,n,dp));
    }
    public static String shortestSuperSequenceTabulation(String s1, String s2){
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
        String ans = "";
        int i = m,j=n;
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                ans+=s1.charAt(i-1);
                i--;
                j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                ans+=s1.charAt(i-1);
                i--;
            }else{
                ans+=s2.charAt(j-1);
                j--;
            }
        }
        while(i>0){
            ans+=s1.charAt(i-1);
            i--;
        }
        while(j>0){
            ans+=s2.charAt(j-1);
            j--;
        }
        return new StringBuilder(ans).reverse().toString();
    }
    public static void main(String[] args){
        String s1 = "cxkizrdghf";
        String s2 = "msnjvnjnejxg";

        String a1 = "blinding";
        String a2 = "lights";
        //Using Recursion
        // Time Complexity: O(2^N+2^N)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Recursion: ");
        System.out.println(shortestSuperSequenceRecursion(s1,s2));
        System.out.println(shortestSuperSequenceRecursion(a1,a2));


        // Using Memoization
        // Time Complexity: O(N*M)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Memoization: ");
        System.out.println(shortestSuperSequenceMemoization(s1,s2));
        System.out.println(shortestSuperSequenceMemoization(a1,a2));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(N*M)
        System.out.println("Using Tabulation: ");
        System.out.println(shortestSuperSequenceTabulation(s1,s2));
        System.out.println(shortestSuperSequenceTabulation(a1,a2));
    }
}