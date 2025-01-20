import java.util.Arrays;

public class PrintLongestCommonSubsequence {

    public static int longestCommonSubsequenceTabulation(String s1, String s2){
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
        Character[] ans = new Character[dp[m][n]];
        int index = dp[m][n]-1;
        int i=m,j=n;
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                ans[index] = s1.charAt(i-1);
                index--;i--;j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        for(char ch:ans){
            System.out.print(ch);
        }
        System.out.println();
        return dp[m][n];
    }

    public static void main(String[] args){
        String s1 = "adebc";
        String s2 = "dcadb";

        String a1 = "abd";
        String a2 = "defg";
        //Using Tabulation
        // Time Complexity: O(N) + O(N*totSum) + O(N)
        // Space Complexity: O(N*TotalSum)
        System.out.println("Using Tabulation: ");
        System.out.println(longestCommonSubsequenceTabulation(s1,s2));
        System.out.println(longestCommonSubsequenceTabulation(a1,a2));
    }
}