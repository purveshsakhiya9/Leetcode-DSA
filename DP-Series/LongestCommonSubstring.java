import java.util.Arrays;

public class LongestCommonSubstring {
    public static int longestCommonSubstringRecursion(String s1, String s2){
        return recursionHelper(s1,s2,s1.length(),s2.length(),0);
    }
    private static int recursionHelper(String s1, String s2, int m, int n, int count){
        if(m==0 || n==0) return count;
        if(s1.charAt(m-1)==s2.charAt(n-1)){
            count = recursionHelper(s1,s2,m-1,n-1,count+1);
        }
        return Math.max(count,Math.max(recursionHelper(s1,s2,m,n-1,0),recursionHelper(s1,s2,m-1,n,0)));
    }
    public static int longestCommonSubstringMemoization(String s1, String s2){
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return memoizationHelper(s1,s2,s1.length(),s2.length(),0,dp);
    }
    private static int memoizationHelper(String s1, String s2, int m, int n,int count, int[][] dp){
        if (m == 0 || n == 0) {
            return count;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int currentCount = count;

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            currentCount = memoizationHelper(s1, s2, m - 1, n - 1, count + 1, dp);
        }
        return  Math.max(currentCount, Math.max(memoizationHelper(s1, s2, m - 1, n, 0, dp), memoizationHelper(s1, s2, m, n - 1, 0, dp)));
    }
    public static int longestCommonSubstringTabulation(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i<=m;i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i<=n;i++){
            dp[0][i] = 0;
        }
        int ans = 0;
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans,dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }
    public static int longestCommonSubstringSpaceOptimized(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n+1];
        for(int i = 0;i<=m;i++){
            prev[0] = 0;
        }
        int ans = 0;
        for(int i = 1;i<=m;i++){
            int[] curr = new int[n+1];
            for(int j = 1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                    ans = Math.max(ans,curr[j]);
                }else{
                    curr[j] = 0;
                }
            }
            prev=curr;
        }
        return ans;
    }
    public static void main(String[] args){
        String s1 = "wasdijkl";
        String s2 = "wsdjkl";
        String a1 = "tyfg";
        String a2 = "cvbnuty";
        //Using Recursion
        // Time Complexity: O(2^N+2^N)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Recursion: ");
        System.out.println(longestCommonSubstringRecursion(s1,s2));
        System.out.println(longestCommonSubstringRecursion(a1,a2));

        // Using Memoization
        // Time Complexity: O(N*M)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Memoization: ");
        System.out.println(longestCommonSubstringMemoization(s1,s2));
        System.out.println(longestCommonSubstringMemoization(a1,a2));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(N*M)
        System.out.println("Using Tabulation: ");
        System.out.println(longestCommonSubstringTabulation(s1,s2));
        System.out.println(longestCommonSubstringTabulation(a1,a2));


        //Using Space Optimized
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(M)
        System.out.println("Using Space Optimized: ");
        System.out.println(longestCommonSubstringSpaceOptimized(s1,s2));
        System.out.println(longestCommonSubstringSpaceOptimized(a1,a2));
    }
}