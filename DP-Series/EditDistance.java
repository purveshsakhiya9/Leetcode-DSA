import java.util.Arrays;

public class EditDistance {
    public static int editdistanceRecursion(String s1, String s2){
        return recursionHelper(s1,s2,s1.length()-1,s2.length()-1);
    }
    private static int recursionHelper(String s1, String s2, int m, int n){
        if(m<0) return n+1;
        if(n<0) return m+1;

        if(s1.charAt(m)==s2.charAt(n)){
            return recursionHelper(s1,s2,m-1,n-1);
        }else{
            int insert = 1+recursionHelper(s1,s2,m,n-1);
            int delete = 1+recursionHelper(s1,s2,m-1,n);
            int replace = 1+recursionHelper(s1,s2,m-1,n-1);
            return Math.min(insert,Math.min(delete,replace));
        }
    }
    public static int editdistanceMemoization(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(s1,s2,s1.length(),s2.length(),dp);
    }
    private static int memoizationHelper(String s1, String s2, int m, int n, int[][] dp){
        if(m==0) return n;
        if(n==0) return m;
        if(dp[m][n]!=-1) return dp[m][n];
        if(s1.charAt(m-1)==s2.charAt(n-1)){
            dp[m][n] = memoizationHelper(s1,s2,m-1,n-1,dp);
        }else{
            int insert = 1+memoizationHelper(s1,s2,m,n-1,dp);
            int delete = 1+memoizationHelper(s1,s2,m-1,n,dp);
            int replace = 1+memoizationHelper(s1,s2,m-1,n-1,dp);
            return dp[m][n]=Math.min(insert,Math.min(delete,replace));
        }
        return dp[m][n];
    }
    public static int editDistanceTabulation(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int j = 0; j<=n;j++){
            dp[0][j] = j;
        }
        for(int i = 0;i<=m;i++){
            dp[i][0]= i;
        }
        for(int i = 1;i<=m;i++){
            for(int j =1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] =dp[i-1][j-1];
                }else{
                    int insert = 1+dp[i][j-1];
                    int delete = 1+dp[i-1][j];
                    int replace = 1+dp[i-1][j-1];
                    dp[i][j]=Math.min(insert,Math.min(delete,replace));
                }
            }
        }
        return dp[m][n];
    }
    public static int editDistanceSpaceOptimized(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n+1];

        for(int j = 0; j<=n;j++){
            prev[j] = j;
        }
        for(int i = 1;i<=m;i++){
            int[] curr = new int[n+1];
            curr[0] = i;
            for(int j =1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    curr[j] =prev[j-1];
                }else{
                    int insert = 1+curr[j-1];
                    int delete = 1+prev[j];
                    int replace = 1+prev[j-1];
                    curr[j]=Math.min(insert,Math.min(delete,replace));
                }
            }
            prev=curr;
        }
        return prev[n];
    }
    public static void main(String[] args){
        String s1 = "abc";
        String s2 = "dc";
        String a1 = "whgtdwhgtdg";
        String a2 = "aswcfg";
        // Using recursion
        // Time Complexity: Exponential
        // Space Complexity: O(M*N) + O(M+N)
        System.out.println("Using Recursion: ");
        System.out.println(editdistanceRecursion(s1,s2));
        System.out.println(editdistanceRecursion(a1,a2));

        // Using Memoization
        // Time Complexity: O(M*N)
        // Space Complexity: O(N*M) + O(M+N)
        System.out.println("Using Memoization: ");
        System.out.println(editdistanceMemoization(s1,s2));
        System.out.println(editdistanceMemoization(a1,a2));

        // Using Tabulation
        // Time Complexity: O(M*N)
        // Space Complexity: O(M*N)
        System.out.println("using tabulation: ");
        System.out.println(editDistanceTabulation(s1,s2));
        System.out.println(editDistanceTabulation(a1,a2));

        // Using Space Optimized
        // Time Complexity: O(M*N)
        // Space Complexity: O(N)+O(N)
        System.out.println("using Space Optimized: ");
        System.out.println(editDistanceSpaceOptimized(s1,s2));
        System.out.println(editDistanceSpaceOptimized(a1,a2));
    }
}