import java.util.Arrays;

public class NumberOfLongestIncreasinSubsequence {
    public static int numberOfLongestIncreasingSubsequence(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(count,1);
        for(int i = 0;i<n;i++){
            for(int prev = 0;prev<i;prev++){
                if(arr[i]>arr[prev] && dp[prev]+1>dp[i]){
                    dp[i] = dp[prev]+1;
                    count[i] = count[prev];
                }else if(arr[i]>arr[prev] && dp[prev]+1==dp[i]){
                    count[i] +=count[prev];
                }
            }
        }
        int maxi = 1;
        int ans = 0;
        for(int i = 1;i<n;i++){
            if(dp[i]>maxi){
                maxi = dp[i];
            }
        }
        for(int i = 0; i<n;i++){
            if(dp[i]==maxi){
                ans+=count[i];
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int[] arr = {5,7,2,3};
        System.out.println(numberOfLongestIncreasingSubsequence(arr));
    }
}