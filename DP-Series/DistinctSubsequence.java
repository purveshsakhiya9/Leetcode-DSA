import java.util.Arrays;

public class DistinctSubsequence {
    public static int distinctSubsequenceRecursion(String str, String sub){
        return recursionHelper(str,sub,str.length()-1,sub.length()-1);
    }
    private static int recursionHelper(String s1, String s2, int i, int j){
        if(j<0) return 1;
        if(i<0) return 0;
        if(s1.charAt(i)==s2.charAt(j)){
            return recursionHelper(s1,s2,i-1,j-1) + recursionHelper(s1,s2,i-1,j);
        }else{
            return recursionHelper(s1,s2,i-1,j);
        }
    }
    public static int distinctSubsequenceMemoization(String str, String sub){
        int[][] dp = new int[str.length()][sub.length()];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return memoizationHelper(str,sub,str.length()-1,sub.length()-1,dp);
    }
    private static int memoizationHelper(String s1, String s2, int i, int j,int[][] dp){
        if(j<0) return 1;
        if(i<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s1.charAt(i)==s2.charAt(j)){
            return dp[i][j]=memoizationHelper(s1,s2,i-1,j-1,dp) + memoizationHelper(s1,s2,i-1,j,dp);
        }else{
            return dp[i][j]=memoizationHelper(s1,s2,i-1,j,dp);
        }
    }
    public static int MOD = 1000000000+7;
    public static int distinctSubsequenceTabulation(String str, String sub){
        int[][] dp = new int[str.length()+1][sub.length()+1];
        for(int i = 0; i<=str.length();i++){
            dp[i][0] = 1;
        }
        for(int j= 1;j<=sub.length();j++){
            dp[0][j] = 0 ;
        }
        for(int i = 1;i<=str.length();i++){
            for(int j=1;j<=sub.length();j++){
                if(str.charAt(i-1)==sub.charAt(j-1)){
                    dp[i][j]=(dp[i-1][j-1] + dp[i-1][j])%MOD;
                }else{
                    dp[i][j]=dp[i-1][j]%MOD;
                }
            }
        }
        return dp[str.length()][sub.length()];
    }
    public static int distinctSubsequenceSpaceOptimized(String str, String sub){
        int[] prev = new int[sub.length()+1];
        prev[0]=1;
        for(int i = 1;i<=str.length();i++){
            for(int j=sub.length();j>=1;j--){
                if(str.charAt(i-1)==sub.charAt(j-1)){
                    prev[j]=(prev[j-1] + prev[j])%MOD;
                }else{
                    prev[j]=prev[j]%MOD;
                }
            }
        }
        return prev[sub.length()];
    }
    public static void main(String[] args){
        String s1 = "eecedda";
        String s2 = "aaa";

        String a1 = "dingdingdingding";
        String a2 = "ing";
        //Using Recursion
        // Time Complexity: O(2^N+2^N)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Recursion: ");
        System.out.println(distinctSubsequenceRecursion(s1,s2));
        System.out.println(distinctSubsequenceRecursion(a1,a2));


        // Using Memoization
        // Time Complexity: O(N*M)
        // Space Complexity: O(N*M) + O(N+M)
        System.out.println("Using Memoization: ");
        System.out.println(distinctSubsequenceMemoization(s1,s2));
        System.out.println(distinctSubsequenceMemoization(a1,a2));

        //Using Tabulation
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(N*M)
        System.out.println("Using Tabulation: ");
        System.out.println(distinctSubsequenceTabulation(s1,s2));
        System.out.println(distinctSubsequenceTabulation(a1,a2));

        //Using Space Optimized
        // Time Complexity: O(N) + O(N*M) + O(N)
        // Space Complexity: O(M)
        System.out.println("Using Space Optimized: ");
        System.out.println(distinctSubsequenceSpaceOptimized(s1,s2));
        System.out.println(distinctSubsequenceSpaceOptimized(a1,a2));
    }
}