import java.util.Arrays;

public class PalindromePartitioningII {
    public static int palindromePartitioningIIRecursion(String str){
        int n = str.length();
        return recursionHelper(0,n,str)-1;
    }
    private static int recursionHelper(int i,int n, String str){
        if(i==n) return 0;
        int min = Integer.MAX_VALUE;
        for(int j = i;j<n;j++){
            if(isPalindrome(i,j,str)){
                int cost = 1+recursionHelper(j+1,n,str);
                min = Math.min(min,cost);
            }
        }
        return min;
    }
    public static int palindromePartitioningIIMemoization(String str){
        int n = str.length();
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return memoizationHelper(0,n,str,dp)-1;
    }
    private static int memoizationHelper(int i,int n, String str,int[] dp){
        if(i==n) return 0;
        if(dp[i]!=-1)return dp[i];
        int min = Integer.MAX_VALUE;
        for(int j = i;j<n;j++){
            if(isPalindrome(i,j,str)){
                int cost = 1+memoizationHelper(j+1,n,str,dp);
                min = Math.min(min,cost);
            }
        }
        return dp[i] = min;
    }
    private static boolean isPalindrome(int i, int j,String s){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public static int palindromePartitioningIITabulation(String str){
        int n = str.length();
        int[] dp = new int[n+1];
        for (int i = n-1;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for(int j = i;j<n;j++){
                if(isPalindrome(i,j,str)){
                    int cost = 1+dp[j+1];
                    min = Math.min(min,cost);
                }
            }
            dp[i] = min;
        }
        return dp[0]-1;
    }
    public static int palindromePartitioningIISpaceOptimized(String str){
        return 0;
    }
    public static void main(String[] args){
        String str = "aaccb";
        String str1 = "aababa";
        // Using Recursion
        // Time Complexity: exponential
        // Space Complexity: O(N)
        System.out.println("Using Recursion: ");
        System.out.println(palindromePartitioningIIRecursion(str));
        System.out.println(palindromePartitioningIIRecursion(str1));

        // Using Memoization
        // Time Complexity: O(N*N)
        // Space Complexity: O(N)+O(N)
        System.out.println("Using Memoization: ");
        System.out.println(palindromePartitioningIIMemoization(str));
        System.out.println(palindromePartitioningIIMemoization(str1));

        // Using Tabulation
        // Time Complexity: O(N*N)
        // Space Complexity: O(N)
        System.out.println("Using Tabulation: ");
        System.out.println(palindromePartitioningIITabulation(str));
        System.out.println(palindromePartitioningIITabulation(str1));
    }
}